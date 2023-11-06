package ru.laba.mongomvc.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.laba.mongomvc.models.Authors;
@Repository
public interface AuthorRepos extends MongoRepository<Authors, String> {
}
