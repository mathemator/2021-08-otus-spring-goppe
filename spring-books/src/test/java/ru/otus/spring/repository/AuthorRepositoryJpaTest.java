package ru.otus.spring.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(AuthorRepositoryJpa.class)
class AuthorRepositoryJpaTest {

    @Autowired
    private AuthorRepositoryJpa authorRepositoryJpa;

    @Autowired
    private TestEntityManager em;


    @Test
    void save() {
        Author expected = new Author(1, "ALEXANDER PUSHKIN");
        Author actual = authorRepositoryJpa.save(expected);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getById() {
        Optional<Author> optionalAuthor = authorRepositoryJpa.getById(1);
        Author expectedAuthor = em.find(Author.class, 1L);
        assertThat(optionalAuthor).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    void getAll() {
        Author expected = new Author(1, "FRANZ KAFKA");
        Author expected2 = new Author(2, "NIKOLAY GOGOL");
        Author expected3 = new Author(3, "TEST AUTHOR");
        List<Author> actualAuthorList = authorRepositoryJpa.getAll();
        assertThat(actualAuthorList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expected, expected2, expected3);
    }

    @Test
    void deleteById() {
        assertThat(authorRepositoryJpa.getById(3).isPresent());

        authorRepositoryJpa.deleteById(3);

        assertThat(authorRepositoryJpa.getById(3).isEmpty());
    }
}