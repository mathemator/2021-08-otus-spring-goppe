package ru.otus.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, Long> {

    Author save(Author author);

    Optional<Author> findById(String id);

    List<Author> findAll();

    void deleteById(String id);
}
