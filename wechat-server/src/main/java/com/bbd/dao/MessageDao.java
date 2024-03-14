package com.bbd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

      message.setChatID(resultSet.getInt("ChatId"));
      message.setContent(resultSet.getString("Content"));

      return message;
    }
  }

  public Collection<Message> getAllMessages() {
    final String sql = "SELECT * FROM Message";
    List<Message> messages = jdbcTemplate.query(dbQuery + sql, new MessageRowMapper());

    return messages;
  }

  public Message getMessageById(int MessageId) {
    final String sql = "SELECT MessageId, ChatId, Content FROM Message WHERE MessageId = ?";
    Message message = jdbcTemplate.queryForObject(dbQuery + sql, new MessageRowMapper(), MessageId);

    return message;
  }

  public void insertMessageToDb(Message message) {
    final String sql = "INSERT INTO Message (ChatId, Content) VALUES (?, ?)";
    final int ChatId = message.getChatID();
    final String Content = message.getContent();
    try {
      jdbcTemplate.update(dbQuery + sql, new Object[] { ChatId, Content });      
    } catch (Exception e) {
      System.err.println("DEBUG: failed to insert message to db "+e.toString());
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
