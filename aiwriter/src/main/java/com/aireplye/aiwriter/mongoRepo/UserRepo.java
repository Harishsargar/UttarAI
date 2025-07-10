package com.aireplye.aiwriter.mongoRepo;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.aireplye.aiwriter.mongoEntity.User;

@Repository
@Profile({"mongodb", "prod"})
public interface UserRepo extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);

    //check if email exist 
    boolean existsByEmail(String email);
}
