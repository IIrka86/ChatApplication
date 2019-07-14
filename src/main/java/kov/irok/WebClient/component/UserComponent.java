package kov.irok.WebClient.component;

import kov.irok.WebClient.entity.Chat;
import kov.irok.WebClient.entity.ChatMessage;
import kov.irok.WebClient.entity.User;
import kov.irok.WebClient.consoleServer.Connection;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserComponent implements UserEvents, AdminEvents{

    private int chatId = 1;
    private final List<Chat> chats = new ArrayList<>();

    private int userId = 1;
    private final List<User> users = new ArrayList<>();

    private final Map<String,List<ChatMessage>> messageQueue = new HashMap<>();

    private final Map<String,Connection> userConnections = new HashMap<>();

    //////////////Add and register new user methods////////////////////////////////

    public synchronized void addNewUser(User newUser) {
        newUser.setId(userId++);
        users.add(newUser);
    }

    public void register(User user) {
        if(user.getType()== User.UserType.AGENT){
            registerAgent(user);
        }else{
            registerClient(user);
        }
    }

    private synchronized void registerClient(User user) {
        Chat chat;
        Optional<Chat> optionalChat = chats.stream().filter(a->a.getClient()==null).findFirst();
        if(optionalChat.isPresent()) {
            chat = optionalChat.get();
            user.setChatId(chat.getId());
            chat.setClient(user);
        }else{
            user.setChatId(chatId);
            chat = new Chat(chatId++,null,user);
            chats.add(chat);
        }
    }

    private synchronized void registerAgent(User user) {
        Chat chat;
        Optional<Chat> optionalChat = chats.stream().filter(a->a.getAgent()==null).findFirst();
        if(optionalChat.isPresent()) {
            chat = optionalChat.get();
            user.setChatId(chat.getId());
            chat.setAgent(user);
        }else{
            user.setChatId(chatId);
            chat = new Chat(chatId++,user,null);
            chats.add(chat);
        }
    }

    public User getUserById(int id) {
        synchronized (users) {
            if(id <= users.size()) {
                return users.get(id - 1);
            }else {
                return null;
            }
        }
    }

    public List<String> allLogins() {
        synchronized (users){
            return users.stream().map(User::getLogin)
                    .collect(Collectors.toList());
        }
    }

    public User findUser(User newUser) {
        Optional<User> opUser;
        synchronized (users) {
            opUser = users.stream().filter(a -> a.getLogin().equals(newUser.getLogin())).findFirst();
        }
        if(opUser.isPresent()) {
            User user = opUser.get();
            if(!user.getPassword().equals(newUser.getPassword())) {
                user.setId(-1);
            }
            return user;
        }else{
            return null;
        }
    }

    public void addUserConnection(User user, Connection connection) {
        synchronized (userConnections){
            userConnections.put(String.valueOf(user.getId()), connection);
        }
    }

    public Connection getUserConnection(User user) {
        synchronized (userConnections){
            return userConnections.get(String.valueOf(user.getId()));
        }
    }

    public void removeConnection(User user) {
        synchronized (userConnections){
            userConnections.remove(String.valueOf(user.getId()));
        }
    }

    //////////////////Show, send and get messages methods///////////////////////////////////////////////

    public synchronized List<ChatMessage> getMessagesByChatId(int chatId) {
        return chats.get(chatId-1).getMessages();
    }

    public synchronized void addMessageToChat(ChatMessage message, int chatId) {
        chats.get(chatId-1).addMessage(message);
    }

    public List<ChatMessage> getAjaxMessages(int getterId) throws InterruptedException {
        List<ChatMessage> messages;
        synchronized (messageQueue) {
            messages = messageQueue.get(String.valueOf(getterId));
            while (messages == null){
                messageQueue.wait();
                //messages = messageQueue.get(String.valueOf(getterId));
            }
            if (messages != null){
                messageQueue.remove(String.valueOf(getterId));
            }
        }
        return messages;
    }

    public void addMessageQueue(User user, ChatMessage chatMessage) {
        synchronized (messageQueue) {
            List<ChatMessage> messages = messageQueue.get(String.valueOf(getOpponent(user).getId()));
            if (messages==null){
                messages = new ArrayList<>();
                messages.add(chatMessage);
                messageQueue.put(String.valueOf(getOpponent(user).getId()), messages);
            }else{
                messages.add(chatMessage);
            }
            messageQueue.notifyAll();
        }
    }

    public User getOpponent(User user){
        if(user.getType() == User.UserType.AGENT){
            return getChatClient(user.getChatId());
        }else{
            return getChatAgent(user.getChatId());
        }
    }

    private User getChatClient(int chatId) {
        User client = null;
        synchronized (chats) {
            if (chats.size() >= chatId) {
                client = chats.get(chatId - 1).getClient();
            }
        }
        return  client;
    }

    private User getChatAgent(int chatId) {
        User agent = null;
        synchronized (chats) {
            if (chats.size() >= chatId) {
                agent = chats.get(chatId - 1).getAgent();
            }
        }
        return  agent;
    }

    ///////////////////Show all chats and users info methods////////////////////////

    public synchronized List<Chat> getAllFullChats() {
        return chats.stream().filter(a->a.getAgent()!=null)
                .filter(a->a.getClient()!=null).collect(Collectors.toList());
    }

    public synchronized Chat getChatById(int chatId) {
        return chats.get(chatId-1);
    }

    public synchronized List<User> getAllNotBusyUsers(User.UserType userType) {
        if(userType == User.UserType.AGENT){
            return chats.stream().filter(a->a.getClient()==null)
                    .map(Chat::getAgent).collect(Collectors.toList());
        }else{
            return chats.stream().filter(a->a.getAgent()==null)
                    .map(Chat::getClient).collect(Collectors.toList());
        }
    }

}
