package com.company.Summative2SemunigusSarah.dao;

import com.company.Summative2SemunigusSarah.model.Author;
import com.company.Summative2SemunigusSarah.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


@Repository
public class AuthorDaoIJdbcTemplmpl implements AuthorDao {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_AUTHOR_SQL =
            "insert into author (first_name, last_name, street, city,state, postal_code, phone, email) values (?, ?, ?, ?, ?, ?,?,?)";

    private static final String SELECT_AUTHOR_SQL =
            "select * from author where author_id = ?";

    private static final String SELECT_ALL_AUTHOR_SQL =
            "select * from author";

    private static final String UPDATE_AUTHOR_SQL =
            "update author set first_name = ?, last_name = ?, street = ?, city = ?, postal_code = ?, phone = ?, email = ?, where author_id = ?";

    private static final String DELETE_AUTHOR =
            "delete from author where author_id = ?";

    @Autowired
    public AuthorDaoIJdbcTemplmpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Author addAuthor(Author author) {

        jdbcTemplate.update(
                INSERT_AUTHOR_SQL,
                author.getFirstName(),
                author.getLastName(),
                author.getStreet(),
                author.getCity(),
                author.getState(),
                author.getPostalCode(),
                author.getPhone(),
                author.getEmail());

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        author.setId(id);

        return author;
    }

    @Override
    public Author getAuthor(int id) {

        try {
            return jdbcTemplate.queryForObject(SELECT_AUTHOR_SQL, this::mapRowToAuthor, id);
        } catch (EmptyResultDataAccessException e) {

            return null;
        }
    }

    @Override
    public List<Author> getAllAuthor() {
        return jdbcTemplate.query(SELECT_ALL_AUTHOR_SQL, this::mapRowToAuthor);
    }

    @Override
    public void updateAuthor(Author author) {

        jdbcTemplate.update(
                UPDATE_AUTHOR_SQL,
                author.getFirstName(),
                author.getLastName(),
                author.getCity(),
                author.getPostalCode(),
                author.getPhone(),
                author.getEmail(),
                author.getId());

    }

    @Override
    public void deleteAuthor(int id) {
        jdbcTemplate.update(DELETE_AUTHOR, id);

    }

    private Author mapRowToAuthor(ResultSet rs, int rowNum) throws SQLException {
        Author author = new Author();
        author.setId(rs.getInt("author_id"));
        author.setFirstName(rs.getString("first_name"));
        author.setLastName(rs.getString("last_name"));
        author.setStreet(rs.getString("street"));
        author.setCity(rs.getString("city"));
        author.setState(rs.getString("state"));
        author.setPostalCode(rs.getString("postal_code"));
        author.setPhone(rs.getString("phone"));
        author.setEmail(rs.getString("email"));

        return author;
    }
}
