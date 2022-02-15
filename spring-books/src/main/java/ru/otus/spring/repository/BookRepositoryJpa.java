package ru.otus.spring.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private final EntityManager em;

    public BookRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Book save(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public Optional<Book> getById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> getAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-entity-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public List<Book> getByGenre(String genreName) {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-entity-graph");
        TypedQuery<Book> query = em.createQuery("select b " +
                        "from Book b where b.genre.name = :genreName",
                Book.class);
        query.setParameter("genreName", genreName);

        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public List<Book> getByAuthor(String authorName) {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-entity-graph");
        TypedQuery<Book> query = em.createQuery("select b " +
                        "from Book b where b.author.name = :authorName",
                Book.class);
        query.setParameter("authorName", authorName);

        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from Book b " +
                "where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
