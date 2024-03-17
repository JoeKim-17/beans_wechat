package com.bbd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bbd.dao.UserDao;
import com.bbd.model.User;
import com.google.gson.Gson;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserDao userDao;

  @RequestMapping(method = RequestMethod.GET)
  public String getAllUsers() {
    return userDao.getAllUsers();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String getUserById(@PathVariable("id") int UserId) {
    return userDao.getUserById(UserId);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public String deleteUserById(@PathVariable("id") int UserId) {
    return userDao.deleteUserById(UserId);
  }

  @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.ALL_VALUE)
  public String updateUser(@RequestBody User user) {
    return userDao.updateUser(user);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
  public String insertUser(@RequestBody String user) {
    User u = new Gson().fromJson(user, User.class);
    System.out.println("DEBUG: "+u);
    return userDao.insertUserToDb(u);
  }

}
