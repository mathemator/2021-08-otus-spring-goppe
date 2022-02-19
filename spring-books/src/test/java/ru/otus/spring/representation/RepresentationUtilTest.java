package ru.otus.spring.representation;

import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class RepresentationUtilTest {

    @Test
    void bookView() {
        Book book = new Book(1, "TEST", new Author(1, "TEST_AUTHOR"), new Genre(1, "TEST_GENRE"));
        assertEquals("id: 1, title: TEST, authorId: 1, authorName: TEST_AUTHOR, genreId: 1, genreName: TEST_GENRE",
                RepresentationUtil.bookView(book));
    }

    @Test
    void authorView() {
        Author author = new Author(1, "TEST_AUTHOR");
        assertEquals("authorId: 1, authorName: TEST_AUTHOR",
                RepresentationUtil.authorView(author));
    }

    @Test
    void genreView() {
        Genre genre = new Genre(1, "TEST_GENRE");
        assertEquals("genreId: 1, genreName: TEST_GENRE",
                RepresentationUtil.genreView(genre));
    }
}