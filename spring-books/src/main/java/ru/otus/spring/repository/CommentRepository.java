package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.spring.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment save(Comment author);

    Optional<Comment> findById(long id);

    @Query("select c from Comment c join fetch c.book")
    List<Comment> findAll();

    List<Comment> findByBookId(long id);

    void deleteById(long id);
}
