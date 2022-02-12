package ru.otus.spring.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
@Import(AuthorRepositoryJpa.class)
class AuthorDaoJdbcTest {

    @Autowired
    private AuthorRepositoryJpa authorDao;

    @Test
    void insert() {
        Author expected = new Author(4, "ALEXANDER PUSHKIN");
        authorDao.insert(expected);
        Author actual = authorDao.getById(expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getById() {
        Author expected = new Author(1, "FRANZ KAFKA");
        Author actual = authorDao.getById(expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getAll() {
        Author expected = new Author(1, "FRANZ KAFKA");
        Author expected2 = new Author(2, "NIKOLAY GOGOL");
        Author expected3 = new Author(3, "TEST AUTHOR");
        List<Author> actualAuthorList = authorDao.getAll();
        assertThat(actualAuthorList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expected, expected2, expected3);
    }

    @Test
    void deleteById() {
        assertThatCode(() -> authorDao.getById(3))
                .doesNotThrowAnyException();

        authorDao.deleteById(3);

        assertThatThrownBy(() -> authorDao.getById(3))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}