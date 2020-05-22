package com.example.demo.rest;


import com.example.demo.model.GithubUser;
import com.example.demo.model.ResponseGitHub;
import com.example.demo.model.ResponseRecap;
import com.example.demo.model.UserData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

@Slf4j
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/")
public class Controller {
    private static final String ClientSecret = "";
    private static final String ClientID = "3a9ad174ee76ffdc05b3";
    WebClient builder = WebClient.create();
    private final String key = System.getenv("RECAPTCHA");
    ;

    @PostMapping("user")
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

    @GetMapping("data")
    public void getUserData(@RequestParam String code, HttpServletResponse response) {
        log.info(code);
        String Urlat = new StringBuilder()
                .append("https://github.com/login/oauth/access_token")
                .append("?client_id=")
                .append(ClientID)
                .append("&client_secret=")
                .append(ClientSecret)
                .append("&code=")
                .append(code)
                .toString();
        ResponseGitHub data = builder.method(HttpMethod.POST)
                .uri(URI.create(Urlat)).accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(ResponseGitHub.class).block();
        log.info(data.toString());
        GithubUser user = builder.method(HttpMethod.GET)
                .uri(URI.create("https://api.github.com/user")).headers(httpHeaders -> {
                    httpHeaders.add("Authorization", "token " + data.getAccesstoken());
                }).accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(GithubUser.class).block();
        log.info(user.toString());
        try {
            response.sendRedirect("http://localhost:4200");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
