package com.bbd.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bbd.dao.MessageDao;
import com.bbd.model.Message;

@RestController
@RequestMapping("/messages")
public class MessageController {

  @Autowired
  private MessageDao messageDao;

  @RequestMapping(method = RequestMethod.GET)
  public Collection<Message> getAllMessages() {
    return messageDao.getAllMessages();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Message getMessageById(@PathVariable("id") int MessageId) {
    return messageDao.getMessageById(MessageId);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteMessageById(@PathVariable("id") int MessageId) {
    messageDao.deleteMessageById(MessageId);
  }

  @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void updateMessage(@RequestBody Message message) {
    messageDao.updateMessage(message);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
  public void insertMessage(@RequestBody String message) {
    System.err.println("DEBUG: "+message);
    String[] messageStrings = message.split(",");
    messageDao.insertMessageToDb(new Message(-1, Integer.parseInt(messageStrings[0].trim()), messageStrings[1].trim()));
  }

}