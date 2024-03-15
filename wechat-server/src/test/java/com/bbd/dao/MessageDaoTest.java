package com.bbd.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
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
import com.bbd.model.Message;

public class MessageDaoTest {

  @Mock
  private JdbcTemplate jdbcTemplate;

  @InjectMocks
  private MessageDao messageDao;

  @Autowired
  private Helper helper;

  @Before
  public void setup() {
    helper = new Helper();
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetAllMessages() {
    // Mocking data retrieval from the database
    List<Message> messageList = new ArrayList<>();
    messageList.add(new Message(1, 1, "content1", null));
    messageList.add(new Message(2, 1, "content2", null));
    when(jdbcTemplate.query(any(String.class), any(RowMapper.class))).thenReturn(messageList);

    // Testing getAllMessages method
    String expectedJson = helper.mockMessages();
    assertEquals(expectedJson, messageDao.getAllMessages());
  }

  @Test
  public void testGetMessageById() {
    // Mocking data retrieval from the database
    Message message = new Message(1, 1, "content", new Timestamp(System.currentTimeMillis()));
    when(jdbcTemplate.queryForObject(any(String.class), any(RowMapper.class), any(Integer.class)))
        .thenReturn(message);

    // Testing getMessageById method
    String expectedJson = helper.mockMessage();
    assertEquals(expectedJson, messageDao.getMessageById(1));
  }

  @Test
  public void testInsertMessageToDb() {
    // Testing insertMessageToDb method
    ResponseEntity<String> expectedResponse = ResponseEntity.status(HttpStatus.CREATED)
        .body("Record Inserted successfully");
    assertEquals(expectedResponse.toString(),
        messageDao.insertMessageToDb(new Message(1, 4000, "content", null)));
  }

  @Test
  public void testUpdateMessage() {
    // Mocking data update in the database
    when(jdbcTemplate.update(any(String.class), any(Object[].class))).thenReturn(1);

    // Testing updateMessage method
    ResponseEntity<String> expectedResponse = ResponseEntity.status(HttpStatus.OK)
        .body("Record updated successfully");
    assertEquals(expectedResponse.toString(),
        messageDao.updateMessage(new Message(1, 1, "updated content", null)));
  }

  @Test
  public void testDeleteMessageById() {
    // Mocking data deletion in the database
    when(jdbcTemplate.update(any(String.class), any(Integer.class))).thenReturn(1);

    // Testing deleteMessageById method
    ResponseEntity<String> expectedResponse = ResponseEntity.status(HttpStatus.OK)
        .body("Record deleted successfully");
    assertEquals(expectedResponse.toString(), messageDao.deleteMessageById(1));
  }
}
