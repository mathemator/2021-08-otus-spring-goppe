package ru.otus.spring.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(CommentRepositoryJpa.class)
class CommentRepositoryJpaTest {

    @Autowired
    private CommentRepositoryJpa commentRepositoryJpa;


    @Autowired
    private TestEntityManager em;


    @Test
    void save() {
        Book book = new Book(1, "THE CASTLE", new Author(1, "FRANZ KAFKA"), new Genre(1, "NOVEL"), new ArrayList<>());
        Comment expected = new Comment(3, "NEW Comment", book);
        Comment actual = commentRepositoryJpa.save(expected);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getById() {
        Optional<Comment> optionalComment = commentRepositoryJpa.getById(1);
        Comment expectedComment = em.find(Comment.class, 1L);
        assertThat(optionalComment).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    void getAll() {
        Comment expected = new Comment(1, "GOOD STUFF", new Book());
        Comment expected2 = new Comment(2, "GOOD TOO", new Book());
        List<Comment> actualCommentList = commentRepositoryJpa.getAll();

        assertThat(actualCommentList)
                .usingDefaultComparator()
                .containsExactlyInAnyOrder(expected, expected2);
    }

    @Test
    void getByBookId() {
        Comment expected = new Comment(1, "GOOD STUFF", new Book());
        List<Comment> actualCommentList = commentRepositoryJpa.getByBookId(1L);
        assertThat(actualCommentList)
                .usingDefaultComparator()
                .containsExactlyInAnyOrder(expected);
    }

    @Test
    void deleteById() {
        assertThat(commentRepositoryJpa.getById(3).isPresent());

        commentRepositoryJpa.deleteById(3);

        assertThat(commentRepositoryJpa.getById(3).isEmpty());
    }
}