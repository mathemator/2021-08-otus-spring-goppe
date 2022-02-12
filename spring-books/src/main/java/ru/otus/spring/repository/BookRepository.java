package ru.otus.spring.repository;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookRepository {

    void insert(Book book);

    Book getById(long id);

    List<Book> getAll();

    List<Book> getByGenre(String genreName);

    List<Book> getByAuthor(String authorName);

    void deleteById(long id);

    void updateById(Book book);
}
