package ru.otus.spring.quiz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
    private MessageProvider messageProvider;

    @BeforeEach
    public void setUp() {

        questionParserMock = Mockito.mock(QuestionParser.class);
        Mockito.when(questionParserMock.fromLine(Mockito.any())).thenReturn(
                new Question("question?", Arrays.asList("answer1", "answer2"), 1));
        messageProvider = Mockito.mock(MessageProvider.class);
    }

    @Test
    public void testSuccessBehaviour() throws IOException {
        quizAdvanced = new QuizAdvancedImpl(questionParserMock, "/test.csv", messageProvider, 3);

        String input = "1\n" +
                "1\n" +
                "1\n" +
                "1\n" +
                "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        PassageStatus display = quizAdvanced.display();
        assertEquals(PassageStatus.SUCCESS, display, "result must be success");
    }

    @Test
    public void testFailBehaviour() throws IOException {
        quizAdvanced = new QuizAdvancedImpl(questionParserMock, "/test.csv", messageProvider, 6);

        String input = "1\n" +
                "1\n" +
                "1\n" +
                "1\n" +
                "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        PassageStatus display = quizAdvanced.display();
        assertEquals(PassageStatus.FAILED, display, "result must be failed");
    }

}