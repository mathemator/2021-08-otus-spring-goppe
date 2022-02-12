package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.domain.Person;
import ru.otus.spring.io.IOService;
import ru.otus.spring.message.MessageProvider;
import ru.otus.spring.question.Question;
import ru.otus.spring.question.loading.QuestionsLoader;
import ru.otus.spring.question.loading.QuestionsLoaderImpl;
import ru.otus.spring.question.service.QuestionService;

import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final IOService ioService;
    private final MessageProvider messageProvider;
    private final PersonService personService;
    private final QuestionsLoader questionsLoader;
    private final QuestionService questionService;

    @Override
    public void performQuiz() {

        ioService.out(messageProvider.getMessage("strings.enter-name"));
        String name = ioService.readString();
        ioService.out(messageProvider.getMessage("strings.enter-age"));

        int age =  Integer.parseInt(ioService.readString());

        Person user = personService.makeNew(name, age);
        System.out.println(messageProvider.getMessage("strings.current-user") + ": " + user.getName() + " " +
                messageProvider.getMessage("strings.current-age") + ": " + user.getAge());

        List<Question> questions = questionsLoader.loadQuestions();
        questionService.runQuestions(questions);
    }
}
