package ru.laba.mongomvc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authors {
    @Id
    private String id;

    private String name;

    private String surname;

    private LocalDateTime birthdate;

}
