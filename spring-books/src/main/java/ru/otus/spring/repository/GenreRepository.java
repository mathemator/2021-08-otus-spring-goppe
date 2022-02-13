package ru.otus.spring.repository;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreRepository {
    void save(Genre genre);

    Genre getById(long id);

    List<Genre> getAll();

    void deleteById(long id);
}
