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

    private final String hi;
    private final String bye;

    public String hello() {
        return String.format(" %s how are you? at %s", hi, LocalDateTime.now().toString());
    }

}
