package ru.otus.spring.question.loading;

import ru.otus.spring.question.Question;

import java.util.List;

public interface QuestionsLoader {

    List<Question> loadQuestions();
}
