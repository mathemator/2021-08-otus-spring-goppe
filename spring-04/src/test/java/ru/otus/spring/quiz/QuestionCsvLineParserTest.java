package ru.otus.spring.quiz;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.spring.quiz.question.Question;
import ru.otus.spring.quiz.question.QuestionCsvLineParser;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionCsvLineParserTest {

    private QuestionCsvLineParser questionCsvLineParser;

    @BeforeEach
    public void setUp() {
        questionCsvLineParser = new QuestionCsvLineParser();
    }

    @Test
    public void testCsvGoodLineParse() {
        Question question = questionCsvLineParser.fromLine("simple question,1,answer1,answer2");
        assertEquals("simple question", question.getQuestionText(), "text must match");
        assertEquals(1, question.getCorrectAnswerNum(), "correct answer num must match");
        assertEquals("answer1", question.getAnswers().get(0), "first answer must match");
        assertEquals("answer2", question.getAnswers().get(1), "second answer must match");
    }

    @Test
    public void testNullLine() {
        assertNull(questionCsvLineParser.fromLine(null), "null input must return null");
        assertNull(questionCsvLineParser.fromLine(""), "empty input must return null");
    }

    @Test
    public void testIncorrectLine() {
        assertThrows(RuntimeException.class, () -> questionCsvLineParser.fromLine("bla,bla"));
    }

}