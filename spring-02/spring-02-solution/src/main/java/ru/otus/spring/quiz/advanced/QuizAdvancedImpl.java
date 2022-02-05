package ru.otus.spring.quiz.advanced;


import lombok.RequiredArgsConstructor;
import ru.otus.spring.io.IOService;
import ru.otus.spring.quiz.PassageStatus;
import ru.otus.spring.quiz.Quiz;
import ru.otus.spring.quiz.question.Question;
import ru.otus.spring.quiz.question.QuestionParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class QuizAdvancedImpl implements Quiz {

    private final IOService ioService;
    private final QuestionParser questionsParser;
    private final String resourcePath;
    private final int passNum;

    @Override
    public PassageStatus display() {
        List<Question> questions = getQuestions();

        int currentQuizResult = 0;
        for (Question question : questions) {
            ioService.out(question.getQuestionText() + " "
                    + "Выберите ответ: ");
            for (int i = 1; i <= question.getAnswers().size(); i++) {
                ioService.out(i + ". " + question.getAnswers().get(i - 1));
            }
            int choice = Integer.parseInt(ioService.readString());
            if (choice == question.getCorrectAnswerNum()) {
                currentQuizResult++;
            }
        }
        PassageStatus result = currentQuizResult >= passNum ? PassageStatus.SUCCESS : PassageStatus.FAILED;
        String resultString = result == PassageStatus.SUCCESS ? "Успешно" : "Неуспешно";
        ioService.out("Спасибо за ваши ответы, ваш результат: " + resultString);
        return result;
    }

    private List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        try (InputStream inputStream = getClass().getResourceAsStream(resourcePath)) {
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            for (String line; (line = reader.readLine()) != null; ) {
                questions.add(questionsParser.fromLine(line));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return questions;
    }
}