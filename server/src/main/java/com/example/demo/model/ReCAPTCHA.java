package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReCAPTCHA {
    private String key;
    private String token;

    public ReCAPTCHA(String key, String token) {
        this.key = key;
        this.token = token;
    }
}
