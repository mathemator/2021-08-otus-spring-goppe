package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc bookDao;

    @Test
    void insert() {
        Book expected = new Book(4, "THE PROCESS", new Author(1, "FRANZ KAFKA"), new Genre(1, "NOVEL"));
        bookDao.insert(expected);
        Book actual = bookDao.getById(expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getById() {
        Book expected = new Book(1, "THE CASTLE", new Author(1, "FRANZ KAFKA"), new Genre(1, "NOVEL"));
        Book actual = bookDao.getById(expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void updateById() {
        Book expected = new Book(1, "THE CASTLE", new Author(1, "FRANZ KAFKA"), new Genre(2, "COMEDY"));
        bookDao.updateById(expected);
        Book actual = bookDao.getById(expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getAll() {
        Book expected = new Book(1, "THE CASTLE", new Author(1, "FRANZ KAFKA"), new Genre(1, "NOVEL"));
        Book expected2 = new Book(2, "THE GOVERNMENT INSPECTOR", new Author(2, "NIKOLAY GOGOL"), new Genre(2, "COMEDY"));
        Book expected3 = new Book(3, "DEAD SOULS", new Author(2, "NIKOLAY GOGOL"), new Genre(1, "NOVEL"));
        List<Book> actualAuthorList = bookDao.getAll();
        assertThat(actualAuthorList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expected, expected2, expected3);
    }

    @Test
    void getByGenre() {
        Book expected = new Book(1, "THE CASTLE", new Author(1, "FRANZ KAFKA"), new Genre(1, "NOVEL"));
        Book expected2 = new Book(3, "DEAD SOULS", new Author(2, "NIKOLAY GOGOL"), new Genre(1, "NOVEL"));
        List<Book> actualAuthorList = bookDao.getByGenre("NOVEL");
        assertThat(actualAuthorList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expected, expected2);
    }

    @Test
    void getByAuthor() {
        Book expected = new Book(2, "THE GOVERNMENT INSPECTOR", new Author(2, "NIKOLAY GOGOL"), new Genre(2, "COMEDY"));
        Book expected2 = new Book(3, "DEAD SOULS", new Author(2, "NIKOLAY GOGOL"), new Genre(1, "NOVEL"));
        List<Book> actualAuthorList = bookDao.getByAuthor("NIKOLAY GOGOL");
        assertThat(actualAuthorList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expected, expected2);
    }

    @Test
    void deleteById() {
        assertThatCode(() -> bookDao.getById(3))
                .doesNotThrowAnyException();

        bookDao.deleteById(3);

        assertThatThrownBy(() -> bookDao.getById(3))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}