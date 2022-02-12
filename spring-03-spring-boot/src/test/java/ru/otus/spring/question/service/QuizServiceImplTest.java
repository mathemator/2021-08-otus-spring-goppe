package ru.otus.spring.question.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.spring.io.IOService;
import ru.otus.spring.message.MessageProvider;
import ru.otus.spring.question.PassageStatus;
import ru.otus.spring.question.Question;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuizServiceImplTest {

    private IOService ioServiceMock;
    private QuestionServiceImpl quizAdvanced;
    private MessageProvider messageProvider;

    @BeforeEach
    public void setUp() {
        ioServiceMock = Mockito.mock(IOService.class);
        Mockito.when(ioServiceMock.readString()).thenReturn("1");

        messageProvider = Mockito.mock(MessageProvider.class);
    }

    @Test
    public void testSuccessBehaviour() {
        quizAdvanced = new QuestionServiceImpl(ioServiceMock, messageProvider, 3);
        Question question = new Question("question?", Arrays.asList("answer1", "answer2"), 1);
        List<Question> questionList = List.of(question, question, question, question, question);

        PassageStatus display = quizAdvanced.runQuestions(questionList);
        assertEquals(PassageStatus.SUCCESS, display, "result must be success");
    }

    @Test
    public void testFailBehaviour() {
        quizAdvanced = new QuestionServiceImpl(ioServiceMock, messageProvider, 3);
        Question question = new Question("question?", Arrays.asList("answer1", "answer2"), 2);
        List<Question> questionList = List.of(question, question, question, question, question);

        PassageStatus display = quizAdvanced.runQuestions(questionList);
        assertEquals(PassageStatus.FAILED, display, "result must be failed");
    }

}