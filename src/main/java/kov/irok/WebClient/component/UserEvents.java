package kov.irok.WebClient.component;

import kov.irok.WebClient.consoleServer.Connection;
import kov.irok.WebClient.entity.ChatMessage;
import kov.irok.WebClient.entity.User;
import java.util.List;

public interface UserEvents {

    void addNewUser(User newUser);
    void register(User user);
    User getUserById(int id);
    List<String> allLogins();
    User findUser(User newUser);

    void addUserConnection(User user, Connection connection);
    Connection getUserConnection(User user);
    void removeConnection(User user);

    User getOpponent(User user);
    List<ChatMessage> getMessagesByChatId(int chatId);
    void addMessageToChat(ChatMessage message, int chatId);
    List<ChatMessage> getAjaxMessages(int id) throws InterruptedException;
    void addMessageQueue(User user, ChatMessage chatMessage);
}
