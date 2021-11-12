package com.company.Summative2SemunigusSarah.dao;

import com.company.Summative2SemunigusSarah.model.Author;
import com.company.Summative2SemunigusSarah.model.Book;
import com.company.Summative2SemunigusSarah.model.Publisher;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthorDaoTest {
    @Autowired
    AuthorDao authorDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    PublisherDao publisherDao;

    @Before
    public void setUp() throws Exception {
        // Clean up the test db
        List<Author> tList = authorDao.getAllAuthor();
        for (Author t : tList) {
            authorDao.deleteAuthor(t.getId());
        }

        List<Book> aList = bookDao.getAllBook();

        for (Book a : aList) {
            bookDao.deleteBook(a.getId());
        }

        List<Publisher> pList = publisherDao.getAllPublisher();

        for (Publisher p : pList) {
            publisherDao.deletePublisher(p.getId());
        }

    }
    @Test
    public void addGetDeleteAuthor() {

        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Brown");
        author.setStreet("sdsf");
        author.setCity("DC");
        author.setState("VA");
        author.setPostalCode("33392");
        author.setPhone("800-393-3030");
        author.setEmail("jbrown@gmail.com");
        author = authorDao.addAuthor(author);

        Author author1 = authorDao.getAuthor(author.getId());
        assertEquals(author1, author);
        authorDao.deleteAuthor(author.getId());

        author1 = authorDao.getAuthor(author.getId());

        assertNull(author1);

    }

    @Test
    public void getAllAuthor() {

        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Brown");
        author.setStreet("sdsf");
        author.setCity("DC");
        author.setState("VA");
        author.setPostalCode("33392");
        author.setPhone("800-393-3030");
        author.setEmail("jbrown@gmail.com");
        author = authorDao.addAuthor(author);

        author = new Author();
        author.setFirstName("Max");
        author.setLastName("Johnson ");
        author.setStreet("sdsf");
        author.setCity("Seattle");
        author.setState("VA");
        author.setPostalCode("32344");
        author.setPhone("800-993-3333");
        author.setEmail("mjohn@gmail.com");
        authorDao.addAuthor(author);

        List<Author> tList = authorDao.getAllAuthor();

        assertEquals(tList.size(), 2);

    }


    @Test
    public void updateAuthor() {

        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Brown");
        author.setCity("DC");
        author.setPostalCode("33392");
        author.setPhone("800-393-3030");
        author.setEmail("jbrown@gmail.com");
        authorDao.addAuthor((author));

        author.setFirstName("New Name");
        author.setLastName("New LName");
        author.setCity("New City");
        author.setPostalCode("New Code");
        author.setPhone("New Number");
        author.setEmail("New Email");

        authorDao.updateAuthor(author);

        Author author1 = authorDao.getAuthor(author.getId());

        assertEquals(author1, author);
    }

}