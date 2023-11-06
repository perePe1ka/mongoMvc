package ru.laba.mongomvc.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.laba.mongomvc.models.AggregationResult;
import ru.laba.mongomvc.models.Books;

import java.util.List;

@Repository
public interface BookRepos extends MongoRepository<Books, String> {
}

