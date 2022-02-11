package ru.otus.spring.quiz.service;


import lombok.RequiredArgsConstructor;
import ru.otus.spring.io.IOService;
import ru.otus.spring.message.MessageProvider;
import ru.otus.spring.quiz.PassageStatus;
import ru.otus.spring.quiz.question.Question;

import java.util.List;

@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final IOService ioService;
    private final MessageProvider messageProvider;
    private final int passNum;

    @Override
    public PassageStatus test(List<Question> questions) {
        int currentQuizResult = 0;
        for (Question question : questions) {
            currentQuizResult += askQuestion(question) ? 1 : 0;
        }
        PassageStatus result = currentQuizResult >= passNum ? PassageStatus.SUCCESS : PassageStatus.FAILED;
        String resultString = result == PassageStatus.SUCCESS ? messageProvider.getMessage("strings.success") :
                messageProvider.getMessage("strings.failed");
        ioService.out(messageProvider.getMessage("strings.answer-thank") + ": " + resultString);
        return result;
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