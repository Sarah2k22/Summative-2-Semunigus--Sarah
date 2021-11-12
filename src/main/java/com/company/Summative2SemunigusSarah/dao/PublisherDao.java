package com.company.Summative2SemunigusSarah.dao;

import com.company.Summative2SemunigusSarah.model.Publisher;

import java.util.List;

public interface PublisherDao {
    Publisher addPublisher(Publisher publisher);

    Publisher getPublisher(int id);

    List<Publisher> getAllPublisher();

    void updatePublisher(Publisher publisher);

    void deletePublisher(int id);
}
