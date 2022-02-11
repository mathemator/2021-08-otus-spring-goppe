package ru.otus.spring.quiz.loading;

import ru.otus.spring.quiz.question.Question;

public interface QuestionParser {

    Question fromLine(String inputLine);
}
