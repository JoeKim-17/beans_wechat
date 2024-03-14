package com.bbd.model;

import java.sql.Timestamp;

public class Chat {

  private int ChatId;
  private String Sender;
  private String Receiver;
  private Timestamp CreatedAt;

  public Chat() {
  }

  public Chat(int ChatId, String Sender, String Receiver) {
    this.ChatId = ChatId;
    this.Sender = Sender;
    this.Receiver = Receiver;
  }

  public Chat(int ChatId, String Sender, String Receiver, Timestamp CreatedAt) {
    this.ChatId = ChatId;
    this.Sender = Sender;
    this.Receiver = Receiver;
    this.CreatedAt = CreatedAt;

  }

  public int getChatID() {
    return ChatId;
  }

  public void setChatID(int ChatId) {
    this.ChatId = ChatId;
  }

  public String getSender() {
    return Sender;
  }

  public void setSender(String Sender) {
    this.Sender = Sender;
  }

  public String getReceiver() {
    return Receiver;
  }

  public void setReceiver(String Receiver) {
    this.Receiver = Receiver;
  }

  public Timestamp getCreatedAt() {
    return CreatedAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    CreatedAt = createdAt;
  }
}
