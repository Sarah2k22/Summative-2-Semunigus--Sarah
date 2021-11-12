package com.company.Summative2SemunigusSarah.dao;

import com.company.Summative2SemunigusSarah.model.Book;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
@Repository
public class BookDaoJdbcTempImpl implements BookDao {
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_BOOK_SQL =
            "insert into book (book_id, isbn, publish_date, author_id, title, publisher_id, publisher_id, price) values (?, ?, ?, ?, ?, ?,?)";

    private static final String SELECT_BOOK_SQL =
            "select * from book where book_id = ?";

    private static final String SELECT_ALL_BOOK_SQL =
            "select * from book_id";

    private static final String UPDATE_BOOK_SQL =
            "update book set isbn = ?, publish_date = ?, author_id = ?, title = ?, publisher_id = ?, price = ?, where book_id = ?";

    private static final String DELETE_BOOK =
            "delete from book where book_id = ?";
    private static final String SELECT_BOOK_BY_AUTHOR =
            "select * from book where author_id = ?";

    @Override
    @Transactional
    public Book addBook(Book book) {
        jdbcTemplate.update(
                INSERT_BOOK_SQL,
                book.getIsbn(),
                book.getPublishDate(),
                book.getAuthorId(),
                book.getTitle(),
                book.getPublisherId(),
                book.getPrice());

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        book.setId(id);


        return null;
    }

    @Override
    public Book getBook(int id) {

        try {
            return jdbcTemplate.queryForObject(SELECT_BOOK_SQL, this::mapRowToBook, id);
        } catch (EmptyResultDataAccessException e) {

            return null;
        }
    }

    @Override
    public List<Book> getAllBook() {
            return jdbcTemplate.query(SELECT_ALL_BOOK_SQL, this::mapRowToBook);
        }

    @Override
    public void updateBook(Book book) {
        jdbcTemplate.update(
                UPDATE_BOOK_SQL,
                book.getIsbn(),
                book.getPublishDate(),
                book.getAuthorId(),
                book.getTitle(),
                book.getPublisherId(),
                book.getPrice(),
                book.getId());

    }

    @Override
    public void deleteBook(int id) {
        jdbcTemplate.update(DELETE_BOOK, id);

    }
    @Override
    public Book getBookByAuthor(int id){
        try {
            return jdbcTemplate.queryForObject(SELECT_BOOK_BY_AUTHOR, this::mapRowToBook, id);
        } catch (EmptyResultDataAccessException e) {

            return null;
        }

    }

    private Book mapRowToBook(ResultSet rs, int rowNum) throws SQLException {

        Book book = new Book();
        book.setIsbn(rs.getString("isbn"));
        book.setPublishDate(rs.getObject("publish_date",LocalDate.class));
        book.setAuthorId(rs.getInt("author_id"));
        book.setTitle(rs.getString("title"));
        book.setPublisherId(rs.getInt("publisher_id"));
        book.setPrice(rs.getObject("price", BigDecimal.class));
        return book;
    }
}
