package com.urlshortener.customurlshortener.repositorie;

import com.urlshortener.customurlshortener.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends  MongoRepository<User, String> {
}
