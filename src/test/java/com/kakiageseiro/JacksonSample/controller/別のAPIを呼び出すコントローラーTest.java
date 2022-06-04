package com.kakiageseiro.JacksonSample.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@Rollback
@SpringBootTest
@AutoConfigureMockMvc
public class 別のAPIを呼び出すコントローラーTest {
    private String mediaType = "application/json;charset=UTF-8";
    private String アクセストークン = "";

    @Value("${successrate.hugahuga-access-key:}")
    private String hugahugaAccessKey;

    @Autowired
    MockMvc mockMvc;

    private String ログイン情報() throws IOException {
        Resource resource = new ClassPathResource("認証/ログイン情報.json");
        File file = resource.getFile();
        return Files.readString(file.toPath());
    }

    @BeforeEach
    void 認証情報を設定する() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/login/ja")
                .header("Content-Type", "application/json").content(ログイン情報())
                .accept(mediaType)).andReturn();
        String content = result.getResponse().getContentAsString(Charset.defaultCharset());
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, String> 認証情報 = objectMapper.readValue(content, new TypeReference<>() {
        });
        this.アクセストークン = "Bearer " + 認証情報.get("アクセストークン");
    }

    @Transactional
    @Rollback
    @SpringBootTest
    @AutoConfigureMockMvc
    @Nested
    class 計算 {
        private String サンプルプロジェクト情報(String fileName) throws IOException {
            Resource resource = new ClassPathResource("連携/" + fileName);
            File file = resource.getFile();
            return Files.readString(file.toPath());
        }
        
        @Test
        public void 計算結果を取得できること() throws Exception {
            String サンプルプロジェクト情報 = サンプルプロジェクト情報("サンプルプロジェクト情報.json");
            mockMvc.perform(post("/api/external/success/rate/calc/v1").accept(mediaType)
                    .header("Authorization", アクセストークン)
                    // .header("X-hugahuga-ACCESS-KEY", hugahugaAccessKey)
                    .header("Content-Type", "application/json")
                    .content(サンプルプロジェクト情報))
                    .andDo(print())
                    .andExpect(jsonPath("$.リスト[0].nanikanoリスト[0].タイトル").value("ここに期待値"))
                    .andExpect(status().isOk());
        }
    }
}