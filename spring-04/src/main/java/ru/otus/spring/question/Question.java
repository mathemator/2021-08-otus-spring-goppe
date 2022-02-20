package ru.otus.spring.question;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class Question {

    private final String questionText;
    private final List<String> answers;
    private final int correctAnswerNum;

}
