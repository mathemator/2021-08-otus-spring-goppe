package ru.otus.spring.quiz.loading;

import ru.otus.spring.quiz.question.Question;

import java.util.List;

public interface QuestionsLoader {

    List<Question> loadQuestions();
}
