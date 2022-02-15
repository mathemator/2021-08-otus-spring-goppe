package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.PersonDao;
import ru.otus.spring.dao.PersonDaoSimple;
import ru.otus.spring.io.IOService;
import ru.otus.spring.io.OpenedConsoleIOService;
import ru.otus.spring.message.MessageProvider;
import ru.otus.spring.question.loading.QuestionsLoader;
import ru.otus.spring.question.service.QuestionService;
import ru.otus.spring.question.service.QuestionServiceImpl;
import ru.otus.spring.question.loading.QuestionParserCsvImpl;
import ru.otus.spring.question.loading.QuestionParser;
import ru.otus.spring.question.loading.QuestionsLoaderImpl;
import ru.otus.spring.service.PersonService;
import ru.otus.spring.service.PersonServiceImpl;
import ru.otus.spring.service.QuizService;
import ru.otus.spring.service.QuizServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public IOService ioService() {
        return new OpenedConsoleIOService(System.in, System.out);
    }

}
