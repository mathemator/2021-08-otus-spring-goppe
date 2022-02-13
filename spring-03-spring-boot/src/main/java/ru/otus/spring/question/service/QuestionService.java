package ru.otus.spring.question.service;

import ru.otus.spring.question.PassageStatus;
import ru.otus.spring.question.Question;

import java.util.List;

public interface QuestionService {
    void runQuestions(List<Question> questions);
}
