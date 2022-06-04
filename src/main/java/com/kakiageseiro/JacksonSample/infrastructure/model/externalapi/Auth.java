package com.kakiageseiro.JacksonSample.infrastructure.model.externalapi;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Auth {
    public long personId;
    public String userId;
    public String languageCode;
    public String authTime;
    public String sessionId;

    public static Auth 作成する(long personId, String userId, String languageCode) {
        return new Auth(
                personId,
                userId,
                languageCode,
                LocalDate.now().toString(),
                "xxx"
        );
    }
}