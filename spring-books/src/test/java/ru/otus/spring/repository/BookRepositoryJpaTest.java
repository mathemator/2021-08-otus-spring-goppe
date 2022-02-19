package ru.otus.spring.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class BookRepositoryJpaTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void save() {
        Book expectedBook = new Book(2, "TEST", new Author(2, "TEST AUTHOR 2"), new Genre(2, "TEST GENRE 2"));
        Book savedBook = bookRepository.save(expectedBook);
        assertThat(savedBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void getById() {
        Optional<Book> optionalbook = bookRepository.findById(1);
        Book expectedBook = mongoTemplate.findOne(Query.query(Criteria.where("id").is(1)), Book.class);
        assertThat(optionalbook).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void getAll() {
        Book expected = new Book(1, "THE CASTLE", new Author(1, "FRANZ KAFKA"), new Genre(1, "NOVEL"));

        Book expected2 = new Book(2, "THE GOVERNMENT INSPECTOR", new Author(2, "NIKOLAY GOGOL"), new Genre(2, "COMEDY"));

        Book expected3 = new Book(3, "DEAD SOULS", new Author(2, "NIKOLAY GOGOL"), new Genre(1, "NOVEL"));
        List<Book> actualAuthorList = bookRepository.findAll();
        assertThat(actualAuthorList)
                .usingRecursiveFieldByFieldElementComparator()
                .contains(expected, expected2, expected3);
    }

    @Test
    void getByGenre() {
        Book expected = new Book(1, "THE CASTLE", new Author(1, "FRANZ KAFKA"), new Genre(1, "NOVEL"));
        Book expected3 = new Book(3, "DEAD SOULS", new Author(2, "NIKOLAY GOGOL"), new Genre(1, "NOVEL"));
        List<Book> actualAuthorList = bookRepository.findByGenreName("NOVEL");
        assertThat(actualAuthorList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expected, expected3);
    }

    @Test
    void getByAuthor() {
        Book expected2 = new Book(2, "THE GOVERNMENT INSPECTOR", new Author(2, "NIKOLAY GOGOL"), new Genre(2, "COMEDY"));

        Book expected3 = new Book(3, "DEAD SOULS", new Author(2, "NIKOLAY GOGOL"), new Genre(1, "NOVEL"));
        List<Book> actualAuthorList = bookRepository.findByAuthorName("NIKOLAY GOGOL");
        assertThat(actualAuthorList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expected2, expected3);
    }

    @Test
    void deleteById() {
        assertThat(bookRepository.findById(3).isPresent());

        bookRepository.deleteById(3);

        assertThat(bookRepository.findById(3).isEmpty());
    }

}