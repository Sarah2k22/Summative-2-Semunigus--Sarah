package com.company.Summative2SemunigusSarah.dao;

import com.company.Summative2SemunigusSarah.model.Publisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class PublisherDaoJdbcTempImpl implements PublisherDao {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_PUBLISHER_SQL =
            "insert into publisher (publisher_id, name, street, city, state, postal_code, phone, email) values (?, ?, ?, ?, ?, ?,? ,?)";

    private static final String SELECT_PUBLISHER_SQL =
            "select * from publisher where publisher_id = ?";

    private static final String SELECT_ALL_PUBLISHER_SQL =
            "select * from publisher_id";

    private static final String UPDATE_PUBLISHER_SQL =
            "update publisher set name = ?, street = ?, city = ?, state = ?, postal_code = ?, phone = ?, email = ?,where publisher_id = ?";

    private static final String DELETE_PUBLISHER =
            "delete from publisher where publisher_id = ?";

    @Override
    @Transactional
    public Publisher addPublisher(Publisher publisher) {

        jdbcTemplate.update(
                INSERT_PUBLISHER_SQL,
                publisher.getName(),
                publisher.getStreet(),
                publisher.getCity(),
                publisher.getState(),
                publisher.getPostal_code(),
                publisher.getEmail());

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        publisher.setId(id);
        return null;
    }

    @Override
    public Publisher getPublisher(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_PUBLISHER_SQL, this::mapRowToPublisher, id);
        } catch (EmptyResultDataAccessException e) {

            return null;
        }
    }

    @Override
    public List<Publisher> getAllPublisher() {
        return jdbcTemplate.query(SELECT_ALL_PUBLISHER_SQL, this::mapRowToPublisher);
    }

    @Override
    public void updatePublisher(Publisher publisher) {
        jdbcTemplate.update(
                UPDATE_PUBLISHER_SQL,
                publisher.getName(),
                publisher.getStreet(),
                publisher.getCity(),
                publisher.getState(),
                publisher.getPostal_code(),
                publisher.getEmail(),
                publisher.getId());

    }

    @Override
    public void deletePublisher(int id) {
        jdbcTemplate.update(DELETE_PUBLISHER, id);

    }
    private Publisher mapRowToPublisher(ResultSet rs, int rowNum) throws SQLException {

        Publisher publisher= new Publisher();
        publisher.setId(rs.getInt("publisher_id"));
        publisher.setName("Alpha Book Publisher");
        publisher.setStreet("1101 Finley Ln, Alexandria, VA 22304");
        publisher.setCity("Alexandria");
        publisher.setState("VA");
        publisher.setPostal_code("22304");
        publisher.setEmail("abp@gmail.com");
        return publisher;
    }


}
