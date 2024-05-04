package com.example.realtimewebsocketchat.model;

public class Message {
    private String content;
    private String sender;
    private MessageType type;

    // Enum for message types: CHAT, LEAVE, JOIN
    public enum MessageType {
        CHAT, LEAVE, JOIN
    }

    // Getters and setters for message content, sender, and type
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
}
