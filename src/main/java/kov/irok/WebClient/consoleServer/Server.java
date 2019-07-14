package kov.irok.WebClient.consoleServer;

import kov.irok.WebClient.entity.User;
import kov.irok.WebClient.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Server implements ConnectionListener{

    final static Logger logger = Logger.getLogger(Server.class);

    @Autowired
    public UserService userService;

    @Override
    public synchronized void onConnectionReady(Connection connection) {
        connection.sendString("You connected to server Successfully");
    }

    @Override
    public synchronized void onReceiveString(Connection connection, String string) {
        if(connection.isLogined()) {
            User user = userService.getUserById(connection.getUserId());
            userService.addMessage(string, user);
        }else{
            while (!parseRequest(string,connection)){
                connection.sendString("Server connection error. Please try again!");
                logger.warn("Uncorrected request error");
                string = connection.getString();
            }
        }
    }

    @Override
    public synchronized void onDisconnect(Connection connection) {
        User user = userService.getUserById(connection.getUserId());
        if (user != null){
            userService.removeConnection(user);
            userService.exitUser(user);
            logger.info("Disconnect client " + user.getName());
        }else{
            logger.info("Disconnect client ");
        }
        connection.disconnect();
    }

    @Override
    public synchronized void onException(Connection connection, Exception e) {
        logger.error("Connection exception: " + e);
    }

    private synchronized boolean parseRequest(String request, Connection connection){
        String[] strings = request.split(":;");
        if(strings.length < 3 || strings.length > 5){
            return false;
        }else{
            return getCommand(strings, connection);
        }
    }

    private boolean getCommand(String[] strings, Connection connection) {

        if(strings.length == 3 & strings[0].equals("login")){
            return isLoginCommand(connection, strings[1], strings[2]);
        }else if(strings.length == 5 & strings[0].equals("register")){
            return isRegisterCommand(connection, strings);
        }else{
            return false;
        }
    }

    private boolean isLoginCommand(Connection connection, String login, String password){
        User newUser = new User();
        newUser.setLogin(login);
        newUser.setPassword(password);
        User user = userService.findUser(newUser);
        if (user == null) {
            connection.sendString("Wrong login. Please try again!");
            logger.warn("Uncorrected login error");
            return false;
        }else if(user.getId() == -1){
            connection.sendString("Wrong password. Please try again!");
            logger.warn("Uncorrected password error");
            return false;
        }else{
            userService.registerUser(user);
            connection.setLogined(true);
            connection.setUserId(user.getId());
            userService.addUserConnection(user,connection);
            //user.setConnection(connection);
            connection.sendString("Welcome " + user.getType() + " " + user.getName());
            connection.sendString(userService.getMainMessage(user));
            logger.info("User " + user.getName() + " are logined.");
            return true;
        }
    }

    private boolean isRegisterCommand(Connection connection, String [] strings){
        User newUser = new User();
        if(strings[1].equals("agent")){
            newUser.setType(User.UserType.AGENT);
        }else if(strings[1].equals("client")){
            newUser.setType(User.UserType.CLIENT);
        }else{
            return false;
        }
        newUser.setName(strings[2]);
        newUser.setLogin(strings[3]);
        newUser.setPassword(strings[4]);
        if (userService.allLogins().contains(newUser.getLogin())) {
            connection.sendString("This login already exists. Please try again!");
            logger.warn("Login already exists error");
            return false;
        }
        userService.addNewUser(newUser);
        connection.sendString("Registration completed successfully");
        logger.info("Register new user " + newUser.getName());
        return true;
    }

}
