package kov.irok.WebClient.component;

import kov.irok.WebClient.entity.Chat;
import kov.irok.WebClient.entity.User;

import java.util.List;

public interface AdminEvents {
    List<Chat> getAllFullChats();
    Chat getChatById(int chatId);
    List<User> getAllNotBusyUsers(User.UserType userType);
}
