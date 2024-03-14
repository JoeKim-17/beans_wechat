package com.bbd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bbd.dao.ChatDao;
import com.bbd.model.Chat;

@RestController
@RequestMapping("/chats")
public class ChatController {

  @Autowired
  private ChatDao chatDao;

  @RequestMapping(method = RequestMethod.GET)
  public String getAllChats() {
    return chatDao.getAllChats();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String getChatById(@PathVariable("id") int ChatId) {
    return chatDao.getChatById(ChatId);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public String deleteChatById(@PathVariable("id") int ChatId) {
    return chatDao.deleteChatById(ChatId);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public String insertChat(@Validated @RequestBody Chat chat) {
    return chatDao.insertChatToDb(chat);
  }

}