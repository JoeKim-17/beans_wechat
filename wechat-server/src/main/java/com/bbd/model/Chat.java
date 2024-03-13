package com.bbd.model;

public class Chat {

  private int chatID;
  private int senderID;
  private int receiverID;

  public Chat(int chatID, int senderID, int receiverID) {
    this.chatID = chatID;
    this.senderID = senderID;
    this.receiverID = receiverID;
  }

  public Chat() {}

  public int getChatID() {
    return chatID;
  }

  public void setChatID(int chatID) {
    this.chatID = chatID;
  }

  public int getSenderID() {
    return senderID;
  }

  public void setSenderID(int senderID) {
    this.senderID = senderID;
  }

  public int getReceiverID() {
    return receiverID;
  }

  public void setReceiverID(int receiverID) {
    this.receiverID = receiverID;
  }

}
