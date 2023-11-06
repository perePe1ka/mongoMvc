package ru.laba.mongomvc.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.laba.mongomvc.models.Genres;
@Repository
public interface GenresRepos extends MongoRepository<Genres, String> {
}
