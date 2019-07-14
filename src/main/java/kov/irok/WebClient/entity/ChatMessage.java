package kov.irok.WebClient.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ChatMessage {

    private int id;
    private User sender;
    private String content;
    private MessageType type;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Date date;

    public ChatMessage() {
    }

    public ChatMessage(Date date) {
        this.date = date;
    }

    public enum MessageType{
        CHAT, LEAVE, EXIT
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
