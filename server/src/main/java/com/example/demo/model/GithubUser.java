package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class GithubUser {
    String login;
    String name;
    String email;

    public GithubUser(@JsonProperty("login")String login,@JsonProperty("name") String name,@JsonProperty("email") String email) {
        this.login = login;
        this.name = name;
        this.email = email;
    }
}
