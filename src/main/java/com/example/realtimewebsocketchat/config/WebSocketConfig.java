package com.example.realtimewebsocketchat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    
    /**
     * Register STOMP endpoints. The endpoint is the URL to which a WebSocket (or SockJS) client will connect.
     * Here, we're using SockJS as a fallback option for browsers that don't support WebSocket.
     * 
     * @param registry the registry to which the STOMP endpoints are registered.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Register the "/websocket" endpoint with SockJS fallback options
        registry.addEndpoint("/websocket").withSockJS();
    }
    
    /**
     * Configure the message broker. The message broker is responsible for routing messages from one client to another.
     * 
     * @param registry the registry to configure the message broker.
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Enable a simple in-memory message broker with a destination prefix of "/topic".
        // Clients can subscribe to this prefix to receive messages.
        registry.enableSimpleBroker("/topic");
        
        // Set the application destination prefix to "/app".
        // Messages sent from clients to the server should have this prefix.
        registry.setApplicationDestinationPrefixes("/app");
    }
}
