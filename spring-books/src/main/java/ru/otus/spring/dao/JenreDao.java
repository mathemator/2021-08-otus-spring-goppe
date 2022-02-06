package ru.otus.spring.dao;

import ru.otus.spring.domain.Jenre;

import java.util.List;

public interface JenreDao {
    void insert(Jenre jenre);

    Jenre getById(long id);

    List<Jenre> getAll();

    void deleteById(long id);
}
