package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }


    @Override
    public void insert(Book book) {
        namedParameterJdbcOperations.update("insert into book (id, title, author_id, jenre_id) " +
                        "values (:id, :title, :author_id, :jenre_id)",
                Map.of("id", book.getId(), "title", book.getTitle(),
                        "author_id", book.getAuthorId(), "jenre_id", book.getJenreId()));
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select id, title, author_id, jenre_id from book where id = :id", params, new BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query("select id, title, author_id, jenre_id from book", new BookMapper());
    }

    @Override
    public List<Book> getByJenre(String jenreName) {
        Map<String, Object> params = Collections.singletonMap("jenre_name", jenreName);
        return namedParameterJdbcOperations.query("select book.id, book.title, book.author_id, book.jenre_id, jenre.name " +
                        "from book left join jenre on book.jenre_id = jenre.id where jenre.name = :jenre_name",
                params, new BookMapper());
    }

    @Override
    public List<Book> getByAuthor(String authorName) {
        Map<String, Object> params = Collections.singletonMap("author_name", authorName);
        return namedParameterJdbcOperations.query("select book.id, book.title, book.author_id, book.jenre_id, author.name " +
                        "from book left join author on book.author_id = author.id where author.name = :author_name",
                params, new BookMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from book where id = :id", params
        );
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String title = resultSet.getString("title");
            long authorId = resultSet.getLong("author_id");
            long jenreId = resultSet.getLong("jenre_id");
            return new Book(id, title, authorId, jenreId);
        }
    }
}
