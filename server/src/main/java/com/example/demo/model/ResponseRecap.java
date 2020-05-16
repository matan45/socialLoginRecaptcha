package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ResponseRecap {

    private boolean success;
    private Float score;

    public ResponseRecap(@JsonProperty("success") boolean success, @JsonProperty("score") Float score) {
        this.success = success;
        this.score = score;
    }
}
