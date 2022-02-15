package ru.otus.spring.repository;

import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Genre save(Genre genre);

    Optional<Genre> getById(long id);

    List<Genre> getAll();

    void deleteById(long id);
}
