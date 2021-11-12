package com.company.Summative2SemunigusSarah.dao;

import com.company.Summative2SemunigusSarah.model.Author;
import com.company.Summative2SemunigusSarah.model.Book;
import com.company.Summative2SemunigusSarah.model.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PublisherDaoTest {
    @Autowired
    AuthorDao authorDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    PublisherDao publisherDao;


    @Before
    public void setUp() throws Exception {
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
    public void addGetDeletePublisher(){

        Publisher publisher = new Publisher();
        publisher.setName("Alpha Book Publisher");
        publisher.setStreet("1101 Finley Ln, Alexandria, VA 22304");
        publisher.setCity("Alexandria");
        publisher.setState("VS");
        publisher.setPostal_code("22304");
        publisher.setEmail("abp@gmail.com");
        publisher = publisherDao.addPublisher(publisher);

        Publisher publisher1= publisherDao.getPublisher(publisher.getId());
        assertEquals(publisher1,publisher);
        publisherDao.deletePublisher(publisher.getId());
        assertNull(publisher1);

    }
    @Test
    public void getAllPublisher(){
        Publisher publisher= new Publisher();
        publisher.setName("Alpha Book Publisher");
        publisher.setStreet("1101 Finley Ln, Alexandria, VA 22304");
        publisher.setCity("Alexandria");
        publisher.setState("VA");
        publisher.setPostal_code("22304");
        publisher.setEmail("abp@gmail.com");
        publisherDao.addPublisher(publisher);

        publisher.setName("New Name");
        publisher.setStreet("New address");
        publisher.setCity("New city");
        publisher.setState("New state");
        publisher.setPostal_code("New Code");
        publisher.setEmail("New email");
        Publisher publisher1 = publisherDao.getPublisher(publisher.getId());

        assertEquals(publisher1, publisher);

    }

    @Test
    public void updatePublisher(){

        Publisher publisher= new Publisher();
        publisher.setName("Alpha Book Publisher");
        publisher.setStreet("1101 Finley Ln, Alexandria, VA 22304");
        publisher.setCity("Alexandria");
        publisher.setState("VA");
        publisher.setPostal_code("22304");
        publisher.setEmail("abp@gmail.com");
        publisherDao.addPublisher(publisher);

        publisher.setName("Inside Washington Publishers");
        publisher.setStreet("1919 S Eads St Suite 100, Arlington, VA 22202");
        publisher.setCity("Arlington");
        publisher.setState("VA");
        publisher.setPostal_code("22202");
        publisher.setEmail("iwp@gmail.com");

        publisherDao.updatePublisher(publisher);

        Publisher publisher1 = publisherDao.getPublisher(publisher.getId());

        assertEquals(publisher1, publisher);



    }

}