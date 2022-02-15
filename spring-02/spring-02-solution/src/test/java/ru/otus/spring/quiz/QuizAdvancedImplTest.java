package ru.otus.spring.quiz;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
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

    @Before
    public void setUp() {

        questionParserMock = Mockito.mock(QuestionParser.class);
        Mockito.when(questionParserMock.fromLine(Mockito.any())).thenReturn(
                new Question("question?", Arrays.asList("answer1", "answer2"), 1));

    }

    @Test
    public void testSuccessBehaviour() throws IOException {
        quizAdvanced = new QuizAdvancedImpl(questionParserMock, "/test.csv", 3);

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
        quizAdvanced = new QuizAdvancedImpl(questionParserMock, "/test.csv", 6);

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