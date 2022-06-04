package com.kakiageseiro.JacksonSample.infrastructure.model.externalapi.apibase;

import java.util.List;

public class ApiLinkFailureException extends RuntimeException {
    public ApiLinkFailureException(List<String> messages) {
        super(messages.toString());
    }
}
