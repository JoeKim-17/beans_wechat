package com.bbd.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bbd.dao.UserDao;
import com.bbd.model.User;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserDao userDao;

  @RequestMapping(method = RequestMethod.GET)
  public int getUserID(@RequestHeader("username") String name) {
    Optional<User> user = getAllUsers().stream().filter(s -> s.getUserName().equals(name)).findFirst();
    return user.get().getUserId();
  }

  public Collection<User> getAllUsers() {
    return userDao.getAllUsers();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public User getUserById(@PathVariable("id") int UserId) {
    return userDao.getUserById(UserId);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteUserById(@PathVariable("id") int UserId) {
    userDao.deleteUserById(UserId);
  }

  @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void updateUser(@RequestBody User user) {
    userDao.updateUser(user);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public void insertUser(@RequestBody User user) {
    userDao.insertUserToDb(user);
  }

}
