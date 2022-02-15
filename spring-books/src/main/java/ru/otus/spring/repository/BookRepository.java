package ru.otus.spring.repository;

import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book save(Book book);

    Optional<Book> getById(long id);

    List<Book> getAll();

    List<Book> getByGenre(String genreName);

    List<Book> getByAuthor(String authorName);

    void deleteById(long id);
}
