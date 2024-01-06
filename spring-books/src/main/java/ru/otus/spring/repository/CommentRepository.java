package ru.otus.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment, Long> {

    Comment save(Comment author);

    Optional<Comment> findById(String id);

    List<Comment> findAll();

    List<Comment> findByBookId(String id);

    void deleteById(String id);

    void deleteByBookId(String id);
}
