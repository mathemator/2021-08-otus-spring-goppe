package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.spring.domain.Person;
import ru.otus.spring.message.MessageProvider;
import ru.otus.spring.quiz.Quiz;
import ru.otus.spring.service.PersonService;

import java.util.Scanner;

@SpringBootApplication
public class SpringDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringDemoApplication.class, args);
        MessageProvider messageProvider = ctx.getBean(MessageProvider.class);

        Scanner scanner = new Scanner(System.in);
        PersonService service = ctx.getBean(PersonService.class);

        System.out.println(messageProvider.getMessage("strings.enter-name"));
        String name = scanner.next();
        System.out.println(messageProvider.getMessage("strings.enter-age"));

        int age = scanner.nextInt();

        Person user = service.makeNew(name, age);
        System.out.println(messageProvider.getMessage("strings.current-user") + ": " + user.getName() + " " +
                messageProvider.getMessage("strings.current-age") + ": " + user.getAge());

        Quiz quiz = ctx.getBean(Quiz.class);
        quiz.display();
        ctx.close();
    }

}
