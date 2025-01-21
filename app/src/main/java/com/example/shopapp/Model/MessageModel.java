package com.example.shopapp.Model;

public class MessageModel {
    private String senderId;
    private String recipientId;
    private String messageText;
    private long timestamp;

    // Constructor with senderId, recipientId, messageText, and timestamp
    public MessageModel(String senderId, String recipientId, String messageText, long timestamp) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.messageText = messageText;
        this.timestamp = timestamp;
    }

    // Constructor with senderId and messageText only
    public MessageModel(String senderId, String messageText) {
        this.senderId = senderId;
        this.messageText = messageText;
        this.timestamp = System.currentTimeMillis(); // Set current timestamp
    }

    // Getters
    public String getSenderId() {
        return senderId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public String getMessageText() {
        return messageText;
    }

    public long getTimestamp() {
        return timestamp;
    }

    // Setter for recipientId
    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }
}
