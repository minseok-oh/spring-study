package com.java.study.learningtest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.study.Greeting;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.junit.jupiter.api.Test;

public class ObjectMapperTest {
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void writeFileOfGreeting() {
        try {
            Greeting greeting = new Greeting(1L, "Greeting");
            File file = new File("src/greeting.json");

            objectMapper.writeValue(file, greeting);

            var reader = new BufferedReader(new FileReader(file));
            assertThat(reader.readLine()).isEqualTo("{\"id\":1,\"content\":\"Greeting\"}");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void readFileOfGreeting() {
        try {
            Greeting readedGreeting = objectMapper.readValue(new File("src/greeting.json"), Greeting.class);

            assertThat(readedGreeting.id()).isEqualTo(1L);
            assertThat(readedGreeting.content()).isEqualTo("Greeting");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void readStringOfGreeting() {
        try {
            String jsonGreeting = "{\"id\": 1, \"content\": \"Greeting\"}";

            Greeting readedGreeting = objectMapper.readValue(jsonGreeting, Greeting.class);

            assertThat(readedGreeting.id()).isEqualTo(1);
            assertThat(readedGreeting.content()).isEqualTo("Greeting");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void readInvalidStringOfGreeting() {
        String invalidJsonGreeting = "{\"id\": 1, \"name\": \"Greeting\"}";

        assertThrows(DatabindException.class,
                () -> objectMapper.readValue(invalidJsonGreeting, Greeting.class));
    }
}
