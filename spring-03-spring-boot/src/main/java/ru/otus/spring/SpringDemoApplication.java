package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.spring.domain.Person;
import ru.otus.spring.message.MessageProvider;
import ru.otus.spring.question.service.QuestionService;
import ru.otus.spring.question.loading.QuestionsLoaderImpl;
import ru.otus.spring.service.PersonService;
import ru.otus.spring.service.QuizService;

import java.util.Scanner;

@SpringBootApplication
public class SpringDemoApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(SpringDemoApplication.class, args);
        QuizService quizService = ctx.getBean(QuizService.class);
        quizService.performQuiz();
        ctx.close();
    }


}
