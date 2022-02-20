package ru.otus.spring.shell;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.shell.Shell;
import org.springframework.shell.Shell;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.representation.RepresentationUtil;
import ru.otus.spring.service.LibraryService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest({"spring.main.allow-bean-definition-overriding=true"})
class ApplicationCommandsTest {

    @Autowired
    private Shell shell;

    @MockBean
    private LibraryService libraryService;

    @Test
    void saveBook() {
        String res = (String) shell.evaluate(() -> "sb 1 NAME 1 1");
        assertThat(res).isEqualTo("the book has been saved");
    }

    @Test
    void saveGenre() {
        String res = (String) shell.evaluate(() -> "sg 1 NAME");
        assertThat(res).isEqualTo("the genre has been saved");
    }

    @Test
    void saveAuthor() {
        String res = (String) shell.evaluate(() -> "sa 1 NAME");
        assertThat(res).isEqualTo("the author has been saved");
    }

    @Test
    void deleteBook() {
        String res = (String) shell.evaluate(() -> "db 1");
        assertThat(res).isEqualTo("the book has been deleted");
    }

    @Test
    void deleteAuthor() {
        String res = (String) shell.evaluate(() -> "da 1");
        assertThat(res).isEqualTo("the author has been deleted");
    }

    @Test
    void deleteGenre() {
        String res = (String) shell.evaluate(() -> "dg 1");
        assertThat(res).isEqualTo("the genre has been deleted");
    }

    @Test
    void deleteComment() {
        String res = (String) shell.evaluate(() -> "dc 1");
        assertThat(res).isEqualTo("the comment has been deleted");
    }

    @Test
    void getBook() {
        Book book = new Book(1, "THE CASTLE", new Author(1, "FRANZ KAFKA"), new Genre(1, "NOVEL"), Collections.emptyList());
        Mockito.when(libraryService.getBookById(Mockito.anyLong())).thenReturn(Optional.of(book));
        String res = (String) shell.evaluate(() -> "gb 1");
        assertThat(res).isEqualTo(RepresentationUtil.bookView(book));
    }

    @Test
    void getBooksByAuthor() {
        Book book1 = new Book(1, "TITLE", new Author(1, "AUTHOR"), new Genre(1, "NOVEL"), Collections.emptyList());
        Book book2 = new Book(1, "TITLE 2", new Author(1, "AUTHOR"), new Genre(1, "NOVEL"), Collections.emptyList());
        List<Book> expectedList = List.of(book1, book2);
        Mockito.when(libraryService.getBooksByAuthor(Mockito.anyString())).thenReturn(expectedList);
        String res = (String) shell.evaluate(() -> "gba AUTHOR");
        assertThat(res).isEqualTo(
                expectedList.stream().map(RepresentationUtil::bookView).collect(Collectors.joining("\n")));
    }

    @Test
    void getBooksByGenre() {
        Book book1 = new Book(1, "TITLE", new Author(1, "AUTHOR"), new Genre(1, "NOVEL"), Collections.emptyList());
        Book book2 = new Book(1, "TITLE 2", new Author(1, "AUTHOR"), new Genre(1, "NOVEL"), Collections.emptyList());
        List<Book> expectedList = List.of(book1, book2);
        Mockito.when(libraryService.getBooksByGenre(Mockito.anyString())).thenReturn(expectedList);
        String res = (String) shell.evaluate(() -> "gbg GENRE");
        assertThat(res).isEqualTo(
                expectedList.stream().map(RepresentationUtil::bookView).collect(Collectors.joining("\n")));
    }

    @Test
    void getAllBooks() {
        Book book1 = new Book(1, "TITLE", new Author(1, "AUTHOR"), new Genre(1, "NOVEL"), Collections.emptyList());
        Book book2 = new Book(1, "TITLE 2", new Author(1, "AUTHOR"), new Genre(1, "NOVEL"), Collections.emptyList());
        List<Book> expectedList = List.of(book1, book2);
        Mockito.when(libraryService.getAllBooks()).thenReturn(expectedList);
        String res = (String) shell.evaluate(() -> "gab");
        assertThat(res).isEqualTo(
                expectedList.stream().map(RepresentationUtil::bookView).collect(Collectors.joining("\n")));
    }

    @Test
    void getAllAuthors() {
        Author author1 = new Author(1, "AUTHOR1");
        Author author2 = new Author(2, "AUTHOR2");

        List<Author> expectedList = List.of(author1, author2);
        Mockito.when(libraryService.getAllAuthors()).thenReturn(expectedList);
        String res = (String) shell.evaluate(() -> "gaa");
        assertThat(res).isEqualTo(
                expectedList.stream().map(RepresentationUtil::authorView).collect(Collectors.joining("\n")));
    }

    @Test
    void getAllGenres() {
        Genre genre1 = new Genre(1, "GENRE1");
        Genre genre2 = new Genre(2, "GENRE2");

        List<Genre> expectedList = List.of(genre1, genre2);
        Mockito.when(libraryService.getAllGenres()).thenReturn(expectedList);
        String res = (String) shell.evaluate(() -> "gag");
        assertThat(res).isEqualTo(
                expectedList.stream().map(RepresentationUtil::genreView).collect(Collectors.joining("\n")));
    }

    @Test
    void getAllComments() {
        Comment comment1 = new Comment(1, "TEST", new Book());
        Comment comment2 = new Comment(2, "TEST2", new Book());

        List<Comment> expectedList = List.of(comment1, comment2);
        Mockito.when(libraryService.getAllComments()).thenReturn(expectedList);
        String res = (String) shell.evaluate(() -> "gac");
        assertThat(res).isEqualTo(
                expectedList.stream().map(RepresentationUtil::commentView).collect(Collectors.joining("\n")));
    }

    @Test
    void getBookComments() {
        Comment comment1 = new Comment(1, "TEST", new Book());
        Comment comment2 = new Comment(2, "TEST2", new Book());

        List<Comment> expectedList = List.of(comment1, comment2);
        Mockito.when(libraryService.getCommentsByBookId(Mockito.anyLong())).thenReturn(expectedList);
        String res = (String) shell.evaluate(() -> "gbc 1");
        assertThat(res).isEqualTo(
                expectedList.stream().map(RepresentationUtil::commentView).collect(Collectors.joining("\n")));
    }
}