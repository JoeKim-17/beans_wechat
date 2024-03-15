package com.bbd.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.bbd.helpers.Helper;
import com.bbd.model.User;

public class UserDaoTest {

  @Mock
  private JdbcTemplate jdbcTemplate;

  @InjectMocks
  private UserDao userDao;

  @Autowired
  private Helper helper;

  @Before
  public void setup() {
    helper = new Helper();
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetAllUsers() {
    // Mocking data retrieval from the database
    List<User> userList = new ArrayList<>();
    userList.add(new User(1, "User1", "user1@example.com", "1234567890", null));
    userList.add(new User(2, "User2", "user2@example.com", "0987654321", null));
    when(jdbcTemplate.query(any(String.class), any(RowMapper.class))).thenReturn(userList);

    // Testing getAllUsers method
    String expectedJson = helper.mockUsers();

    assertEquals(expectedJson, userDao.getAllUsers());
  }

  @Test
  public void testGetUserById() {
    // Mocking data retrieval from the database
    User user = new User(1, "User1", "user1@example.com", "1234567890", null);
    when(jdbcTemplate.queryForObject(any(String.class), any(RowMapper.class), any(Integer.class)))
        .thenReturn(user);

    // Testing getUserById method
    String expectedJson = helper.mockUser();
    assertEquals(expectedJson, userDao.getUserById(1));
  }

  @Test
  public void testInsertUserToDb() {
    // Testing insertUserToDb method
    ResponseEntity<String> expectedResponse = ResponseEntity.status(HttpStatus.CREATED)
        .body("Record Inserted successfully");
    assertEquals(expectedResponse.toString(),
        userDao.insertUserToDb(new User(1, "User3", "user3@example.com", "9876543210", null)));
  }

  @Test
  public void testUpdateUser() {
    // Mocking data update in the database
    when(jdbcTemplate.update(any(String.class), any(Object[].class))).thenReturn(1);

    // Testing updateUser method
    ResponseEntity<String> expectedResponse = ResponseEntity.status(HttpStatus.OK)
        .body("Record updated successfully");
    assertEquals(expectedResponse.toString(),
        userDao.updateUser(new User(1, "UpdatedUser", "updated@example.com", "5555555555", null)));
  }

  @Test
  public void testDeleteUserById() {
    // Mocking data deletion in the database
    when(jdbcTemplate.update(any(String.class), any(Integer.class))).thenReturn(1);

    // Testing deleteUserById method
    ResponseEntity<String> expectedResponse = ResponseEntity.status(HttpStatus.OK)
        .body("Record deleted successfully");
    assertEquals(expectedResponse.toString(), userDao.deleteUserById(1));
  }
}
