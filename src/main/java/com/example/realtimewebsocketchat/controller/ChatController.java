package com.example.realtimewebsocketchat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.example.realtimewebsocketchat.model.Message;

@Controller
public final class ChatController {

    /**
     * Method to handle user registration. When a client sends a message to the 
     * "/app/chat.register" destination, this method will be invoked.
     * 
     * @param message the message payload containing the registration details.
     * @param headerAccessor the accessor to manage WebSocket session attributes.
     * @return the message to be broadcast to all clients subscribed to "/topic/public".
     */
    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public Message register(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        // Set the username in the WebSocket session attributes.
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        // Return the message to be broadcast to all clients subscribed to "/topic/public".
        return message;
    }

    /**
     * Method to handle sending messages. When a client sends a message to the 
     * "/app/chat.send" destination, this method will be invoked.
     * 
     * @param message the message payload containing the chat message.
     * @return the message to be broadcast to all clients subscribed to "/topic/public".
     */
    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message message) {
        // Return the message to be broadcast to all clients subscribed to "/topic/public".
        return message;
    }
}
