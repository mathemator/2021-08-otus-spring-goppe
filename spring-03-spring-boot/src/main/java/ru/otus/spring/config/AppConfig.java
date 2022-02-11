package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.PersonDao;
import ru.otus.spring.dao.PersonDaoSimple;
import ru.otus.spring.io.IOService;
import ru.otus.spring.io.OpenedConsoleIOService;
import ru.otus.spring.message.MessageProvider;
import ru.otus.spring.quiz.service.QuizService;
import ru.otus.spring.quiz.service.QuizServiceImpl;
import ru.otus.spring.quiz.loading.QuestionParserCsvImpl;
import ru.otus.spring.quiz.loading.QuestionParser;
import ru.otus.spring.quiz.loading.QuestionsLoaderImpl;
import ru.otus.spring.service.PersonService;
import ru.otus.spring.service.PersonServiceImpl;

@Configuration
public class AppConfig {

    @Value("${quiz.resource-path}")
    String resourcePath;

    @Value("${quiz.pass-number}")
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
        return new QuestionParserCsvImpl();
    }

    @Bean
    public QuizService quiz(IOService ioService, MessageProvider messageProvider) {
        return new QuizServiceImpl(ioService, messageProvider, passNumber);
    }

    @Bean
    public QuestionsLoaderImpl questionsLoader(QuestionParser questionParser) {
        return new QuestionsLoaderImpl(questionParser, resourcePath);
    }

    @Bean
    public IOService ioService() {
        return new OpenedConsoleIOService(System.in, System.out);
    }

}
