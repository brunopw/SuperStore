package com.brunopw.superstore.services;

import com.brunopw.superstore.User;
import com.brunopw.superstore.UserType;
import com.brunopw.superstore.repositories.UserRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User newUser) { ;
        return userRepository.save(newUser);
    }

    public User saveOrUpdate(User newUser) { ;
        Example userExample = Example.of(new User(newUser.getEmail()));
        List<User> users = userRepository.findAll(userExample);
        if(!users.isEmpty()){
            newUser.setId(users.get(0).getId());
        }
        return userRepository.save(newUser);
    }

    public Boolean existsByEmail(String email) {
        return userRepository.exists(Example.of(new User(email)));
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    public List<User> findByType(UserType type) {
        return userRepository.findByType(type);
    }

    public List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findByNameContaining(String name) {
        return userRepository.findByNameContaining(name);
    }
}
