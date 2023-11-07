package ru.laba.mongomvc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Document(collection = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Books extends BaseEntity{

    private String title;

    private Authors author;

    private Genres genre;

    private int publicationYear;

    private int pageCount;

}

