package com.example.realtimewebsocketchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Real-time WebSocket Chat Application.
 * This class contains the main method which is the entry point of the Spring Boot application.
 */
@SpringBootApplication
public class RealtimeWebsocketChatApplication {

    /**
     * The main method which serves as the entry point for the Spring Boot application.
     * It launches the application.
     * 
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Launch the Spring Boot application
        SpringApplication.run(RealtimeWebsocketChatApplication.class, args);
    }
}
