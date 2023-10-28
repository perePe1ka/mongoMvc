package ru.laba.mongomvc.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.laba.mongomvc.models.Book;

import java.util.List;
import java.util.Optional;
@Repository
public interface BookRepos extends MongoRepository<Book, String> {
    List<Book> findByBook(String book);

    List<Book> findByAuthor(String author);

//    List<Book> findByGenre(String genres);


}
