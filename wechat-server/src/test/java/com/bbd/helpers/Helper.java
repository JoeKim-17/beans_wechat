package com.bbd.helpers;

import java.sql.Timestamp;

import com.bbd.model.Chat;
import com.bbd.model.Message;
import com.bbd.model.User;
import com.google.gson.Gson;

public class Helper {

  public Helper() {
  }

  public String mockUsers() {
    User[] users = {
        new User(1, "User1", "user1@example.com", "1234567890"),
        new User(2, "User2", "user2@example.com", "0987654321")
    };

    return new Gson().toJson(users);
  }

  public String mockUser() {
    User users = new User(1, "User1", "user1@example.com", "1234567890");

    return new Gson().toJson(users);
  }

  public String mockChats() {
    Chat[] chats = {
      new Chat(1, "sender1", "receiver1"),
      new Chat(2, "sender2", "receiver2"),
    };

    return new Gson().toJson(chats);
  }

  public String mockChat() {
    Chat chat = new Chat(1, "sender", "receiver");

    return new Gson().toJson(chat);
  }


  public String mockMessages() {
    Message[] messages = {
      new Message(1, 1, "content1"),
      new Message(2, 1, "content2")
    };

    return new Gson().toJson(messages);
  }

  public String mockMessage() {
    Message message = new Message(1, 1, "content", new Timestamp(System.currentTimeMillis()));

    return new Gson().toJson(message);
  }

}
