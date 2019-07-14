package kov.irok.WebClient.service;

import kov.irok.WebClient.component.UserEvents;
import kov.irok.WebClient.entity.ChatMessage;
import kov.irok.WebClient.entity.User;
import kov.irok.WebClient.component.UserComponent;
import kov.irok.WebClient.consoleServer.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static kov.irok.WebClient.entity.ChatMessage.MessageType.*;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    public UserEvents userEvents;

    //////////////Add and register new user methods////////////////////////////////

    @Override
    public void addNewUser(User newUser){userEvents.addNewUser(newUser);
    }

    @Override
    public void registerUser(User user) {
        userEvents.register(user);
        User opponent = getOpponent(user);
        if(opponent != null && userEvents.getUserConnection(opponent) != null){
            userEvents.getUserConnection(opponent) .sendString(getMainMessage(opponent));
        }
    }

    @Override
    public String getMainMessage(User user) {
        User opponent = getOpponent(user);
        if(user.getType() == User.UserType.AGENT){
            if (opponent == null) {
                return "Do not have clients now. Please wait.";
            }else{
                return "You connected to client " + opponent.getName();
            }
        }else{
            if (opponent == null) {
                return "All agents is busy now. Please wait.";
            }else{
                return "You connected to agent " + opponent.getName();
            }
        }
    }

    @Override
    public User getUserById(int id) {
        return userEvents.getUserById(id);
    }

    @Override
    public List<String> allLogins() {
        return userEvents.allLogins();
    }

    @Override
    public User findUser(User newUser) {
        return userEvents.findUser(newUser);
    }

    @Override
    public void addUserConnection(User user, Connection connection) {
        userEvents.addUserConnection(user, connection);
    }

    @Override
    public void removeConnection(User user) {
        userEvents.removeConnection(user);
    }

    @Override
    public void leaveUser(User user) {

    }

    @Override
    public void exitUser(User user) {

    }

    //////////////////Show, send and get messages methods///////////////////////////////////////////////

    @Override
    public User getOpponent(User user) {
        return userEvents.getOpponent(user);
    }

    @Override
    public List<ChatMessage> getMessagesByChatId(int chatId) {
        return userEvents.getMessagesByChatId(chatId);
    }

    @Override
    public ChatMessage addMessage(String message, User user) {
        ChatMessage chatMessage = parseMessage(message,user);
        switch (chatMessage.getType()){
            case LEAVE: {
                //not finished yet
                leaveUser(user); break;
            }
            case EXIT:{
                //not finished yet
                exitUser(user); break;
            }
            case CHAT:{
                userEvents.addMessageToChat(chatMessage,user.getChatId());
                Connection sendTo = userEvents.getUserConnection(getOpponent(user));
                if(sendTo == null) {
                    userEvents.addMessageQueue(user, chatMessage);
                }else {
                    sendToConnection(sendTo, "" + user.getName() + " : " + chatMessage.getContent());
                }
                break;
            }
        }
        return chatMessage;
    }

    private void sendToConnection(Connection sendTo, String string) {
        sendTo.sendString(string);
    }

    private ChatMessage parseMessage(String message, User user){
        ChatMessage chatMessage = new ChatMessage(new Date());
        if(message.equals("/leave")){
            chatMessage.setType(LEAVE);
        }else if(message.equals("/exit")){
            chatMessage.setType(EXIT);
        }else{
            chatMessage.setType(CHAT);
            chatMessage.setContent(message);
            chatMessage.setSender(user);
        }
        return chatMessage;
    }

    @Override
    public List<ChatMessage> getAjaxMessages(int id) throws InterruptedException {
        return userEvents.getAjaxMessages(id);
    }
}
