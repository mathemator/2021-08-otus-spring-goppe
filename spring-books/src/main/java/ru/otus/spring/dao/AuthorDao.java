package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Jenre;

import java.util.List;

public interface AuthorDao {

    void insert(Author jenre);

    Author getById(long id);

    List<Author> getAll();

    void deleteById(long id);
}
