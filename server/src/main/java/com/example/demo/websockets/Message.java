package com.example.demo.websockets;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Message {

    private String from;
    private String text;

}
