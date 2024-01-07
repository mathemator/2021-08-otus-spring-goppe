package ru.otus.spring.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class GenreRepositoryJpaTest {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void save() {
        Genre expected = new Genre("4", "NEW GENRE");
        Genre actual = genreRepository.save(expected);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getById() {
        Optional<Genre> optionalGenre = genreRepository.findById("1");
        Genre expectedGenre = mongoTemplate.findOne(Query.query(Criteria.where("id").is("1")), Genre.class);
        assertThat(optionalGenre).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    void getAll() {
        Genre expected = new Genre("1", "NOVEL");
        Genre expected2 = new Genre("2", "COMEDY");
        Genre expected3 = new Genre("3", "TEST GENRE");
        List<Genre> actualGenreList = genreRepository.findAll();
        assertThat(actualGenreList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expected, expected2, expected3);
    }

    @Test
    void deleteById() {
        assertThat(genreRepository.findById("3").isPresent());

        genreRepository.deleteById("3");

        assertThat(genreRepository.findById("3").isEmpty());
    }
}