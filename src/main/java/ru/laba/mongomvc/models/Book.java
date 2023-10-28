package ru.laba.mongomvc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "book")
@Data
public class Book {
    @Id
    private String id;

    private String book;

    private String author;

    private String genres;

    private int index;

    public Book() {
    }

    public Book(String book, String author, String genres, int index) {
        this.book = book;
        this.author = author;
        this.genres = genres;
        this.index = index;
    }
}
