package com.bbd.model;

import java.sql.Timestamp;

public class CustomObject {

  private int ChatId;
  private int messageID;
  private String receiverUserName;
  private String senderUserName;
  private String content;
  private Timestamp CreatedAt;

  public CustomObject() {}

  public int getChatId() {
    return ChatId;
  }

  public void setChatId(int chatId) {
    ChatId = chatId;
  }

  public int getMessageID() {
    return messageID;
  }

  public void setMessageID(int messageID) {
    this.messageID = messageID;
  }

  public String getReceiverUserName() {
    return receiverUserName;
  }

  public void setReceiverUserName(String receiverUserName) {
    this.receiverUserName = receiverUserName;
  }

  public String getSenderUserName() {
    return senderUserName;
  }

  public void setSenderUserName(String senderUserName) {
    this.senderUserName = senderUserName;
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

  public void setCreatedAt(Timestamp timestamp) {
    CreatedAt = timestamp;
  }
}
