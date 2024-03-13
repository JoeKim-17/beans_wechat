package com.bbd.controller;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bbd.dao.ChatDao;
import com.bbd.model.Chat;

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

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public void insertChat(@Validated @RequestBody String chat) {
    String[] chatter = chat.split(",");
    System.out.println(Arrays.toString(chatter));
    chatDao.insertChatToDb(new Chat(-1, chatter[0].trim(), chatter[1].trim()));
  }

}