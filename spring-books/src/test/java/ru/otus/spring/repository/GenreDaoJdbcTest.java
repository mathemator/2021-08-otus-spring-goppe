package ru.otus.spring.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
@Import(GenreRepositoryJpa.class)
class GenreDaoJdbcTest {

    @Autowired
    private GenreRepositoryJpa genreDao;

    @Test
    void insert() {
        Genre expected = new Genre(4, "DETECTIVE");
        genreDao.save(expected);
        Genre actual = genreDao.getById(expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getById() {
        Genre expected = new Genre(1, "NOVEL");
        Genre actual = genreDao.getById(expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getAll() {
        Genre expected = new Genre(1, "NOVEL");
        Genre expected2 = new Genre(2, "COMEDY");
        Genre expected3 = new Genre(3, "TEST GENRE");
        List<Genre> actualAuthorList = genreDao.getAll();
        assertThat(actualAuthorList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expected, expected2, expected3);
    }

    @Test
    void deleteById() {
        assertThatCode(() -> genreDao.getById(3))
                .doesNotThrowAnyException();

        genreDao.deleteById(3);

        assertThatThrownBy(() -> genreDao.getById(3))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}