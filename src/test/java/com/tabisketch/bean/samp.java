package com.tabisketch.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tabisketch.bean.entity.Destination;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class samp {
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void a() throws JsonProcessingException {
        final var destinations = new ArrayList<>();
        destinations.add(new Destination());
        destinations.add(new Destination());
        final var json = objectMapper.writeValueAsString(destinations);
        System.out.println(json);
    }
}
