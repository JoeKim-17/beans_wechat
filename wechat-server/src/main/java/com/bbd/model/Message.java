package com.bbd.model;

import java.sql.Timestamp;

public class Message {

  private int messageID; 
  private int chatID;
  private String content;
  private Timestamp CreatedAt;

  public Message(int messageID, int chatID, String content, Timestamp CreatedAt) {
    this.messageID = messageID;
    this.chatID = chatID;
    this.content = content;
    this.CreatedAt = CreatedAt;
  }

  public Message() {}

  public int getChatID() {
    return this.chatID;
  }

  public void setChatID(int chatID) {
    this.chatID = chatID;
  }

  public int getMessageID(){
    return this.messageID;
  }

  public void setMessageID(int messageID) {
    this.messageID = messageID;
  }
  public String getContent() {
    return this.content;
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
