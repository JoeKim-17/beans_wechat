package com.levelup.model;

public class Message {

  private String sender;
  private String receiver;
  private String content;
  private int messageID;
  private int chatID;

  public Message(String sender, String receiver, String content) {
    this.sender = sender;
    this.receiver = receiver;
    this.content = content;
  }

  public Message() {
  }

  public int getChatID() {
    return this.chatID;
  }

  public void setChatID(int chatID) {
    this.chatID = chatID; 
  }

  public int getMessageID() {
    return this.messageID;
  }

  public void setMessageID(int messageID) {
    this.messageID = messageID;
  }

  public String getSender() {
    return this.sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public String getReceiver() {
    return this.receiver;
  }

  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
