package com.bbd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bbd.model.Message;

@Repository
public class MessageDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  final String dbQuery = "USE beanwechat;";

  private static class MessageRowMapper implements RowMapper<Message> {

    @Override
    public Message mapRow(ResultSet resultSet, int i) throws SQLException {
      Message message = new Message();

      message.setMessageID(resultSet.getInt("MessageID"));
      message.setChatID(resultSet.getInt("ChatId"));
      message.setContent(resultSet.getString("Content"));
      message.setCreatedAt(resultSet.getTimestamp("CreatedAt"));

      return message;
    }
  }

  public String getAllMessages() {
    List<Message> messages = null;
    try {
      final String sql = "SELECT MessageId, ChatId, Content, CreatedAt FROM Message";
      messages = jdbcTemplate.query(dbQuery + sql, new MessageRowMapper());
    } catch (EmptyResultDataAccessException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()).toString();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(e.getMessage()).toString();
    }

    return messages.toString();
  }

  public String getMessageById(int MessageId) {
    Message message;
    try {
      final String sql = "SELECT MessageId, ChatId, Content, CreatedAt FROM Message WHERE MessageId = ?";
      message = jdbcTemplate.queryForObject(dbQuery + sql, new MessageRowMapper(), MessageId);
    } catch (EmptyResultDataAccessException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()).toString();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(e.getMessage()).toString();
    }

    return message.toString();
  }

  public void insertMessageToDb(Message message) {
    final String sql = "EXECUTE InsertMessage @Sender = ?, @Receiver= ?, @Content= ?";
    final String sender = message.getSender();
    final String receiver = message.getReceiver();
    final String Content = message.getContent();
    try {
      jdbcTemplate.update(dbQuery + sql, new Object[] { sender, receiver, Content });
    } catch (Exception e) {
      System.err.println("DEBUG: failed to insert message to db " + e.toString());
    }
  }

  public void updateMessage(Message message) {
    final String sql = "UPDATE Message SET Content = ? WHERE MessageId = ?";
    final int MessageId = message.getMessageID();
    final String Content = message.getContent();

    jdbcTemplate.update(dbQuery + sql, new Object[] { Content, MessageId });
  }

  public void deleteMessageById(int MessageId) {
    final String sql = "DELETE FROM Message WHERE MessageId = ?";
    jdbcTemplate.update(dbQuery + sql, MessageId);
  }

}
