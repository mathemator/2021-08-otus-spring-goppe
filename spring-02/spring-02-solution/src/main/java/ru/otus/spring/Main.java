package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.quiz.Quiz;
import ru.otus.spring.domain.Person;
import ru.otus.spring.service.PersonService;

import java.util.Scanner;

@Configuration
@ComponentScan
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        Scanner scanner = new Scanner(System.in);
        PersonService service = context.getBean(PersonService.class);
        System.out.println("Enter your name: ");
        String name = scanner.next();
        System.out.println("Enter your age: ");
        int age = scanner.nextInt();

        Person user = service.makeNew(name, age);
        System.out.println("current user: " + user.getName() + " age: " + user.getAge());

        Quiz quiz = context.getBean(Quiz.class);
        quiz.display();
    }
}
