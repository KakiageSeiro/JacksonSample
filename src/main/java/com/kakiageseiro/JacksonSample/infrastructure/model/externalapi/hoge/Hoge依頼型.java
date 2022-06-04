package com.kakiageseiro.JacksonSample.infrastructure.model.externalapi.hoge;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kakiageseiro.JacksonSample.infrastructure.model.externalapi.Auth;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonRootName("request")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Hoge依頼型<T> {
    public Auth auth;
    public long projectId;
    public T content;

    public Auth getAuth() {
        return auth;
    }

    public long getProjectId() {
        return projectId;
    }

    public T getContent() {
        return content;
    }
}
