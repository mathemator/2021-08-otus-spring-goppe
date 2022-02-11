package ru.otus.spring.quiz.loading;

import ru.otus.spring.quiz.loading.QuestionParser;
import ru.otus.spring.quiz.question.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionParserCsvImpl implements QuestionParser {
    @Override
    public Question fromLine(String inputLine) {
        if (inputLine == null || inputLine.length() == 0) {
            return null;
        }
        String[] array = inputLine.split(",");
        if (array.length < 4) {
            throw new RuntimeException("incorrect csv line");
        }
        String questionText = array[0];

        int correctAnswerNum = Integer.parseInt(array[1]);

        List<String> answers = new ArrayList<>(Arrays.asList(array).subList(2, array.length));

        return new Question(questionText, answers, correctAnswerNum);
    }
}
