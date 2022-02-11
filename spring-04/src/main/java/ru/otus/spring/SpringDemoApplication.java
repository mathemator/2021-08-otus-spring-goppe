package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.spring.domain.Person;
import ru.otus.spring.message.MessageProvider;
import ru.otus.spring.quiz.service.QuizService;
import ru.otus.spring.quiz.loading.QuestionsLoaderImpl;
import ru.otus.spring.person.PersonService;

import java.util.Scanner;

@SpringBootApplication
public class SpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }


}
