package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class LibraryServiceImplTest {

    @MockBean
    private BookDao bookDaoMock;
    @MockBean
    private AuthorDao authorDaoMock;
    @MockBean
    private GenreDao genreDaoMock;

    @Autowired
    private LibraryServiceImpl libraryService;

    @Test
    void getBookById() {
        Book expected = new Book(1, "TEST", new Author(1, "TEST_AUTHOR"), new Genre(1, "TEST_GENRE"));
        when(bookDaoMock.getById(anyLong())).thenReturn(expected);
        Book actual = libraryService.getBookById(1);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void addBook() {
        Author testAuthor = new Author(1, "TEST_AUTHOR");
        Genre testGenre = new Genre(1, "TEST_GENRE");
        Book expected = new Book(0, "TEST", testAuthor, testGenre);
        when(authorDaoMock.getById(anyLong())).thenReturn(testAuthor);
        when(genreDaoMock.getById(anyLong())).thenReturn(testGenre);
        libraryService.addBook("TEST", 1, 1);
        verify(bookDaoMock, times(1)).insert(expected);
    }

    @Test
    void getAllBooks() {
        Book book1 = new Book(1, "TEST", new Author(1, "TEST_AUTHOR"), new Genre(1, "TEST_GENRE"));
        Book book2 = new Book(2, "TEST2", new Author(2, "TEST_AUTHOR"), new Genre(1, "TEST_GENRE"));
        when(bookDaoMock.getAll()).thenReturn(List.of(book1, book2));
        List<Book> actual = libraryService.getAllBooks();
        assertThat(actual)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(book1, book2);
    }

    @Test
    void getBooksByGenre() {
        Book book1 = new Book(1, "TEST", new Author(2, "TEST_AUTHOR"), new Genre(1, "TEST_GENRE"));
        Book book2 = new Book(2, "TEST2", new Author(2, "TEST_AUTHOR"), new Genre(1, "TEST_GENRE"));
        when(bookDaoMock.getByGenre(anyString())).thenReturn(List.of(book1, book2));
        List<Book> actual = libraryService.getBooksByGenre("TESTY");
        assertThat(actual)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(book1, book2);
    }

    @Test
    void getBookstByAuthor() {
        Book book1 = new Book(1, "TEST", new Author(2, "TEST_AUTHOR"), new Genre(1, "TEST_GENRE"));
        Book book2 = new Book(2, "TEST2", new Author(2, "TEST_AUTHOR"), new Genre(1, "TEST_GENRE"));
        when(bookDaoMock.getByAuthor(anyString())).thenReturn(List.of(book1, book2));
        List<Book> actual = libraryService.getBooksByAuthor("TESTY");
        assertThat(actual)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(book1, book2);
    }

    @Test
    void removeBookById() {
        libraryService.removeBookById(1);
        verify(bookDaoMock, times(1)).deleteById(1);
    }

    @Test
    void addGenre() {
        Genre expected = new Genre(1, "TEST");
        libraryService.addGenre(expected);
        verify(genreDaoMock, times(1)).insert(expected);
    }

    @Test
    void getGenreById() {
        Genre expected = new Genre(1, "TEST");
        when(genreDaoMock.getById(anyLong())).thenReturn(expected);
        Genre actual = libraryService.getGenreById(1);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getAllGenres() {
        Genre genre1 = new Genre(1, "TEST");
        Genre genre2 = new Genre(2, "TEST2");
        when(genreDaoMock.getAll()).thenReturn(List.of(genre1, genre2));
        List<Genre> actual = libraryService.getAllGenres();
        assertThat(actual)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(genre1, genre2);
    }

    @Test
    void removeGenreById() {
        libraryService.removeGenreById(1);
        verify(genreDaoMock, times(1)).deleteById(1);
    }

    @Test
    void addAuthor() {
        Author expected = new Author(1, "test");
        libraryService.addAuthor(expected);
        verify(authorDaoMock, times(1)).insert(expected);
    }

    @Test
    void getAuthorById() {
        Author expected = new Author(1, "TEST");
        when(authorDaoMock.getById(anyLong())).thenReturn(expected);
        Author actual = libraryService.getAuthorById(1);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getAllAuthors() {
        Author author1 = new Author(1, "TEST");
        Author author2 = new Author(2, "TEST2");
        when(authorDaoMock.getAll()).thenReturn(List.of(author1, author2));
        List<Author> actual = libraryService.getAllAuthors();
        assertThat(actual)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(author1, author2);
    }

    @Test
    void removeAuthorById() {
        libraryService.removeAuthorById(1);
        verify(authorDaoMock, times(1)).deleteById(1);
    }
}