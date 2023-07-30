package com.iprofile.model;

public class UserDetails {

    private String username;
    private String messageCount;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(String messageCount) {
        this.messageCount = messageCount;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "username='" + username + '\'' +
                ", messageCount='" + messageCount + '\'' +
                '}';
    }
}
