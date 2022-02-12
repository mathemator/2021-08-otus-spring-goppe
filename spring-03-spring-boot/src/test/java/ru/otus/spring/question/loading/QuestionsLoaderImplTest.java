package ru.otus.spring.question.loading;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.spring.question.Question;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionsLoaderImplTest {

    private QuestionParser questionParserMock;

    @BeforeEach
    public void setUp() {
        questionParserMock = Mockito.mock(QuestionParser.class);
    }

    @Test
    void loadQuestions() {
        Question que = new Question("question?", Arrays.asList("answer1", "answer2"), 1);
        Mockito.when(questionParserMock.fromLine(Mockito.anyString())).thenReturn(que);
        QuestionsLoaderImpl questionsLoader = new QuestionsLoaderImpl(questionParserMock, "/test.csv");
        List<Question> questionList = questionsLoader.loadQuestions();

        assertThat(questionList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(que, que, que, que, que);
    }
}