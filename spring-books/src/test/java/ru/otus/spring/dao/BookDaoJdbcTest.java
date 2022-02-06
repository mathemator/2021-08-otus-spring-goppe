package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.domain.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc bookDao;

    @Test
    void insert() {
        Book expected = new Book(4, "THE PROCESS", 1, 1);
        bookDao.insert(expected);
        Book actual = bookDao.getById(expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getById() {
        Book expected = new Book(1, "THE CASTLE", 1, 1);
        Book actual = bookDao.getById(expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getAll() {
        Book expected = new Book(1, "THE CASTLE", 1, 1);
        Book expected2 = new Book(2, "THE GOVERNMENT INSPECTOR", 2, 2);
        Book expected3 = new Book(3, "DEAD SOULS", 2, 1);
        List<Book> actualAuthorList = bookDao.getAll();
        assertThat(actualAuthorList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expected, expected2, expected3);
    }

    @Test
    void getByJenre() {
        Book expected = new Book(1, "THE CASTLE", 1, 1);
        Book expected2 = new Book(3, "DEAD SOULS", 2, 1);
        List<Book> actualAuthorList = bookDao.getByJenre("NOVEL");
        assertThat(actualAuthorList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expected, expected2);
    }

    @Test
    void getByAuthor() {
        Book expected = new Book(2, "THE GOVERNMENT INSPECTOR", 2, 2);
        Book expected2 = new Book(3, "DEAD SOULS", 2, 1);
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