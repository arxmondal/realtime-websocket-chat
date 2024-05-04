package com.example.realtimewebsocketchat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.example.realtimewebsocketchat.model.Message;

@Controller
public final class ChatController {
    
    // Method to handle registration of users
    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public Message register(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        // Set the username in the session attributes
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        // Return the message
        return message;
    }

    // Method to handle sending messages
    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message message) {
        // Return the message
        return message;
    }
}
