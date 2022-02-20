package ru.otus.spring.question.loading;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.spring.question.Question;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionsLoaderImpl implements QuestionsLoader {

    private final QuestionParser questionsParser;
    private final String resourcePath;

    public QuestionsLoaderImpl(QuestionParser questionsParser,
                               @Value("${quiz.resource-path}") String resourcePath) {
        this.questionsParser = questionsParser;
        this.resourcePath = resourcePath;
    }

    public List<Question> loadQuestions() {
        List<Question> questions = new ArrayList<>();
        try (InputStream inputStream = getClass().getResourceAsStream(resourcePath);
             InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            for (String line; (line = reader.readLine()) != null; ) {
                questions.add(questionsParser.fromLine(line));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return questions;
    }

}


