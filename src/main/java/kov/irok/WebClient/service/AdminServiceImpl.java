package kov.irok.WebClient.service;

import kov.irok.WebClient.component.AdminEvents;
import kov.irok.WebClient.entity.Chat;
import kov.irok.WebClient.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    public AdminEvents adminEvents;

    ///////////////////Show all chats and users info methods////////////////////////

    @Override
    public List<Chat> getAllFullChats() {
        return adminEvents.getAllFullChats();
    }

    @Override
    public Chat getChatById(int chatId) {
        return adminEvents.getChatById(chatId);
    }

    @Override
    public List<User> getAllNotBusyUsers(User.UserType userType) {
        return adminEvents.getAllNotBusyUsers(userType);
    }
}
