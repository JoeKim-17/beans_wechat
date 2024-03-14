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
import org.springframework.web.bind.annotation.RestController;

import com.bbd.dao.UserDao;
import com.bbd.model.User;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserDao userDao;

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login(String username) {
    
    return "";
  }

  @RequestMapping(method = RequestMethod.GET)
  public int getUserID(@RequestHeader("username") String name) {
    System.out.println("DEBUG " + name);
    System.err.println(getAllUsers());
    Optional<User> user = getAllUsers().stream().filter(s -> s.getUserName().equals(name)).findFirst();
    System.out.println(user);
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

  @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.ALL_VALUE)
  public void updateUser(@RequestBody User user) {
    userDao.updateUser(user);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
  public void insertUser(@RequestBody String user) {
    System.out.println("======================== TYING =========================");
    String[] details = (user.toString()).split(",");
    userDao.insertUserToDb(new User(0, details[0].trim(), details[1].trim(), details[2].trim()));
    System.out.println("SUCESS");
  }

}
