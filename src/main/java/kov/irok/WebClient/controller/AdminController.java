package kov.irok.WebClient.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kov.irok.WebClient.entity.User;
import kov.irok.WebClient.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chatsInfo")
@Api(value = "Check chats information endpoints")
public class AdminController {

    @Autowired
    public AdminService adminService;


    ///////////////Web interface////////////////////////////////////////////////

    @ApiOperation(value = "Returns all chats info")
    @GetMapping("/")
    public String getAllChats(Model model){
        model.addAttribute("chats", adminService.getAllFullChats());
        return "webApi/allChatsInfo";
    }

    @ApiOperation(value = "Return chat info")
    @GetMapping("/chatMessages/{chatId}")
    public String getChat(Model model, @PathVariable int chatId){
        model.addAttribute("chat", adminService.getChatById(chatId));
        return "webApi/chatInfo";
    }

    @ApiOperation(value = "Returns all not busy users")
    @GetMapping("/freeUsers/{userType}")
    public String getAllNotBusyAgents(Model model, @PathVariable User.UserType userType){
        model.addAttribute("type",userType);
        model.addAttribute("users", adminService.getAllNotBusyUsers(userType));
        return "webApi/allFreeUsers";
    }


    ////////////////Rest Interface//////////////////////////////////////////////////

    @ApiOperation(value = "Returns all chats information")
    @GetMapping("/restApi/allChats")
    public String showChatsInfo(){
        return "restApi/allChatsInfo";
    }

    @ApiOperation(value = "Returns chat information")
    @GetMapping("/restApi/chatInfo/{chatId}")
    public String showChatInfo(Model model,@PathVariable int chatId) {
        model.addAttribute("chatId",chatId);
        return "restApi/chatInfo";
    }

    @ApiOperation(value = "Returns all not busy users")
    @GetMapping("/restApi/freeUsers/{userType}")
    public String showAllFreeUsers(Model model, @PathVariable User.UserType userType){
        model.addAttribute("type",userType);
        return "restApi/allFreeUsers";
    }

}
