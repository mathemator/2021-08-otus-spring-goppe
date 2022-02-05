package ru.otus.spring.quiz.advanced;


import ru.otus.spring.message.MessageProvider;
import ru.otus.spring.quiz.PassageStatus;
import ru.otus.spring.quiz.Quiz;
import ru.otus.spring.quiz.question.Question;
import ru.otus.spring.quiz.question.QuestionParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizAdvancedImpl implements Quiz {

    private final QuestionParser questionsParser;
    private final MessageProvider messageProvider;
    private final int passNum;

    private List<Question> questions;
    private int currentQuizResult;


    public QuizAdvancedImpl(QuestionParser questionsParser, String resourcePath,
                            MessageProvider messageProvider, int passNum) throws IOException {
        this.questionsParser = questionsParser;
        this.messageProvider = messageProvider;
        this.passNum = passNum;

        questions = new ArrayList<>();
        InputStream inputStream = getClass().getResourceAsStream(resourcePath);
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        for (String line; (line = reader.readLine()) != null; ) {
            questions.add(questionsParser.fromLine(line));
        }
    }

    @Override
    public PassageStatus display() {
        currentQuizResult = 0;
        Scanner scanner = new Scanner(System.in);
        for (Question question : questions) {
            System.out.println(messageProvider.getMessage(question.getQuestionText()) + " "
                    + messageProvider.getMessage("strings.choose-answer") + ": ");
            for (int i = 1; i <= question.getAnswers().size(); i++) {
                System.out.println(i + ". " + messageProvider.getMessage(question.getAnswers().get(i - 1)));
            }
            int choice = scanner.nextInt();
            if (choice == question.getCorrectAnswerNum()) {
                currentQuizResult++;
            }
        }
        PassageStatus result = currentQuizResult >= passNum ? PassageStatus.SUCCESS : PassageStatus.FAILED;
        System.out.println(messageProvider.getMessage("strings.answer-thank") + ": " +
                messageProvider.getMessage("strings." + result.toString().toLowerCase()));
        return result;
    }

}
