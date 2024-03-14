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

import com.bbd.model.Message;
import com.google.gson.Gson;

@Repository
public class MessageDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  final String dbQuery = "USE beanwechat;";

  private static class MessageRowMapper implements RowMapper<Message> {

    @Override
    public Message mapRow(ResultSet resultSet, int i) throws SQLException {
      Message message = new Message();

      message.setChatID(resultSet.getInt("ChatId"));
      message.setContent(resultSet.getString("Content"));
      message.setCreatedAt(resultSet.getTimestamp("CreatedAt"));

      return message;
    }
  }

  public String getAllMessages() {
    List<Message> messages = null;
    try {
      final String sql = "SELECT * FROM Message";
      messages = jdbcTemplate.query(dbQuery + sql, new MessageRowMapper());
    } catch (EmptyResultDataAccessException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()).toString();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(e.getMessage()).toString();
    }

    String json = new Gson().toJson(messages);

    return json;
  }

  public String getMessageById(int MessageId) {
    Message message;
    try {
      final String sql = "SELECT MessageId, ChatId, Content FROM Message WHERE MessageId = ?";
      message = jdbcTemplate.queryForObject(dbQuery + sql, new MessageRowMapper(), MessageId);
    } catch (EmptyResultDataAccessException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()).toString();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(e.getMessage()).toString();
    }

    String json = new Gson().toJson(message);

    return json;
  }

  public String insertMessageToDb(Message message) {

    final String sql = "INSERT INTO Message (ChatId, Content) VALUES (?, ?)";
    final int ChatId = message.getChatID();
    final String Content = message.getContent();

    try {
      jdbcTemplate.update(dbQuery + sql, new Object[] { ChatId, Content });
    } catch (EmptyResultDataAccessException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("data not found").toString();
    } catch (Exception e) {
      boolean isFkConstraintError = e.getMessage()
          .contains("The INSERT statement conflicted with the FOREIGN KEY constraint");

      if (isFkConstraintError) {
        return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Failed to INSERT: ChatId not found")
            .toString();
      }

      return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(e.getMessage()).toString();
    }

    return ResponseEntity.status(HttpStatus.CREATED).body("Record Inserted successfully").toString();
  }

  public String updateMessage(Message message) {
    final String sql = "UPDATE Message SET Content = ? WHERE MessageId = ?";
    final int MessageId = message.getMessageID();
    final String Content = message.getContent();

    int res;
    try {
      res = jdbcTemplate.update(dbQuery + sql, new Object[] { Content, MessageId });
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

  public String deleteMessageById(int MessageId) {
    final String sql = "DELETE FROM Message WHERE MessageId = ?";

    int res;
    try {
      res = jdbcTemplate.update(dbQuery + sql, MessageId);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(e.getMessage()).toString();
    }

    if (res == 0) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("failed to delete record").toString();
    }

    return ResponseEntity.status(HttpStatus.OK).body("Record deleted successfully").toString();
  }

}
