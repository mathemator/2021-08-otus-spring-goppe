package ru.otus.spring.quiz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.message.MessageProvider;
import ru.otus.spring.quiz.advanced.QuizAdvancedImpl;
import ru.otus.spring.quiz.question.Question;
import ru.otus.spring.quiz.question.QuestionParser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class QuizAdvancedImplTest {

    @MockBean
    private QuestionParser questionParser;

    @Autowired
    private Quiz quizAdvanced;

    @Test
    public void testSuccessBehaviour() {

        Mockito.when(questionParser.fromLine(Mockito.any())).thenReturn(
                new Question("question?", Arrays.asList("answer1", "answer2"), 1));

        String input = "1\n" +
                "1\n" +
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
    public void testFailBehaviour() {

        Mockito.when(questionParser.fromLine(Mockito.any())).thenReturn(
                new Question("question?", Arrays.asList("answer1", "answer2"), 2));

        String input = "1\n" +
                "1\n" +
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