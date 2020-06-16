package com.brunopw.superstore;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Document
public class User {

    @Id
    private String id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    private UserType type;
    @NotNull
    private String name;
    @Indexed(unique = true)
    @Email
    private String email;
    private String address;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @NotNull
    private LocalDate birthday;

    public User() {
        this.setType(UserType.CLIENT);
    }

    public User(String username, String password, UserType type) {
        this();
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public User(String email) {
        this.email = email;
    }

    public User(UserType type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
