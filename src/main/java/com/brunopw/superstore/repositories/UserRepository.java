package com.brunopw.superstore.repositories;

import com.brunopw.superstore.User;
import com.brunopw.superstore.UserType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    List<User> findByType(UserType type);

    List<User> findByUsername(String username);

    List<User> findByEmail(String email);

    List<User> findByNameContaining(String name);
}
