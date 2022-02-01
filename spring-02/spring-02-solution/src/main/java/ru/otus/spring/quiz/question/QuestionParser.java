package ru.otus.spring.quiz.question;

public interface QuestionParser {

    Question fromLine(String inputLine);
}
