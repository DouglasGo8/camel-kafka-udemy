package com.udemy.camel.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 *
 */
@Data
@AllArgsConstructor
public class MyBean {

    private String hi;
    private String bye;

    public String hello() {
        return String.format(" %s how are you? at %s", hi, LocalDateTime.now().toString());
    }

    public String bye() {
        return bye + " World";
    }
}
