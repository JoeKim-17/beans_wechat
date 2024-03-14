package com.bbd.controller;

import java.util.Arrays;
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
import com.google.gson.Gson;
import com.bbd.model.Message;

@RestController
@RequestMapping("/messages")
public class MessageController {

  @Autowired
  private MessageDao messageDao;

  @RequestMapping(method = RequestMethod.GET)
  public String getAllMessages() {
    return messageDao.getAllMessages();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String getMessageById(@PathVariable("id") int MessageId) {
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

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void insertMessage(@RequestBody String message) {
    System.err.println("DEBUG MSG: " + message);
    Message msg = new Gson().fromJson(message, Message.class);
    messageDao.insertMessageToDb(new Message(msg.getSender(),msg.getReceiver(),msg.getContent()));
  }

}