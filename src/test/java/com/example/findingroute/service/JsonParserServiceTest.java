package com.example.findingroute.service;

import com.example.findingroute.domain.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonParserServiceTest {
    String json = "";
    String wrongJson = "[{\"name\": {\"common\": \"Aruba\",\"official\": \"Aruba\"}},{\"name\": {\"common\": " +
            "\"Afghanistan\", \"official\": \"Islamic Republic of Afghanistan\"}}]";

    @BeforeEach
    public void init() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource("json.txt")).getFile());
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            while (bufferedReader.ready()) {
                sb.append(bufferedReader.readLine());
            }
            json = sb.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void parse() {
        List<Node> parsedList = JsonParserService.parse(json);
        assertEquals(5, parsedList.size());
    }

    @Test
    void parseJSONWithError() {
        List<Node> parsedList = JsonParserService.parse(wrongJson);
        assertEquals(0, parsedList.size());
    }
}