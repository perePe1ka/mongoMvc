package ru.laba.mongomvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import ru.laba.mongomvc.models.AggregationResult;
import ru.laba.mongomvc.models.Books;

import java.util.List;

@Service
public class AggregationService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<AggregationResult> performAggregation() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("title").count().as("count"),
                Aggregation.match(Criteria.where("count").gt(1))
        );

        AggregationResults<AggregationResult> result = mongoTemplate.aggregate(aggregation, "books", AggregationResult.class);
        List<AggregationResult> resultList = result.getMappedResults();

        return resultList;
    }
}
