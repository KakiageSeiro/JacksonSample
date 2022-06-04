package com.kakiageseiro.JacksonSample.infrastructure.model.externalapi.hoge;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;
import java.util.stream.Collectors;

@JsonRootName("response")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Hoge結果型 {
    long status;
    List<String> messages;
    JsonNode content;

    public long getStatus() {
        return status;
    }

    public List<String> getMessages() {
        return messages;
    }

    public JsonNode getContent() {
        return content;
    }

    public Hoge結果型(@JsonProperty("response") JsonNode node) {
        this.status = node.path("status").asLong();
        this.messages = node.findValues("messages").stream().map(JsonNode::toString).collect(Collectors.toList());
        this.content = node.path("content");
    }
}