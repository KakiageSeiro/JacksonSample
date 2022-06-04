package com.kakiageseiro.JacksonSample.infrastructure.model.externalapi.hoge;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.kakiageseiro.JacksonSample.infrastructure.model.externalapi.apibase.ApiLinkFailureException;
import org.springframework.web.client.RestTemplate;

public class Hoge連携クライアント {
    private RestTemplate restTemplate;
    private String url;
    private String path;

    public Hoge連携クライアント(RestTemplate restTemplate, String url, String path) {
        this.restTemplate = restTemplate;
        this.url = url;
        this.path = path;
    }

    // このサンプルプロジェクトではつかってないよ！
    public String get() {
        return restTemplate.getForObject(this.url + this.path, String.class);
    }

    public Hoge結果型 post(Hoge依頼型 payload) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        String json;
        try {
            json = mapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("objectMapper.writeValueAsString error", e);
        }
        Hoge結果型 hoge結果 = restTemplate.postForObject(this.url + this.path, json, Hoge結果型.class);
        if (hoge結果.getStatus() != 0) {
            throw new ApiLinkFailureException(hoge結果.getMessages()); // 独自エラー型
        }
        return hoge結果;
    }
}