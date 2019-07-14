package kov.irok.WebClient.entity;

import java.util.ArrayList;
import java.util.List;

public class Chat {

    private int id;
    private User agent;
    private User client;
    private List<ChatMessage> messages;

    public Chat(int id, User agent, User client) {
        this.id = id;
        this.agent = agent;
        this.client = client;
        this.messages = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAgent() {
        return agent;
    }

    public void setAgent(User agent) {
        this.agent = agent;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public void addMessage(ChatMessage message){
        messages.add(message);
    }
}
