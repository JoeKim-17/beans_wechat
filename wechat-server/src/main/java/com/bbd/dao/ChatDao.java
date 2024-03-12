package com.bbd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bbd.model.Chat;


@Repository
public class ChatDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  final String dbQuery = "USE beanwechat;";

  private static class ChatRowMapper implements RowMapper<Chat> {

    @Override
    public Chat mapRow(ResultSet resultSet, int i) throws SQLException {
      Chat chat = new Chat();

      chat.setChatID(resultSet.getInt("ChatId"));
      chat.setSenderID(resultSet.getInt("Sender"));
      chat.setReceiverID(resultSet.getInt("Receiver"));

      return chat;
    }
  }

  public Collection<Chat> getAllChats() {
    final String sql = "SELECT * FROM Chat";
    List<Chat> chats = jdbcTemplate.query(dbQuery + sql, new ChatRowMapper());

    return chats;
  }

  public Chat getChatById(int ChatId) {
    final String sql = "SELECT ChatId, Sender, Receiver FROM Chat WHERE ChatId = ?";
    Chat chat = jdbcTemplate.queryForObject(dbQuery + sql, new ChatRowMapper(), ChatId);

    return chat;
  }

  public void insertChatToDb(Chat chat) {
    final String sql = "INSERT INTO Chat (Sender, Receiver) VALUES (?, ?)";
    final int senderId = chat.getSenderID();
    final int receiverId = chat.getReceiverID();

    jdbcTemplate.update(dbQuery + sql, new Object[] {senderId, receiverId});
  }

  public void deleteChatById(int ChatId) {
    final String sql = "DELETE FROM Chat WHERE ChatId = ?";
    jdbcTemplate.update(dbQuery + sql, ChatId);
  }

  public void updateChat(Chat Chat) {}

}
