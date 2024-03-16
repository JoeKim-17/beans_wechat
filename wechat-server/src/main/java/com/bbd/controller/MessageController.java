package com.bbd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bbd.dao.MessageDao;
import com.bbd.model.Message;
import com.google.gson.Gson;
import com.google.gson.Gson;

@RestController
@RequestMapping("/messages")
public class MessageController {

  @Autowired
  private MessageDao messageDao;
  private Map<Integer, String> notifcations = new HashMap<>();
  private int trackSize = 0;

  @RequestMapping(value = "/notifactions/{user}", method = RequestMethod.GET)
  public String getUserMessages(@PathVariable String user) {
    System.out.println("DEBUG: THIS IS NTOIFS");
    if (notifcations.size() == trackSize) {
      return "";
    } else {
      List<String> values = notifcations.values().stream().toList();
      System.out.println(values);
      trackSize = notifcations.size();
      return values.toString();
    }
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
  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
  public String insertMessage(@RequestBody String msg) {
    Gson gson = new Gson();
    System.out.println("EDGUB: " + msg);
    Message message = gson.fromJson(msg, Message.class);
    notifcations.put(message.getChatID(), message.getContent());
    System.out.println("GUBED: " + message);
    return messageDao.insertMessageToDb(message);

  }
}