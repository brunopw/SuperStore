package com.brunopw.superstore.controllers;

import com.brunopw.superstore.User;
import com.brunopw.superstore.UserNotFoundException;
import com.brunopw.superstore.UserType;
import com.brunopw.superstore.services.UserService;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    private List<User> findAll(@RequestParam(required = false) UserType type,
                               @RequestParam(required = false) String username,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String email) {
        if(type != null) {
            return userService.findByType(type);
        }
        if(username != null) {
            return userService.findByUsername(username);
        }
        if(name != null) {
            return userService.findByNameContaining(name);
        }
        if(email != null) {
            return userService.findByEmail(email);
        }
        return userService.findAll();
    }

    @PostMapping
    private User newUser(@Valid @RequestBody User newUser) {
        if(!userService.existsByEmail(newUser.getEmail())) {
            return userService.save(newUser);
        }
        return null;
    }

    @GetMapping("/{id}")
    private User findById(@PathVariable String id) {

        return userService.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/{id}")
    private User replaceUser(@RequestBody User newUser, @PathVariable String id) {

        return userService.findById(id)
                .map(User -> {
                    User.setUsername(newUser.getUsername());
                    User.setPassword(newUser.getPassword());
                    User.setType(newUser.getType());
                    User.setName(newUser.getName());
                    User.setEmail(newUser.getEmail());
                    User.setAddress(newUser.getAddress());
                    return userService.save(User);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userService.save(newUser);
                });
    }

    @DeleteMapping("/{id}")
    private void deleteUser(@PathVariable String id) {
        userService.deleteById(id);
    }
}
