package com.urlshortener.customurlshortener.repositorie;

import com.urlshortener.customurlshortener.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends  MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
