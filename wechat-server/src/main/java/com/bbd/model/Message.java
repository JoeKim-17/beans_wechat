package com.bbd.model;

import java.sql.Timestamp;

public class Message {

  private int messageID;
  private int chatID;
  private String content;
  private Timestamp CreatedAt;

  public Message() {}

  public Message(int messageID, int chatID, String content) {
    this.messageID = messageID;
    this.chatID = chatID;
    this.content = content;
  }

  public Message(int messageID, int chatID, String content, Timestamp CreatedAt) {
    this.messageID = messageID;
    this.chatID = chatID;
    this.content = content;
    this.CreatedAt = CreatedAt;
  }

  public int getMessageID() {
    return messageID;
  }

  public void setMessageID(int messageID) {
    this.messageID = messageID;
  }

  public int getChatID() {
    return chatID;
  }

  public void setChatID(int chatID) {
    this.chatID = chatID;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Timestamp getCreatedAt() {
    return CreatedAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    CreatedAt = createdAt;
  }
}
