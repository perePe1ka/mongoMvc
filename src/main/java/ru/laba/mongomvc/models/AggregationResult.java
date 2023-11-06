package ru.laba.mongomvc.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "books")
public class AggregationResult {
    @Id
    private String title;
    private int count;
}
