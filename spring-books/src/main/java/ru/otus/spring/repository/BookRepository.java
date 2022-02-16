package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book save(Book book);

    Optional<Book> findById(long id);

    @EntityGraph("book-entity-graph")
    List<Book> findAll();

    @EntityGraph("book-entity-graph")
    List<Book> findByGenreName(String genreName);

    @EntityGraph("book-entity-graph")
    List<Book> findByAuthorName(String authorName);

    void deleteById(long id);
}
