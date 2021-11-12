package com.company.Summative2SemunigusSarah.dao;

import com.company.Summative2SemunigusSarah.model.Book;

import java.util.List;

public interface BookDao {
    Book addBook(Book book);

    Book getBook(int id);

    Book getBookByAuthor(int id);

    List<Book> getAllBook();

    void updateBook(Book book);

    void deleteBook(int id);

}
