package com.kakiageseiro.JacksonSample.controller.結果型;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class jzip住所結果型 {
    @JsonProperty
    private String state;
    @JsonProperty
    private String stateName;
    @JsonProperty
    private String city;
    @JsonProperty
    private String street;

    @Override
    public String toString() {
        return "jzip住所結果型{" +
                "state='" + state + '\'' +
                ", stateName='" + stateName + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
