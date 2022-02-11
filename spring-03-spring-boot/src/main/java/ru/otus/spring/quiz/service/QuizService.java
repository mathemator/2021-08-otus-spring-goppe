package ru.otus.spring.quiz.service;

import ru.otus.spring.quiz.PassageStatus;
import ru.otus.spring.quiz.question.Question;

import java.util.List;

public interface QuizService {
    PassageStatus test(List<Question> questions);
}
