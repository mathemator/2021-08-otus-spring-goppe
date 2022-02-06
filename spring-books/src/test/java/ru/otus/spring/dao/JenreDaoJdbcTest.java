package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.domain.Jenre;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(JenreDaoJdbc.class)
class JenreDaoJdbcTest {

    @Autowired
    private JenreDaoJdbc jenreDao;

    @Test
    void insert() {
        Jenre expected = new Jenre(4, "DETECTIVE");
        jenreDao.insert(expected);
        Jenre actual = jenreDao.getById(expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getById() {
        Jenre expected = new Jenre(1, "NOVEL");
        Jenre actual = jenreDao.getById(expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getAll() {
        Jenre expected = new Jenre(1, "NOVEL");
        Jenre expected2 = new Jenre(2, "COMEDY");
        Jenre expected3 = new Jenre(3, "TEST JENRE");
        List<Jenre> actualAuthorList = jenreDao.getAll();
        assertThat(actualAuthorList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expected, expected2, expected3);
    }

    @Test
    void deleteById() {
        assertThatCode(() -> jenreDao.getById(3))
                .doesNotThrowAnyException();

        jenreDao.deleteById(3);

        assertThatThrownBy(() -> jenreDao.getById(3))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}