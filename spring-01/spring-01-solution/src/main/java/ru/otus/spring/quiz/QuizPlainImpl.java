package ru.otus.spring.quiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class QuizPlainImpl implements Quiz {
    private final String resourcePath;

    public QuizPlainImpl(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Override
    public PassageStatus display() {
        List<String> plainTextLines = new ArrayList<>();
        try (InputStream inputStream = getClass().getResourceAsStream(resourcePath)) {
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            for (String line; (line = reader.readLine()) != null; ) {
                plainTextLines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String line : plainTextLines) {
            System.out.println(line);
        }
        return PassageStatus.SUCCESS;
    }
}
