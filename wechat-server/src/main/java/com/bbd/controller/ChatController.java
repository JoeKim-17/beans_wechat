package com.bbd.controller;

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
  public String getAllChats() {
    return chatDao.getAllChats();
  }

  @RequestMapping(value = "/userchat/{sender}/{receiver}", method = RequestMethod.GET)
  public String getUserChat(@PathVariable String sender, @PathVariable String receiver) {
    return chatDao.getUserChat(sender, receiver);
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
  public String insertChat(@RequestBody String chat) {
    Chat c = new Gson().fromJson(chat, Chat.class);
    System.out.println("DEBUG InsertChat :" + chat);
    String rec = chatDao.insertChatToDb(c);
    System.out.println("REC: "+rec);
    return rec;
  }

}