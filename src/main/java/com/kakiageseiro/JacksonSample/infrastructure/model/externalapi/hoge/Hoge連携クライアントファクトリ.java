package com.kakiageseiro.JacksonSample.infrastructure.model.externalapi.hoge;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Component
public class Hoge連携クライアントファクトリ {
    @Value("${external.hoge.baseurl}")
    String baseUrl;

    public Hoge連携クライアント getクライアント(String path, String id) {
        // これってなにしてたっけ？
        String url = String.format(this.baseUrl, id);

        return new Hoge連携クライアント(this.getRestTemplate(), url, path);
    }

    private RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            return execution.execute(request, body);
        });
        restTemplate.getInterceptors().add((request, body, execution) -> {
            MediaType mediaType = new MediaType("application", "json", StandardCharsets.UTF_8);
            request.getHeaders().setContentType(mediaType);
            return execution.execute(request, body);
        });
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }
}

