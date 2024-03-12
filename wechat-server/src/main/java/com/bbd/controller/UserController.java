package com.bbd.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
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

  @RequestMapping(method = RequestMethod.GET)
  public Collection<User> getAllUsers(){
    return this.userDao.getAllUsers();
  }

}
