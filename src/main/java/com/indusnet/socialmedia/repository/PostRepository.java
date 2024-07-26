package com.indusnet.socialmedia.repository;

import com.indusnet.socialmedia.model.Post;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {

    void deleteById(Long id);

    Optional<Post> findById(Long id);
}
