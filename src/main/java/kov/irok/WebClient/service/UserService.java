package kov.irok.WebClient.service;

import kov.irok.WebClient.entity.ChatMessage;
import kov.irok.WebClient.entity.User;
import kov.irok.WebClient.consoleServer.Connection;

import java.util.List;

public interface UserService {

    void addNewUser(User newUser);
    void registerUser(User user);
    String getMainMessage(User user);
    User getUserById(int id);
    List<String> allLogins();
    User findUser(User newUser);
    void addUserConnection(User user, Connection connection);
    void removeConnection(User user);
    void leaveUser(User user);
    void exitUser(User user);

    User getOpponent(User user);
    List<ChatMessage> getMessagesByChatId(int chatId);
    ChatMessage addMessage(String message, User user);
    List<ChatMessage> getAjaxMessages(int id) throws InterruptedException;

}
