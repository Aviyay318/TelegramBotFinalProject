package org.example;

import java.time.LocalDateTime;

public class Message {
    private Long id;
    private LocalDateTime timestamp;
    private String name;

    public Message(Long id, LocalDateTime timestamp, String name) {
        this.id = id;
        this.timestamp = timestamp;
        this.name = name;
    }
// Constructors, getters, and setters

    @Override
    public String toString() {
        return "ID: " + id + ", Timestamp: " + timestamp + ", Name: " + name;
    }
}

