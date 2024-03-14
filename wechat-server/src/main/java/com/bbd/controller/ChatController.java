package com.bbd.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bbd.dao.ChatDao;
import com.bbd.model.Chat;
import com.google.gson.Gson;

@RestController
@RequestMapping("/chats")
public class ChatController {

  @Autowired
  private ChatDao chatDao;

  @RequestMapping(method = RequestMethod.GET)
  public Collection<Chat> getAllChats() {
    return chatDao.getAllChats();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Chat getChatById(@PathVariable("id") int ChatId) {
    return chatDao.getChatById(ChatId);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteChatById(@PathVariable("id") int ChatId) {
    chatDao.deleteChatById(ChatId);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
  public void insertChat(@RequestBody String chat) {
    System.err.println("DEBUG: "+chat); 
    Chat c = new Gson().fromJson(chat,Chat.class);
    chatDao.insertChatToDb(c);
  }

}