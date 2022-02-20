package ru.otus.spring.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void save() {
        Book book = new Book(1, "THE CASTLE", new Author(1, "FRANZ KAFKA"), new Genre(1, "NOVEL"));
        Comment expected = new Comment(3, "NEW Comment", book);
        Comment actual = commentRepository.save(expected);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getById() {
        Optional<Comment> optionalComment = commentRepository.findById(1);
        Comment expectedComment = mongoTemplate.findOne(Query.query(Criteria.where("id").is(1)), Comment.class);
        assertThat(optionalComment).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    void getAll() {
        Comment expected = new Comment(1, "GOOD STUFF", new Book());
        Comment expected2 = new Comment(2, "GOOD TOO", new Book());
        List<Comment> actualCommentList = commentRepository.findAll();

        assertThat(actualCommentList)
                .usingDefaultComparator()
                .containsExactlyInAnyOrder(expected, expected2);
    }

    @Test
    void getByBookId() {
        Comment expected = new Comment(1, "GOOD STUFF", new Book());
        List<Comment> actualCommentList = commentRepository.findByBookId(1L);
        assertThat(actualCommentList)
                .usingDefaultComparator()
                .containsExactlyInAnyOrder(expected);
    }

    @Test
    void deleteById() {
        assertThat(commentRepository.findById(2).isPresent());

        commentRepository.deleteById(2);

        assertThat(commentRepository.findById(2).isEmpty());
    }
}