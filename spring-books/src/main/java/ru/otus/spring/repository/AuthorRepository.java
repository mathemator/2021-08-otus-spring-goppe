package ru.otus.spring.repository;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorRepository {

    void save(Author author);

    Author getById(long id);

    List<Author> getAll();

    void deleteById(long id);
}
