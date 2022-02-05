package ru.otus.spring.quiz.plain;

import ru.otus.spring.quiz.PassageStatus;
import ru.otus.spring.quiz.Quiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class QuizPlainImpl implements Quiz {
    private List<String> plainTextLines;

    public QuizPlainImpl(String resourcePath) throws IOException {
        try (InputStream inputStream = getClass().getResourceAsStream(resourcePath)) {
            plainTextLines = new ArrayList<>();
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            for (String line; (line = reader.readLine()) != null; ) {
                plainTextLines.add(line);
            }
        }
    }

    @Override
    public PassageStatus display() {
        for (String line : plainTextLines) {
            System.out.println(line);
        }
        return PassageStatus.SUCCESS;
    }

}
