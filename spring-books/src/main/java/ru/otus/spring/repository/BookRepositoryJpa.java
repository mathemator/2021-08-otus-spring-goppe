package ru.otus.spring.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepositoryJpa implements BookRepository {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookRepositoryJpa(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }


    @Override
    public void insert(Book book) {
        namedParameterJdbcOperations.update("insert into book (title, author_id, genre_id) " +
                        "values (:title, :author_id, :genre_id)",
                Map.of("title", book.getTitle().toUpperCase(),
                        "author_id", book.getAuthor().getId(), "genre_id", book.getGenre().getId()));
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select book.id, book.title, book.author_id, " +
                        "author.name as author_name, book.genre_id, genre.name as genre_name from book " +
                        "left join genre on book.genre_id = genre.id " +
                        "left join author on book.author_id = author.id where book.id = :id", params, new BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query("select book.id, book.title, book.author_id, " +
                "author.name as author_name, book.genre_id, genre.name as genre_name from book " +
                "left join genre on book.genre_id = genre.id " +
                "left join author on book.author_id = author.id", new BookMapper());
    }

    @Override
    public List<Book> getByGenre(String genreName) {
        Map<String, Object> params = Collections.singletonMap("genre_name", genreName);
        return namedParameterJdbcOperations.query("select book.id, book.title, book.author_id, " +
                        "author.name as author_name, book.genre_id, genre.name as genre_name from book " +
                        "left join genre on book.genre_id = genre.id " +
                        "left join author on book.author_id = author.id " +
                        "where genre.name = :genre_name",
                params, new BookMapper());
    }

    @Override
    public List<Book> getByAuthor(String authorName) {
        Map<String, Object> params = Collections.singletonMap("author_name", authorName);
        return namedParameterJdbcOperations.query("select book.id, book.title, book.author_id, " +
                        "author.name as author_name, book.genre_id, genre.name as genre_name from book " +
                        "left join genre on book.genre_id = genre.id " +
                        "left join author on book.author_id = author.id where author.name = :author_name",
                params, new BookMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from book where id = :id", params
        );
    }

    @Override
    public void updateById(Book book) {
        Map<String, Object> params = Map.of("id", book.getId(), "title", book.getTitle().toUpperCase(),
                "author_id", book.getAuthor().getId(), "genre_id", book.getGenre().getId());
        namedParameterJdbcOperations.update(
                "update book set title = :title, author_id = :author_id, genre_id = :genre_id " +
                        "where id = :id", params
        );
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String title = resultSet.getString("title");
            long authorId = resultSet.getLong("author_id");
            String authorName = resultSet.getString("author_name");
            long genreId = resultSet.getLong("genre_id");
            String genreName = resultSet.getString("genre_name");
            return new Book(id, title, new Author(authorId, authorName), new Genre(genreId, genreName));
        }
    }
}
