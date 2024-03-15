package com.bbd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bbd.model.User;
import com.google.gson.Gson;

@Repository
public class UserDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  final String dbQuery = "USE beanwechat;";

  private static class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
      User user = new User();
      user.setUserId(resultSet.getInt("UserId"));
      user.setUserName(resultSet.getString("UserName"));
      user.setEmailAddress(resultSet.getString("EmailAddress"));
      user.setMobileNo(resultSet.getString("MobileNo"));
      user.setCreatedAt(resultSet.getTimestamp("CreatedAt"));

      return user;
    }
  }

  public String getAllUsers() {
    final String sql = "SELECT UserId, UserName, EmailAddress, MobileNo, CreatedAt FROM Users";
    List<User> users;

    try {
      users = jdbcTemplate.query(dbQuery + sql, new UserRowMapper());
    } catch (EmptyResultDataAccessException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()).toString();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(e.getMessage()).toString();
    }

    String json = new Gson().toJson(users);

    return json;
  }

  public String getUserById(int UserId) {
    final String sql = "SELECT UserId, UserName, EmailAddress, MobileNo, CreatedAt FROM Users WHERE UserId = ?";

    User user;
    try {
      user = jdbcTemplate.queryForObject(dbQuery + sql, new UserRowMapper(), UserId);
    } catch (EmptyResultDataAccessException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()).toString();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(e.getMessage()).toString();
    }

    String json = new Gson().toJson(user);

    return json;
  }

  public String insertUserToDb(User user) {
    final String sql = "INSERT INTO Users (UserName, EmailAddress, MobileNo) VALUES (?, ?, ?)";
    final String UserName = user.getUserName();
    final String EmailAddress = user.getEmailAddress();
    final String MobileNo = user.getMobileNo();

    try {
      jdbcTemplate.update(dbQuery + sql, new Object[] { UserName, EmailAddress, MobileNo });
    } catch (EmptyResultDataAccessException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("data not found").toString();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(e.getMessage()).toString();
    }

    return ResponseEntity.status(HttpStatus.CREATED).body("Record Inserted successfully").toString();
  }

  public String updateUser(User user) {
    final String sql = "UPDATE Users SET UserName = ?, EmailAddress = ?, MobileNo = ? WHERE UserId = ?";
    final int UserId = user.getUserId();
    final String UserName = user.getUserName();
    final String EmailAddress = user.getEmailAddress();
    final String MobileNo = user.getMobileNo();

    int res;
    try {
      res = jdbcTemplate.update(dbQuery + sql, new Object[] { UserName, EmailAddress, MobileNo, UserId });
    } catch (EmptyResultDataAccessException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("data not found").toString();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(e.getMessage()).toString();
    }

    if (res == 0) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("failed update record").toString();
    }

    return ResponseEntity.status(HttpStatus.OK).body("Record updated successfully").toString();
  }

  public String deleteUserById(int UserId) {
    final String sql = "DELETE FROM Users WHERE UserId = ?";

    int res;
    try {
      res = jdbcTemplate.update(dbQuery + sql, UserId);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(e.getMessage()).toString();
    }

    if (res == 0) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("failed to delete record").toString();
    }

    return ResponseEntity.status(HttpStatus.OK).body("Record deleted successfully").toString();
  }

}
