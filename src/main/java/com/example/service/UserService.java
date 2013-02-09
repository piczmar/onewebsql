package com.example.service;

import com.generated.onewebsql.User;

import java.util.List;

/**
 * Service for managing users.
 */
public interface UserService {
    /*
   Returns users list given login name.
    */
    List<User> getUsersByLogin(String login);

    /* Saves user data if it exists or creates new user.
    */
    User save(User user);
}
