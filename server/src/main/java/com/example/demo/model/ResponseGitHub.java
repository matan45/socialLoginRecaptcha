package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class ResponseGitHub {
    String accesstoken;

    public ResponseGitHub(@JsonProperty("access_token")String accesstoken) {
        this.accesstoken = accesstoken;
    }
}
