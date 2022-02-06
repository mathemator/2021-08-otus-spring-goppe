package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Jenre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class JenreDaoJdbc implements JenreDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public JenreDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public void insert(Jenre jenre) {
        namedParameterJdbcOperations.update("insert into jenre (id, name) values (:id, :name)",
                Map.of("id", jenre.getId(), "name", jenre.getName().toUpperCase()));
    }

    @Override
    public Jenre getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select * from jenre where id = :id", params, new JenreMapper()
        );
    }

    @Override
    public List<Jenre> getAll() {
        return namedParameterJdbcOperations.query("select * from jenre", new JenreMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from jenre where id = :id", params
        );
    }

    private static class JenreMapper implements RowMapper<Jenre> {

        @Override
        public Jenre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Jenre(id, name);
        }
    }
}
