package ru.otus.spring;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.CommentRepository;

import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Main.class);
//        Console.main();
    }
}
