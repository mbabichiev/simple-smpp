package com.gmail.foy.maxach.models;

public class Message {

    private Long time;
    private String message;

    public Message(Long time, String message) {
        this.time = time;
        this.message = message;
    }

    public Long getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }
}
