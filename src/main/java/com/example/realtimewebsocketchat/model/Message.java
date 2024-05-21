package com.example.realtimewebsocketchat.model;

public class Message {

    // The content of the message
    private String content;

    // The sender of the message
    private String sender;

    // The type of the message (CHAT, LEAVE, JOIN)
    private MessageType type;

    /**
     * Enum for message types.
     * CHAT: Represents a standard chat message.
     * LEAVE: Represents a message indicating a user has left the chat.
     * JOIN: Represents a message indicating a user has joined the chat.
     */
    public enum MessageType {
        CHAT, LEAVE, JOIN
    }

    /**
     * Gets the content of the message.
     * 
     * @return the content of the message.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the message.
     * 
     * @param content the content to set.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the sender of the message.
     * 
     * @return the sender of the message.
     */
    public String getSender() {
        return sender;
    }

    /**
     * Sets the sender of the message.
     * 
     * @param sender the sender to set.
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * Gets the type of the message.
     * 
     * @return the type of the message.
     */
    public MessageType getType() {
        return type;
    }

    /**
     * Sets the type of the message.
     * 
     * @param type the type to set.
     */
    public void setType(MessageType type) {
        this.type = type;
    }
}
