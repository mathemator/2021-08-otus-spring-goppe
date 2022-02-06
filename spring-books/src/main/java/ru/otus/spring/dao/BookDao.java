package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookDao {

    void insert(Book book);

    Book getById(long id);

    List<Book> getAll();

    List<Book> getByJenre(String jenreName);

    List<Book> getByAuthor(String authorName);

    void deleteById(long id);
}
