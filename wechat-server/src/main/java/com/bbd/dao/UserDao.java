package com.bbd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bbd.model.User;

@Repository("mssql")
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

      return user;
    }
  }

  public Collection<User> getAllUsers() {
    final String sql = "SELECT * FROM Users";
    List<User> users = jdbcTemplate.query(dbQuery + sql, new UserRowMapper());

    return users;
  }

  public User getUserById(int UserId) {
    final String sql = "SELECT UserId, UserName, EmailAddress, MobileNo FROM Users where UserId = ?";
    User user = jdbcTemplate.queryForObject(dbQuery + sql, new UserRowMapper(), UserId);

    return user;
  }

  public void insertUserToDb(User user) {
    final String sql = "INSERT INTO Users (UserName, EmailAddress, MobileNo) VALUES (?, ?, ?)";
    final String UserName = user.getUserName();
    final String EmailAddress = user.getEmailAddress();
    final String MobileNo = user.getMobileNo();

    jdbcTemplate.update(dbQuery + sql, new Object[] { UserName, EmailAddress, MobileNo });
  }

  public void updateUser(User user) {
    final String sql = "UPDATE Users SET UserName = ?, EmailAddress = ?, MobileNo = ? WHERE UserId = ?";
    final int UserId = user.getUserId();
    final String UserName = user.getUserName();
    final String EmailAddress = user.getEmailAddress();
    final String MobileNo = user.getMobileNo();

    jdbcTemplate.update(dbQuery + sql, new Object[] { UserName, EmailAddress, MobileNo, UserId });
  }

  public void deleteUserById(int UserId) {
    final String sql = "DELETE FROM Users WHERE UserId = ?";
    jdbcTemplate.update(dbQuery + sql, UserId);
  }

}
