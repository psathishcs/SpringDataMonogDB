package com.hello.spring.data.mongoDB.dal;

import com.hello.spring.data.mongoDB.model.User;

import java.util.List;

public interface UserDAL {
    List<User> getAllUsers();
    User getUser( String userId);

    User addNewUser(User user);
    Object getAllUsersSettings(String userId);
    String getAllUsersSettings(String userId, String key);
    String addUserSetting( String userId, String key, String value);
}
