package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.message.MessageProvider;
import ru.otus.spring.service.QuizService;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

    private final MessageProvider messageProvider;
    private final QuizService quizService;

    @ShellMethod(value = "perform quiz", key = {"q", "quiz"})
    public String performQuiz() {
        quizService.performQuiz();

        return messageProvider.getMessage("strings.farewell");
    }
}
