package ru.otus.spring.quiz;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.otus.spring.io.IOService;
import ru.otus.spring.message.MessageProvider;
import ru.otus.spring.quiz.advanced.QuizAdvancedImpl;
import ru.otus.spring.quiz.question.Question;
import ru.otus.spring.quiz.question.QuestionParser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuizAdvancedImplTest {

    private QuizAdvancedImpl quizAdvanced;
    private QuestionParser questionParserMock;
    private IOService ioServiceMock;
    private MessageProvider messageProviderMock;


    @Before
    public void setUp() {
        ioServiceMock = Mockito.mock(IOService.class);
        Mockito.when(ioServiceMock.readString()).thenReturn("1");
        messageProviderMock = Mockito.mock(MessageProvider.class);
        questionParserMock = Mockito.mock(QuestionParser.class);
        Mockito.when(questionParserMock.fromLine(Mockito.any())).thenReturn(
                new Question("question?", Arrays.asList("answer1", "answer2"), 1));

    }

    @Test
    public void testSuccessBehaviour() {
        quizAdvanced = new QuizAdvancedImpl(ioServiceMock, questionParserMock, "/test.csv", messageProviderMock, 3);

        PassageStatus display = quizAdvanced.display();
        assertEquals(PassageStatus.SUCCESS, display, "result must be success");
    }

    @Test
    public void testFailBehaviour() {
        quizAdvanced = new QuizAdvancedImpl(ioServiceMock, questionParserMock, "/test.csv", messageProviderMock, 6);

        PassageStatus display = quizAdvanced.display();
        assertEquals(PassageStatus.FAILED, display, "result must be failed");
    }

}