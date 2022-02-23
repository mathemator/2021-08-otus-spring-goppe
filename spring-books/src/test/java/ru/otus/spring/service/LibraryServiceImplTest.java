package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.CommentRepository;
import ru.otus.spring.repository.GenreRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class LibraryServiceImplTest {

    @MockBean
    private BookRepository bookRepositoryMock;
    @MockBean
    private AuthorRepository authorRepositoryMock;
    @MockBean
    private GenreRepository genreRepositoryMock;
    @MockBean
    private CommentRepository commentRepositoryMock;

    @Autowired
    private LibraryServiceImpl libraryService;

    @Test
    void getBookById() {
        Optional<Book> expected = Optional.of(new Book(1, "THE CASTLE", new Author(1, "FRANZ KAFKA"), new Genre(1, "NOVEL")));
        when(bookRepositoryMock.findById(anyLong())).thenReturn(expected);
        Optional<Book> actual = libraryService.getBookById(1);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void saveBook() {
        Optional<Author> testAuthor = Optional.of(new Author(1, "TEST_AUTHOR"));
        Optional<Genre> testGenre = Optional.of(new Genre(1, "TEST_GENRE"));
        Book expected = new Book(0, "TEST", testAuthor.get(), testGenre.get());
        when(authorRepositoryMock.findById(anyLong())).thenReturn(testAuthor);
        when(genreRepositoryMock.findById(anyLong())).thenReturn(testGenre);
        Book actual = libraryService.saveBook(0, "TEST", 1, 1);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        verify(bookRepositoryMock, times(1)).save(expected);
    }

    @Test
    void getAllBooks() {
        Book book1 = new Book(1, "TEST", new Author(1, "TEST_AUTHOR"), new Genre(1, "TEST_GENRE"));
        Book book2 = new Book(2, "TEST2", new Author(2, "TEST_AUTHOR"), new Genre(1, "TEST_GENRE"));
        when(bookRepositoryMock.findAll()).thenReturn(List.of(book1, book2));
        List<Book> actual = libraryService.getAllBooks();
        assertThat(actual)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(book1, book2);
    }

    @Test
    void getBooksByGenre() {
        Book book1 = new Book(1, "TEST", new Author(2, "TEST_AUTHOR"), new Genre(1, "TEST_GENRE"));
        Book book2 = new Book(2, "TEST2", new Author(2, "TEST_AUTHOR"), new Genre(1, "TEST_GENRE"));
        when(bookRepositoryMock.findByGenreName(anyString())).thenReturn(List.of(book1, book2));
        List<Book> actual = libraryService.getBooksByGenre("TESTY");
        assertThat(actual)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(book1, book2);
    }

    @Test
    void getBookstByAuthor() {
        Book book1 = new Book(1, "TEST", new Author(2, "TEST_AUTHOR"), new Genre(1, "TEST_GENRE"));
        Book book2 = new Book(2, "TEST2", new Author(2, "TEST_AUTHOR"), new Genre(1, "TEST_GENRE"));
        when(bookRepositoryMock.findByAuthorName(anyString())).thenReturn(List.of(book1, book2));
        List<Book> actual = libraryService.getBooksByAuthor("TESTY");
        assertThat(actual)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(book1, book2);
    }

    @Test
    void deleteBookById() {
        libraryService.deleteBookById(1);
        verify(bookRepositoryMock, times(1)).deleteById(1);
    }

    @Test
    void saveGenre() {
        Genre expected = new Genre(1, "TEST");
        Genre actual = libraryService.saveGenre(1, "TEST");
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        verify(genreRepositoryMock, times(1)).save(expected);
    }

    @Test
    void getGenreById() {
        Optional<Genre> expected = Optional.of(new Genre(1, "TEST"));
        when(genreRepositoryMock.findById(anyLong())).thenReturn(expected);
        Optional<Genre> actual = libraryService.getGenreById(1);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getAllGenres() {
        Genre genre1 = new Genre(1, "TEST");
        Genre genre2 = new Genre(2, "TEST2");
        when(genreRepositoryMock.findAll()).thenReturn(List.of(genre1, genre2));
        List<Genre> actual = libraryService.getAllGenres();
        assertThat(actual)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(genre1, genre2);
    }

    @Test
    void deleteGenreById() {
        libraryService.deleteGenreById(1);
        verify(genreRepositoryMock, times(1)).deleteById(1);
    }

    @Test
    void saveAuthor() {
        Author expected = new Author(1, "test");
        Author actual = libraryService.saveAuthor(1, "test");
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        verify(authorRepositoryMock, times(1)).save(expected);
    }

    @Test
    void getAuthorById() {
        Optional<Author> expected = Optional.of(new Author(1, "TEST"));
        when(authorRepositoryMock.findById(anyLong())).thenReturn(expected);
        Optional<Author> actual = libraryService.getAuthorById(1);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getAllAuthors() {
        Author author1 = new Author(1, "TEST");
        Author author2 = new Author(2, "TEST2");
        when(authorRepositoryMock.findAll()).thenReturn(List.of(author1, author2));
        List<Author> actual = libraryService.getAllAuthors();
        assertThat(actual)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(author1, author2);
    }

    @Test
    void deleteAuthorById() {
        libraryService.deleteAuthorById(1);
        verify(authorRepositoryMock, times(1)).deleteById(1);
    }



    @Test
    void saveComment() {
        Book book1 = new Book(1, "TEST", new Author(2, "TEST_AUTHOR"), new Genre(1, "TEST_GENRE"));
        Comment expected = new Comment(1, "TEST", book1);
        when(bookRepositoryMock.findById(anyLong())).thenReturn(Optional.of(book1));
        Comment actual = libraryService.saveComment(1, "TEST", 1);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        verify(commentRepositoryMock, times(1)).save(expected);
    }

    @Test
    void getCommentById() {
        Optional<Comment> expected = Optional.of(new Comment(1, "TEST", new Book()));
        when(commentRepositoryMock.findById(anyLong())).thenReturn(expected);
        Optional<Comment> actual = libraryService.getCommentById(1);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void deleteCommentById() {
        libraryService.deleteCommentById(1);
        verify(commentRepositoryMock, times(1)).deleteById(1);
    }

    @Test
    void getCommentsByBookId() {
        Comment comment1 = new Comment(1, "TEST", new Book());
        Comment comment2 = new Comment(2, "TEST2", new Book());
        when(commentRepositoryMock.findByBookId(Mockito.anyLong())).thenReturn(List.of(comment1, comment2));
        List<Comment> actual = libraryService.getCommentsByBookId(1);
        assertThat(actual)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(comment1, comment2);
    }
}