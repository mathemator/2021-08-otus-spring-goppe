package ru.otus.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, Long> {

    Book save(Book book);

    Optional<Book> findById(String id);

    List<Book> findAll();

    List<Book> findByGenreId(String id);

    @Query("{'genre.name' : ?0}")
    List<Book> findByGenreName(String genreName);

    @Query(value = "{'author.name' : ?0 }")
    List<Book> findByAuthorName(String authorName);

    List<Book> findByAuthorId(String id);

    void deleteById(String id);

    void deleteByGenreId(String id);
}
