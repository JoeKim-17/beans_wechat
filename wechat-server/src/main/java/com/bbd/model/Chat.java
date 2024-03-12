package com.bbd.model;

public class Chat {

  private int ChatId;
  private int Sender;
  private int Receiver;

  public Chat(int ChatId, int Sender, int Receiver) {
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

  public int getSenderID() {
    return Sender;
  }

  public void setSenderID(int Sender) {
    this.Sender = Sender;
  }

  public int getReceiverID() {
    return Receiver;
  }

  public void setReceiverID(int Receiver) {
    this.Receiver = Receiver;
  }

}
