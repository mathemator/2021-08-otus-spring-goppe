package ru.otus.spring.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class BookRepositoryJpaTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    void save() {
        Book expectedBook = new Book(2, "TEST", new Author(2, "TEST AUTHOR 2"), new Genre(2, "TEST GENRE 2"),
                Collections.emptyList());
        Book savedBook = bookRepository.save(expectedBook);
        assertThat(savedBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void getById() {
        Optional<Book> optionalbook = bookRepository.findById(1);
        Book expectedBook = em.find(Book.class, 1L);
        assertThat(optionalbook).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void getAll() {
        Book expected = new Book(1, "THE CASTLE", new Author(1, "FRANZ KAFKA"), new Genre(1, "NOVEL"), new ArrayList<>());
        expected.getComments().add(new Comment(1, "GOOD STUFF", expected));

        Book expected2 = new Book(2, "THE GOVERNMENT INSPECTOR", new Author(2, "NIKOLAY GOGOL"), new Genre(2, "COMEDY"), Collections.emptyList());

        Book expected3 = new Book(3, "DEAD SOULS", new Author(2, "NIKOLAY GOGOL"), new Genre(1, "NOVEL"), new ArrayList<>());
        expected3.getComments().add(new Comment(2, "GOOD TOO", expected3));
        List<Book> actualAuthorList = bookRepository.findAll();
        assertThat(actualAuthorList)
                .usingRecursiveFieldByFieldElementComparator()
                .contains(expected, expected2, expected3);
    }

    @Test
    void getByGenre() {
        Book expected = new Book(1, "THE CASTLE", new Author(1, "FRANZ KAFKA"), new Genre(1, "NOVEL"), new ArrayList<>());
        expected.getComments().add(new Comment(1, "GOOD STUFF", expected));

        Book expected3 = new Book(3, "DEAD SOULS", new Author(2, "NIKOLAY GOGOL"), new Genre(1, "NOVEL"), new ArrayList<>());
        expected3.getComments().add(new Comment(2, "GOOD TOO", expected3));
        List<Book> actualAuthorList = bookRepository.findByGenreName("NOVEL");
        assertThat(actualAuthorList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expected, expected3);
    }

    @Test
    void getByAuthor() {
        Book expected2 = new Book(2, "THE GOVERNMENT INSPECTOR", new Author(2, "NIKOLAY GOGOL"), new Genre(2, "COMEDY"), Collections.emptyList());

        Book expected3 = new Book(3, "DEAD SOULS", new Author(2, "NIKOLAY GOGOL"), new Genre(1, "NOVEL"), new ArrayList<>());
        expected3.getComments().add(new Comment(2, "GOOD TOO", expected3));
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