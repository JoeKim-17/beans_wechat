package com.bbd.model;

public class Message {

  private int messageID;
  private int chatID;
  private String content;

  public Message(int messageID, int chatID, String content) {
    this.messageID = messageID;
    this.chatID = chatID;
    this.content = content;
  }

  public Message() {}

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

}
