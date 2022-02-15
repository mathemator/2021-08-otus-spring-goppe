package ru.otus.spring.question.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.spring.io.IOService;
import ru.otus.spring.message.MessageProvider;
import ru.otus.spring.question.PassageStatus;
import ru.otus.spring.question.Question;

import java.util.List;

@Component
public class QuestionServiceImpl implements QuestionService {

    private final IOService ioService;
    private final MessageProvider messageProvider;
    private final int passNumber;

    public QuestionServiceImpl(IOService ioService,
                               MessageProvider messageProvider,
                               @Value("${quiz.pass-number}") int passNumber) {
        this.ioService = ioService;
        this.messageProvider = messageProvider;
        this.passNumber = passNumber;
    }

    @Override
    public void runQuestions(List<Question> questions) {
        int currentQuizResult = 0;
        for (Question question : questions) {
            currentQuizResult += askQuestion(question) ? 1 : 0;
        }
        PassageStatus result = currentQuizResult >= passNumber ? PassageStatus.SUCCESS : PassageStatus.FAILED;
        String resultString = result == PassageStatus.SUCCESS ? messageProvider.getMessage("strings.success") :
                messageProvider.getMessage("strings.failed");
        ioService.out(messageProvider.getMessage("strings.answer-thank") + ": " + resultString);
    }

    private boolean askQuestion(Question question) {
        ioService.out(question.getQuestionText() + " "
                + messageProvider.getMessage("strings.choose-answer") + ": ");
        for (int i = 1; i <= question.getAnswers().size(); i++) {
            ioService.out(i + ". " + question.getAnswers().get(i - 1));
        }
        int choice = Integer.parseInt(ioService.readString());
        return choice == question.getCorrectAnswerNum();
    }
}