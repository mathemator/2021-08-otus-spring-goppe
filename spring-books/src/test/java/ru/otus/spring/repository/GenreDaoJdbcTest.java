package ru.otus.spring.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(GenreRepositoryJpa.class)
class GenreDaoJdbcTest {

    @Autowired
    private GenreRepositoryJpa genreRepositoryJpa;


    @Autowired
    private TestEntityManager em;


    @Test
    void save() {
        Genre expected = new Genre(4, "NEW GENRE");
        Genre actual = genreRepositoryJpa.save(expected);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getById() {
        Optional<Genre> optionalGenre = genreRepositoryJpa.getById(1);
        Genre expectedGenre = em.find(Genre.class, 1L);
        assertThat(optionalGenre).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    void getAll() {
        Genre expected = new Genre(1, "NOVEL");
        Genre expected2 = new Genre(2, "COMEDY");
        Genre expected3 = new Genre(3, "TEST GENRE");
        List<Genre> actualGenreList = genreRepositoryJpa.getAll();
        assertThat(actualGenreList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expected, expected2, expected3);
    }

    @Test
    void deleteById() {
        assertThat(genreRepositoryJpa.getById(3).isPresent());

        genreRepositoryJpa.deleteById(3);

        assertThat(genreRepositoryJpa.getById(3).isEmpty());
    }
}