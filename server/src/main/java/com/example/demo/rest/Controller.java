package com.example.demo.rest;


import com.example.demo.ResponseRecap;
import com.example.demo.UserData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

@Slf4j
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/")
public class Controller {

    WebClient builder = WebClient.create();
    private final String key = "6Lc7l_cUAAAAAN44oewe0vnrCOgjQc_GrGXu17AO";

    @PostMapping("user")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getUserData(@RequestBody UserData data) {
        log.info("hit");
        log.info(data.getEmail());
        log.info(data.getName());
        log.info(data.getPhotoUrl());
    }

    @PostMapping("recaptcha")
    public ResponseEntity<ResponseRecap> recaptchaUserData(@RequestBody String token) {
        log.info(token);
        String recaptchaUrl = new StringBuilder()
                .append("https://www.google.com/recaptcha/api/siteverify")
                .append("?secret=")
                .append(key)
                .append("&response=")
                .append(token)
                .toString();

        ResponseRecap data = builder.method(HttpMethod.POST)
                .uri(URI.create(recaptchaUrl))
                .accept(MediaType.APPLICATION_FORM_URLENCODED).retrieve().bodyToMono(ResponseRecap.class).block();
        log.info(data.getScore() + "," + data.isSuccess());


        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
