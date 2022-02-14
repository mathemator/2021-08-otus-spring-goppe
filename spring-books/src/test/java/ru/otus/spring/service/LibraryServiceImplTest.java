package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.GenreRepository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import javax.swing.text.html.Option;
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

    @Autowired
    private LibraryServiceImpl libraryService;

    @Test
    void getBookById() {
        Optional<Book> expected = Optional.of(new Book(1, "THE CASTLE", new Author(1, "FRANZ KAFKA"), new Genre(1, "NOVEL"), new ArrayList<>()));
        when(bookRepositoryMock.getById(anyLong())).thenReturn(expected);
        Optional<Book> actual = libraryService.getBookById(1);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void addBook() {
        Optional<Author> testAuthor = Optional.of(new Author(1, "TEST_AUTHOR"));
        Optional<Genre> testGenre = Optional.of(new Genre(1, "TEST_GENRE"));
        Book expected = new Book(0, "TEST", testAuthor.get(), testGenre.get(), Collections.emptyList());
        when(authorRepositoryMock.getById(anyLong())).thenReturn(testAuthor);
        when(genreRepositoryMock.getById(anyLong())).thenReturn(testGenre);
        libraryService.addBook("TEST", 1, 1);
        verify(bookRepositoryMock, times(1)).save(expected);
    }

    @Test
    void getAllBooks() {
        Book book1 = new Book(1, "TEST", new Author(1, "TEST_AUTHOR"), new Genre(1, "TEST_GENRE"), Collections.emptyList());
        Book book2 = new Book(2, "TEST2", new Author(2, "TEST_AUTHOR"), new Genre(1, "TEST_GENRE"), Collections.emptyList());
        when(bookRepositoryMock.getAll()).thenReturn(List.of(book1, book2));
        List<Book> actual = libraryService.getAllBooks();
        assertThat(actual)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(book1, book2);
    }

    @Test
    void getBooksByGenre() {
        Book book1 = new Book(1, "TEST", new Author(2, "TEST_AUTHOR"), new Genre(1, "TEST_GENRE"), Collections.emptyList());
        Book book2 = new Book(2, "TEST2", new Author(2, "TEST_AUTHOR"), new Genre(1, "TEST_GENRE"), Collections.emptyList());
        when(bookRepositoryMock.getByGenre(anyString())).thenReturn(List.of(book1, book2));
        List<Book> actual = libraryService.getBooksByGenre("TESTY");
        assertThat(actual)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(book1, book2);
    }

    @Test
    void getBookstByAuthor() {
        Book book1 = new Book(1, "TEST", new Author(2, "TEST_AUTHOR"), new Genre(1, "TEST_GENRE"), Collections.emptyList());
        Book book2 = new Book(2, "TEST2", new Author(2, "TEST_AUTHOR"), new Genre(1, "TEST_GENRE"), Collections.emptyList());
        when(bookRepositoryMock.getByAuthor(anyString())).thenReturn(List.of(book1, book2));
        List<Book> actual = libraryService.getBooksByAuthor("TESTY");
        assertThat(actual)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(book1, book2);
    }

    @Test
    void removeBookById() {
        libraryService.removeBookById(1);
        verify(bookRepositoryMock, times(1)).deleteById(1);
    }

    @Test
    void addGenre() {
        Genre expected = new Genre(1, "TEST");
        libraryService.addGenre(expected);
        verify(genreRepositoryMock, times(1)).save(expected);
    }

    @Test
    void getGenreById() {
//        Genre expected = new Genre(1, "TEST");
//        when(genreRepositoryMock.getById(anyLong())).thenReturn(expected);
//        Genre actual = libraryService.getGenreById(1);
//        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getAllGenres() {
        Genre genre1 = new Genre(1, "TEST");
        Genre genre2 = new Genre(2, "TEST2");
        when(genreRepositoryMock.getAll()).thenReturn(List.of(genre1, genre2));
        List<Genre> actual = libraryService.getAllGenres();
        assertThat(actual)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(genre1, genre2);
    }

    @Test
    void removeGenreById() {
        libraryService.removeGenreById(1);
        verify(genreRepositoryMock, times(1)).deleteById(1);
    }

    @Test
    void addAuthor() {
        Author expected = new Author(1, "test");
        libraryService.addAuthor(expected);
        verify(authorRepositoryMock, times(1)).save(expected);
    }

    @Test
    void getAuthorById() {
        Optional<Author> expected = Optional.of(new Author(1, "TEST"));
        when(authorRepositoryMock.getById(anyLong())).thenReturn(expected);
        Optional<Author> actual = libraryService.getAuthorById(1);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getAllAuthors() {
        Author author1 = new Author(1, "TEST");
        Author author2 = new Author(2, "TEST2");
        when(authorRepositoryMock.getAll()).thenReturn(List.of(author1, author2));
        List<Author> actual = libraryService.getAllAuthors();
        assertThat(actual)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(author1, author2);
    }

    @Test
    void removeAuthorById() {
        libraryService.removeAuthorById(1);
        verify(authorRepositoryMock, times(1)).deleteById(1);
    }
}