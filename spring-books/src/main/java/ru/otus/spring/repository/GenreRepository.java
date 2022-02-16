package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre save(Genre genre);

    Optional<Genre> findById(long id);

    List<Genre> findAll();

    void deleteById(long id);
}
