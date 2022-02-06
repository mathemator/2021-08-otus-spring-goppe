package ru.otus.spring;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = SpringApplication.run(Main.class);

        BookDao bookDao = context.getBean(BookDao.class);
        List<Book> novel = bookDao.getByJenre("NOVEL");
        System.out.println(novel.stream().map(Book::toString).collect(Collectors.joining("\n")));

        Console.main(args);
    }
}
