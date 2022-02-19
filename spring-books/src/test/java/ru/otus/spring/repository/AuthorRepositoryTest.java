package ru.otus.spring.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.spring.domain.Author;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void saveNew() {
        Author expected = new Author(-1, "ALEXANDER PUSHKIN");
        Author actual = authorRepository.save(expected);
        expected.setId(4L);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void saveExisting() {
        Author expected = new Author(1, "ALEXANDER PUSHKIN");
        Author actual = authorRepository.save(expected);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getById() {
        Optional<Author> optionalAuthor = authorRepository.findById(1L);
        Author expectedAuthor = mongoTemplate.findOne(Query.query(Criteria.where("id").is(1)), Author.class);
        assertThat(optionalAuthor).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    void getAll() {
        Author expected = new Author(1, "FRANZ KAFKA");
        Author expected2 = new Author(2, "NIKOLAY GOGOL");
        Author expected3 = new Author(3, "TEST AUTHOR");
        List<Author> actualAuthorList = authorRepository.findAll();
        assertThat(actualAuthorList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expected, expected2, expected3);
    }

    @Test
    void deleteById() {
        assertThat(authorRepository.findById(3L).isPresent());

        authorRepository.deleteById(3L);

        assertThat(authorRepository.findById(3L).isEmpty());
    }
}