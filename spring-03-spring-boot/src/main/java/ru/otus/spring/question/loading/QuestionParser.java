package ru.otus.spring.question.loading;

import ru.otus.spring.question.Question;

public interface QuestionParser {

    Question fromLine(String inputLine);
}
