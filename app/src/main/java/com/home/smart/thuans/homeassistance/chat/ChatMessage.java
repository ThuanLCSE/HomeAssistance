package com.home.smart.thuans.homeassistance.chat;

/**
 * Created by Thuans on 3/29/2017.
 */

public class ChatMessage {
    private boolean left;
    private String message;

    public ChatMessage(boolean left, String message) {
        super();
        this.setLeft(left);
        this.setMessage(message);
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
