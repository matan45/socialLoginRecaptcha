package com.example.demo.websockets;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OutputMessage {

    private String from;
    private String text;
    private String time;
}
