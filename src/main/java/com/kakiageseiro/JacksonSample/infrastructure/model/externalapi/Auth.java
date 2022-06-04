package com.kakiageseiro.JacksonSample.infrastructure.model.externalapi;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Auth {
    public long personId;
    public String userId;
    public String languageCode;
    public String authTime;
    public String sessionId;

    public Auth(long personId, String userId, String languageCode, String authTime, String sessionId) {
        this.personId = personId;
        this.userId = userId;
        this.languageCode = languageCode;
        this.authTime = authTime;
        this.sessionId = sessionId;
    }

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