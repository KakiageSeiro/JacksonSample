package com.kakiageseiro.JacksonSample.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@Rollback
@SpringBootTest
@AutoConfigureMockMvc
public class 別のAPIを呼び出すコントローラーTest {
    private String mediaType = "application/json;charset=UTF-8";
    private String アクセストークン = "";

//    @Value("${aaa.hugahuga-access-key:}")
//    private String hugahugaAccessKey;

    @Autowired
    MockMvc mockMvc;

//    private String ログイン情報() throws IOException {
//        Resource resource = new ClassPathResource("認証/ログイン情報.json");
//        File file = resource.getFile();
//        return Files.readString(file.toPath());
//    }

//    @BeforeEach
//    void 認証情報を設定する() throws Exception {
//        MvcResult result = mockMvc.perform(post("/api/login/ja")
//                .header("Content-Type", "application/json").content(ログイン情報())
//                .accept(mediaType)).andReturn();
//        String content = result.getResponse().getContentAsString(Charset.defaultCharset());
//        ObjectMapper objectMapper = new ObjectMapper();
//        HashMap<String, String> 認証情報 = objectMapper.readValue(content, new TypeReference<>() {
//        });
//        this.アクセストークン = "Bearer " + 認証情報.get("アクセストークン");
//    }


    @Test
    public void 計算結果を取得できること() throws Exception {
        mockMvc.perform(get("/test/call").accept(mediaType))
                .andDo(print())
                .andExpect(status().isOk())
                // これが返ってくる想定{ "state":"13", "stateName":"東京都", "city":"大田区", "street":"蒲田" }
                .andExpect(jsonPath("$.state").value("13"))
                .andExpect(jsonPath("$.stateName").value("東京都"))
                .andExpect(jsonPath("$.city").value("大田区"))
                .andExpect(jsonPath("$.street").value("蒲田"))
        ;
    }

//    @Nested
//    class 正常系 {
//        private String サンプルプロジェクト情報(String fileName) throws IOException {
//            Resource resource = new ClassPathResource("連携/" + fileName);
//            File file = resource.getFile();
//            return Files.readString(file.toPath());
//        }
//
//        @Test
//        public void 計算結果を取得できること() throws Exception {
//            String サンプルプロジェクト情報 = サンプルプロジェクト情報("サンプルプロジェクト情報.json");
//            mockMvc.perform(post("/test/call").accept(mediaType)
//                            .header("Authorization", アクセストークン)
//                            // .header("X-hugahuga-ACCESS-KEY", hugahugaAccessKey)
//                            .header("Content-Type", "application/json")
//                            .content(サンプルプロジェクト情報))
//                    .andDo(print())
//                    .andExpect(jsonPath("$.リスト[0].nanikanoリスト[0].タイトル").value("ここに期待値"))
//                    .andExpect(status().isOk());
//        }
//    }
}