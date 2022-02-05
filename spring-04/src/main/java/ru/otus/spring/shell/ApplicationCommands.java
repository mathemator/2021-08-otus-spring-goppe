package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Person;
import ru.otus.spring.message.MessageProvider;
import ru.otus.spring.quiz.Quiz;
import ru.otus.spring.service.PersonService;

import java.util.Scanner;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

    private final MessageProvider messageProvider;
    private final PersonService personService;
    private final Quiz quiz;

    private String userName;
    private Integer age;
    private Scanner scanner = new Scanner(System.in);


    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login() {
        System.out.println(messageProvider.getMessage("strings.enter-name"));
        this.userName = scanner.next();

        System.out.println(messageProvider.getMessage("strings.enter-age"));
        this.age = scanner.nextInt();
        Person user = personService.makeNew(this.userName, age);
        return messageProvider.getMessage("strings.current-user") + ": " + user.getName() + " " +
                messageProvider.getMessage("strings.current-age") + ": " + user.getAge();
    }

    @ShellMethod(value = "Display questions", key = {"q", "quiz"})
    @ShellMethodAvailability(value = "isDisplayAvailable")
    public String display() {
        quiz.display();

        return "Всего доброго!";
    }

    private Availability isDisplayAvailable() {
        return userName == null || age == null ? Availability.unavailable("Сначала залогиньтесь") : Availability.available();
    }
}
