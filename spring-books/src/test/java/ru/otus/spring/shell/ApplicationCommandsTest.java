package ru.otus.spring.shell;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Shell;
import ru.otus.spring.service.LibraryService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest({"spring.main.allow-bean-definition-overriding=true"})
class ApplicationCommandsTest {

    @Autowired
    private Shell shell;

    @MockBean
    private LibraryService libraryService;

    @Test
    void createBook() {
        String res = (String) shell.evaluate(() -> "cb NAME 1 1");
        assertThat(res).isEqualTo("the book has been added");
    }

    @Test
    void deleteBook() {
    }

    @Test
    void getBook() {
    }

    @Test
    void updateBook() {
    }

    @Test
    void getBooksByAuthor() {
    }

    @Test
    void getBooksByGenre() {
    }
}