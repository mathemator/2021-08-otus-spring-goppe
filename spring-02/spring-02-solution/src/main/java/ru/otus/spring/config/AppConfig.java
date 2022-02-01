package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.quiz.question.QuestionCsvLineParser;
import ru.otus.spring.quiz.question.QuestionParser;
import ru.otus.spring.quiz.Quiz;
import ru.otus.spring.quiz.advanced.QuizAdvancedImpl;
import ru.otus.spring.dao.PersonDao;
import ru.otus.spring.dao.PersonDaoSimple;
import ru.otus.spring.service.PersonService;
import ru.otus.spring.service.PersonServiceImpl;

import java.io.IOException;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Value("${quiz.resourcePath}")
    String resourcePath;

    @Value("${quiz.passNumber}")
    int passNumber;

    @Bean
    public PersonDao personDao() {
        return new PersonDaoSimple();
    }

    @Bean
    public PersonService personService(PersonDao dao) {
        return new PersonServiceImpl(dao);
    }

    @Bean
    public QuestionParser questionParser() {
        return new QuestionCsvLineParser();
    }

    @Bean
    public Quiz quiz(QuestionCsvLineParser questionParser) throws IOException {
        return new QuizAdvancedImpl(questionParser, resourcePath, passNumber);
    }

}
