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
import com.bbd.model.Chat;

public class ChatDaoTest {

  @Mock
  private JdbcTemplate jdbcTemplate;

  @InjectMocks
  private ChatDao chatDao;

  @Autowired
  private Helper helper;

  @Before
  public void setup() {
    helper = new Helper();
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetAllChats() {
    // Mocking data retrieval from the database
    List<Chat> chatList = new ArrayList<>();
    chatList.add(new Chat(1, "sender1", "receiver1", null));
    chatList.add(new Chat(2, "sender2", "receiver2", null));
    when(jdbcTemplate.query(any(String.class), any(RowMapper.class))).thenReturn(chatList);

    // Testing getAllChats method
    String expectedJson = helper.mockChats();

    assertEquals(expectedJson, chatDao.getAllChats());
  }

  @Test
  public void testGetChatById() {
    // Mocking data retrieval from the database
    Chat chat = new Chat(1, "sender", "receiver", null);
    when(jdbcTemplate.queryForObject(any(String.class), any(RowMapper.class), any(Integer.class)))
        .thenReturn(chat);

    // Testing getChatById method
    String expectedJson = helper.mockChat();
    assertEquals(expectedJson, chatDao.getChatById(1));
  }

  @Test
  public void testInsertChatToDb() {
    // Testing insertChatToDb method
    ResponseEntity<String> expectedResponse = ResponseEntity.status(HttpStatus.CREATED)
        .body("Record Inserted successfully");
    assertEquals(expectedResponse.toString(),
        chatDao.insertChatToDb(new Chat(1, "sender", "receiver", null)));
  }

  @Test
  public void testDeleteChatById() {
    // Mocking data deletion in the database
    when(jdbcTemplate.update(any(String.class), any(Integer.class))).thenReturn(1);

    // Testing deleteChatById method
    ResponseEntity<String> expectedResponse = ResponseEntity.status(HttpStatus.OK)
        .body("Record deleted successfully");
    assertEquals(expectedResponse.toString(), chatDao.deleteChatById(1));
  }
}
