package com.bbd.model;

public class Chat {

  private int ChatId;
  private String Sender;
  private String Receiver;

  public Chat(int ChatId, String Sender, String Receiver) {
    this.ChatId = ChatId;
    this.Sender = Sender;
    this.Receiver = Receiver;
  }

  public Chat() {}

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

}
