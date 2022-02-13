package ru.otus.spring.repository;

import ru.otus.spring.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment author);

    Optional<Comment> getById(long id);

    List<Comment> getByBookId(long id);

    void deleteById(long id);
}
