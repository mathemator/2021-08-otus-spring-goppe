package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.JenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Jenre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class LibraryServiceImplTest {

    private BookDao bookDaoMock;
    private AuthorDao authorDaoMock;
    private JenreDao jenreDaoMock;

    private LibraryServiceImpl libraryService;

    @BeforeEach
    public void setUp() {
        bookDaoMock = mock(BookDao.class);
        authorDaoMock = mock(AuthorDao.class);
        jenreDaoMock = mock(JenreDao.class);
        libraryService = new LibraryServiceImpl(authorDaoMock, bookDaoMock, jenreDaoMock);
    }

    @Test
    void getBookById() {
        Book expected = new Book(1, "TEST", 1, 1);
        when(bookDaoMock.getById(anyLong())).thenReturn(expected);
        Book actual = libraryService.getBookById(1);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void addBook() {
        Book expected = new Book(1, "TEST", 1, 1);
        libraryService.addBook(expected);
        verify(bookDaoMock, times(1)).insert(expected);
    }

    @Test
    void getAllBooks() {
        Book book1 = new Book(1, "TEST", 1, 1);
        Book book2 = new Book(2, "TEST2", 1, 1);
        when(bookDaoMock.getAll()).thenReturn(List.of(book1, book2));
        List<Book> actual = libraryService.getAllBooks();
        assertThat(actual)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(book1, book2);
    }

    @Test
    void getBooksByJenre() {
        Book book1 = new Book(1, "TEST", 1, 1);
        Book book2 = new Book(2, "TEST2", 1, 1);
        when(bookDaoMock.getByJenre(anyString())).thenReturn(List.of(book1, book2));
        List<Book> actual = libraryService.getBooksByJenre("TESTY");
        assertThat(actual)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(book1, book2);
    }

    @Test
    void getBookstByAuthor() {
        Book book1 = new Book(1, "TEST", 1, 1);
        Book book2 = new Book(2, "TEST2", 1, 1);
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
    void addJenre() {
        Jenre expected = new Jenre(1, "TEST");
        libraryService.addJenre(expected);
        verify(jenreDaoMock, times(1)).insert(expected);
    }

    @Test
    void getJenreById() {
        Jenre expected = new Jenre(1, "TEST");
        when(jenreDaoMock.getById(anyLong())).thenReturn(expected);
        Jenre actual = libraryService.getJenreById(1);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getAllJenres() {
        Jenre jenre1 = new Jenre(1, "TEST");
        Jenre jenre2 = new Jenre(2, "TEST2");
        when(jenreDaoMock.getAll()).thenReturn(List.of(jenre1, jenre2));
        List<Jenre> actual = libraryService.getAllJenres();
        assertThat(actual)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(jenre1, jenre2);
    }

    @Test
    void removeJenreById() {
        libraryService.removeJenreById(1);
        verify(jenreDaoMock, times(1)).deleteById(1);
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