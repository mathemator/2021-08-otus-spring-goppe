package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookDao {

    void insert(Book book);

    Book getById(long id);

    List<Book> getAll();

    List<Book> getByGenre(String genreName);

    List<Book> getByAuthor(String authorName);

    void deleteById(long id);

    void updateById(Book book);
}
