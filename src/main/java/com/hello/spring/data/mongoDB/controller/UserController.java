package com.hello.spring.data.mongoDB.controller;

import com.hello.spring.data.mongoDB.dal.UserDAL;
import com.hello.spring.data.mongoDB.dal.UserRepository;
import com.hello.spring.data.mongoDB.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/users")
public class UserController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;
    private final UserDAL userDAL;

    @Autowired
    public UserController(UserRepository userRepository, UserDAL userDAL){
        this.userRepository = userRepository;
        this.userDAL = userDAL;
    }
    @GetMapping
    public List<User> getAllUsers(){
        LOG.info("Getting all users!");
        return userRepository.findAll();

    }
    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId){
        LOG.info("Getting user with ID: {} !", userId);
        return userRepository.findById(userId).orElse(null);
    }

    @PostMapping("/create")
    public User addNewUser(@RequestBody User user){
        return userDAL.addNewUser(user);
    }

    @GetMapping("/settings/{userId}")
    public Object getAllUsersSettings(@PathVariable String userId){
        LOG.info("Getting user setting with ID: {} !", userId);
        User user = userRepository.findById(userId).orElse(null);
        if (user != null){
            return user.getUserSettings();
        } else {
            return "User not found!";
        }
    }

    @GetMapping("/settings/{userId}/{key}")
    public String getAllUsersSettings(@PathVariable String userId, @PathVariable String key){
        LOG.info("Getting user setting with ID: {} !", userId);
        User user = userRepository.findById(userId).orElse(null);
        if (user != null){
            return user.getUserSettings().get(key);
        } else {
            return "User not found!";
        }
    }
    @GetMapping("/settings/{userId}/{key}/{value}")
    public String addUserSetting(@PathVariable String userId, @PathVariable String key, @PathVariable String value){
        LOG.info("Getting user setting with ID: {} !", userId);
        User user = userRepository.findById(userId).orElse(null);
        if (user != null){
            user.getUserSettings().put(key, value);
            userRepository.save(user);
            return "key added";
        } else {
            return "User not found!";
        }
    }
}
