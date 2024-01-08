package ru.otus.spring.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//import org.springframework.shell.Shell;
import org.springframework.shell.Input;
import org.springframework.shell.InputProvider;
import org.springframework.shell.Shell;
import org.springframework.shell.test.ShellAssertions;
import org.springframework.shell.test.ShellTestClient;
import org.springframework.shell.test.autoconfigure.ShellTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.representation.RepresentationUtil;
import ru.otus.spring.service.LibraryService;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@EnableMongoRepositories
@ShellTest(properties = "spring.shell.command.max-width=120")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ApplicationCommandsTest {


    @Autowired
    ShellTestClient client;

    @MockBean
    LibraryService libraryService;

    @Test
    void testHelp() {
        ShellTestClient.NonInteractiveShellSession session = client
                .nonInterative("help")
                .run();

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen())
                    .containsText("Built-In Commands");
        });
    }

    @Test
    void saveBook() {
        ShellTestClient.NonInteractiveShellSession session = client
                .nonInterative("sb", "1", "NAME", "1", "1")
                .run();

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen())
                    .containsText("the book has been saved");
        });
    }

    @Test
    void saveGenre() {
        ShellTestClient.NonInteractiveShellSession session = client
                .nonInterative("sg", "1", "NAME")
                .run();

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen())
                    .containsText("the genre has been saved");
        });
    }

    @Test
    void saveAuthor() {
        ShellTestClient.NonInteractiveShellSession session = client
                .nonInterative("sa", "1", "NAME")
                .run();

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen())
                    .containsText("the author has been saved");
        });
    }

    @Test
    void deleteBook() {
        ShellTestClient.NonInteractiveShellSession session = client
                .nonInterative("db", "1")
                .run();

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen())
                    .containsText("the book has been deleted");
        });
    }

    @Test
    void deleteAuthor() {
        ShellTestClient.NonInteractiveShellSession session = client
                .nonInterative("da", "1")
                .run();

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen())
                    .containsText("the author has been deleted");
        });
    }

    @Test
    void deleteGenre() {
        ShellTestClient.NonInteractiveShellSession session = client
                .nonInterative("dg", "1")
                .run();

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen())
                    .containsText("the genre has been deleted");
        });
    }

    @Test
    void deleteComment() {
        ShellTestClient.NonInteractiveShellSession session = client
                .nonInterative("dc", "1")
                .run();

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen())
                    .containsText("the comment has been deleted");
        });
    }

    @Test
    void getBook() {
        Book book = new Book("1", "THE CASTLE", new Author("1", "FRANZ KAFKA"), new Genre("1", "NOVEL"));
        Mockito.when(libraryService.getBookById(Mockito.anyString())).thenReturn(Optional.of(book));

        ShellTestClient.NonInteractiveShellSession session = client
                .nonInterative("gb", "1")
                .run();

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen())
                    .containsText(RepresentationUtil.bookView(book).substring(0, 80));
        });

    }

    @Test
    void getBooksByAuthor() {
        Book book1 = new Book("1", "TITLE", new Author("1", "AUTHOR"), new Genre("1", "NOVEL"));
        Book book2 = new Book("1", "TITLE 2", new Author("1", "AUTHOR"), new Genre("1", "NOVEL"));
        List<Book> expectedList = List.of(book1, book2);
        Mockito.when(libraryService.getBooksByAuthor(Mockito.anyString())).thenReturn(expectedList);

        ShellTestClient.NonInteractiveShellSession session = client
                .nonInterative("gba", "AUTHOR")
                .run();

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen())
                    .containsText(RepresentationUtil.bookView(book1).substring(0, 80));
            ShellAssertions.assertThat(session.screen())
                    .containsText(RepresentationUtil.bookView(book2).substring(0, 80));
        });
    }

    @Test
    void getBooksByGenre() {
        Book book1 = new Book("1", "TITLE", new Author("1", "AUTHOR"), new Genre("1", "NOVEL"));
        Book book2 = new Book("1", "TITLE 2", new Author("1", "AUTHOR"), new Genre("1", "NOVEL"));
        List<Book> expectedList = List.of(book1, book2);
        Mockito.when(libraryService.getBooksByGenre(Mockito.anyString())).thenReturn(expectedList);

        ShellTestClient.NonInteractiveShellSession session = client
                .nonInterative("gbg", "NOVEL")
                .run();

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen())
                    .containsText(RepresentationUtil.bookView(book1).substring(0, 80));
            ShellAssertions.assertThat(session.screen())
                    .containsText(RepresentationUtil.bookView(book2).substring(0, 80));
        });
    }

    @Test
    void getAllBooks() {
        Book book1 = new Book("1", "TITLE", new Author("1", "AUTHOR"), new Genre("1", "NOVEL"));
        Book book2 = new Book("1", "TITLE 2", new Author("1", "AUTHOR"), new Genre("1", "NOVEL"));
        List<Book> expectedList = List.of(book1, book2);
        Mockito.when(libraryService.getAllBooks()).thenReturn(expectedList);

        ShellTestClient.NonInteractiveShellSession session = client
                .nonInterative("gab")
                .run();

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen())
                    .containsText(RepresentationUtil.bookView(book1).substring(0, 80));
            ShellAssertions.assertThat(session.screen())
                    .containsText(RepresentationUtil.bookView(book2).substring(0, 80));
        });
    }

    @Test
    void getAllAuthors() {
        Author author1 = new Author("1", "AUTHOR1");
        Author author2 = new Author("2", "AUTHOR2");

        List<Author> expectedList = List.of(author1, author2);
        Mockito.when(libraryService.getAllAuthors()).thenReturn(expectedList);

        ShellTestClient.NonInteractiveShellSession session = client
                .nonInterative("gaa")
                .run();

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen())
                    .containsText(RepresentationUtil.authorView(author1));
            ShellAssertions.assertThat(session.screen())
                    .containsText(RepresentationUtil.authorView(author2));
        });
    }

    @Test
    void getAllGenres() {
        Genre genre1 = new Genre("1", "GENRE1");
        Genre genre2 = new Genre("2", "GENRE2");

        List<Genre> expectedList = List.of(genre1, genre2);
        Mockito.when(libraryService.getAllGenres()).thenReturn(expectedList);
        ShellTestClient.NonInteractiveShellSession session = client
                .nonInterative("gag")
                .run();

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen())
                    .containsText(RepresentationUtil.genreView(genre1));
            ShellAssertions.assertThat(session.screen())
                    .containsText(RepresentationUtil.genreView(genre2));
        });
    }

    @Test
    void getAllComments() {
        Comment comment1 = new Comment("1", "TEST", new Book());
        Comment comment2 = new Comment("2", "TEST2", new Book());

        List<Comment> expectedList = List.of(comment1, comment2);
        Mockito.when(libraryService.getAllComments()).thenReturn(expectedList);

        ShellTestClient.NonInteractiveShellSession session = client
                .nonInterative("gac")
                .run();

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen())
                    .containsText(RepresentationUtil.commentView(comment1));
            ShellAssertions.assertThat(session.screen())
                    .containsText(RepresentationUtil.commentView(comment2));
        });
    }

    @Test
    void getBookComments() {
        Comment comment1 = new Comment("1", "TEST", new Book());
        Comment comment2 = new Comment("2", "TEST2", new Book());

        List<Comment> expectedList = List.of(comment1, comment2);
        Mockito.when(libraryService.getCommentsByBookId(Mockito.anyString())).thenReturn(expectedList);

        ShellTestClient.NonInteractiveShellSession session = client
                .nonInterative("gbc", "1")
                .run();

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen())
                    .containsText(RepresentationUtil.commentView(comment1));
            ShellAssertions.assertThat(session.screen())
                    .containsText(RepresentationUtil.commentView(comment2));
        });
    }
}