package com.bbd.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bbd.dao.MessageDao;
import com.bbd.model.Message;

@RestController
@RequestMapping("/messages")
public class MessageController {

  @Autowired
  private MessageDao messageDao;
  private Map<Integer, String> notifcations = new HashMap<>();
  @RequestMapping(value = "/user",method = RequestMethod.GET)
  public String getUserMessages(@RequestHeader("username") String username){

    return "";
  }
  @RequestMapping(method = RequestMethod.GET)
  public String getAllMessages() {
    return messageDao.getAllMessages();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String getMessageById(@PathVariable("id") int MessageId) {
    return messageDao.getMessageById(MessageId);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public String deleteMessageById(@PathVariable("id") int MessageId) {
    return messageDao.deleteMessageById(MessageId);
  }

  @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  public String updateMessage(@RequestBody Message message) {
    return messageDao.updateMessage(message);
  }

  /**
   * Uses Chat.ChatID and Message.Content to insert message
   * 
   * @param message A Message that has a chatID and content
   * @return error String
   */
  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public String insertMessage(@RequestBody Message message) {
    notifcations.put(message.getChatID(), message.getContent());
    return messageDao.insertMessageToDb(message);
  }

}