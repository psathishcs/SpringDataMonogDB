package com.hello.spring.data.mongoDB.dal;

import com.hello.spring.data.mongoDB.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
