package kov.irok.WebClient.controller;

import io.swagger.annotations.ApiOperation;
import kov.irok.WebClient.entity.Chat;
import kov.irok.WebClient.entity.ChatMessage;
import kov.irok.WebClient.entity.User;
import kov.irok.WebClient.service.AdminService;
import kov.irok.WebClient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

    @Autowired
    public AdminService adminService;

    @Autowired
    public UserService userService;

    @ApiOperation(value = "Returns all chats")
    @GetMapping("/chats")
    public List<Chat> getAllChats(){
        return adminService.getAllFullChats();
    }

    @ApiOperation(value = "Returns chat by chat id")
    @GetMapping("/chats/{chatId}")
    public Chat getChatById(@PathVariable int chatId){
        return adminService.getChatById(chatId);
    }

    @ApiOperation(value = "Returns all not busy users")
    @GetMapping("/freeUsers/{userType}")
    public List<User> getAllNotBusyUsers(@PathVariable User.UserType userType){
        return adminService.getAllNotBusyUsers(userType);
    }

    @ApiOperation(value = "Returns all messages of user")
    @GetMapping("/chatMessages/{userId}")
    public List<ChatMessage> getChatMessagesByUserId(@PathVariable int userId) {
        User user = userService.getUserById(userId);
        return userService.getMessagesByChatId(user.getChatId());
    }

    @ApiOperation(value = "Send message to opponent")
    @PostMapping("/chatMessages/{userId}")
    public ChatMessage sendMessage(@PathVariable int userId, @RequestBody String message){
        User user = userService.getUserById(userId);
        return userService.addMessage(message,user);
    }

    @ApiOperation(value = "Returns messages from message queue")
    @GetMapping("/getAjax/{id}")
    public List<ChatMessage> getAjax(@PathVariable int id) throws InterruptedException {
        return userService.getAjaxMessages(id);
    }

}
