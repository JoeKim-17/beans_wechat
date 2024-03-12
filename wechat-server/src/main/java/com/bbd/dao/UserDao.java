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

  private static class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
      User user = new User();
      user.setUserID(resultSet.getInt("UserId"));
      user.setUsername(resultSet.getString("UserName"));
      user.setEmail(resultSet.getString("EmailAddress"));
      user.setMobile(resultSet.getString("MobileNo"));

      return user;
    }
  }

  public Collection<User> getAllUsers() {
    final String dbQuery = "USE beanwechat;";
    final String sql = "SELECT * FROM Users";
    List<User> users = jdbcTemplate.query(dbQuery + sql, new UserRowMapper());

    return users;
  }

  public User getUserByID(int id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getUserByID'");
  }

  public void insertUserToDb(User user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'insertUserToDb'");
  }

  public void updateUser(User User) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
  }

  public void deleteUserByID(int id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteUserByID'");
  }

}
