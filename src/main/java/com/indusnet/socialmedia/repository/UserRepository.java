package com.indusnet.socialmedia.repository;

import com.indusnet.socialmedia.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
