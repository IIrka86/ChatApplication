package kov.irok.WebClient.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kov.irok.WebClient.entity.ChatMessage;
import kov.irok.WebClient.entity.User;
import kov.irok.WebClient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@Api(value = "Client and Agent use chat endpoints")
public class MainController {

    @Autowired
    public UserService userService;

//    @Autowired
//    public Server server;

    ///////////////Web Interface/////////////////////////////////////

    @ApiOperation(value = "Returns Login page")
    @GetMapping("/")
    public String showLoginPage(){
        return "webApi/loginPage";
    }

    @ApiOperation(value = "Returns Login page")
    @GetMapping("/{messNumber}")
    public String showLoginPage(Model model, @PathVariable int messNumber){
        if(messNumber == 1) model.addAttribute("message","Wrong login");
        if(messNumber == 2) model.addAttribute("message","Wrong password");
        return "webApi/loginPage";
    }

    @ApiOperation(value = "Returns Register page")
    @GetMapping("/register")
    public String showRegisterPage(){
        return "webApi/registerPage";
    }

    @ApiOperation(value = "Returns Register page")
    @GetMapping("/register/{messNumber}")
    public String showRegisterPage(Model model, @PathVariable int messNumber){
        if(messNumber == 1) model.addAttribute("message","User with the same LOGIN already exists");
        return "webApi/registerPage";
    }

    @ApiOperation(value = "Returns user chat page")
    @GetMapping("/chat")
    public String showUserPage(Model model, @RequestParam ("id") int id) {
        User user = userService.getUserById(id);
        model.addAttribute("user",user);
        model.addAttribute("mainMessage", userService.getMainMessage(user));
        model.addAttribute("messages", userService.getMessagesByChatId(user.getChatId()));
        return "webApi/userPage";
    }

    @ModelAttribute
    public User readUser(){
        return new User();
    }

    @ApiOperation(value = "Login user")
    @PostMapping("/login")
    public String doLogin(@ModelAttribute("user") User newUser) {

        if (newUser.getLogin().isEmpty()||newUser.getPassword().isEmpty()) {
            return "webApi/loginPage";
        }
        User user = userService.findUser(newUser);
        if (user == null) {
            return "redirect:/1";
        }else if(user.getId() == -1){
            return "redirect:/2";
        }else{
            userService.registerUser(user);
            return "redirect:/chat?id=" + user.getId();
        }
    }

    @ApiOperation(value = "Register new User")
    @PostMapping("/register/registerUser")
    public String registerNewUser(@ModelAttribute("user") User newUser) {

        if (newUser.getLogin().isEmpty()||newUser.getPassword().isEmpty() || newUser.getName().isEmpty()) {
            return "webApi/registerPage";
        }
        if (userService.allLogins().contains(newUser.getLogin())) {
            return "redirect:/register/1";
        }
        userService.addNewUser(newUser);
        return "redirect:/";
    }

    @ModelAttribute
    public ChatMessage readMessage(){
        return new ChatMessage();
    }

    @ApiOperation(value = "Send message to opponent")
    @PostMapping("/sendMessage")
    public String sendMessage(@ModelAttribute ("newMessage")String message,
                              @ModelAttribute ("user") User newUser){
        User user = userService.getUserById(newUser.getId());
        userService.addMessage(message,user);
        return "redirect:/chat?id="+user.getId();
    }


    //////////////Rest Interface////////////////////////////////////////////////////


    @ApiOperation(value = "Returns Login page")
    @GetMapping("/restApi")
    public String showRestLoginPage(){
        return "restApi/loginPage";
    }

    @ApiOperation(value = "Returns Login page")
    @GetMapping("/restApi/{messNumber}")
    public String showRestLoginPage(Model model, @PathVariable int messNumber){
        if(messNumber == 1) model.addAttribute("message","Wrong login");
        if(messNumber == 2) model.addAttribute("message","Wrong password");
        return "restApi/loginPage";
    }

    @ApiOperation(value = "Returns Register page")
    @GetMapping("/restApi/register")
    public String showRestRegisterPage(){
        return "restApi/registerPage";
    }

    @ApiOperation(value = "Returns Register page")
    @GetMapping("/restApi/register/{messNumber}")
    public String showRestRegisterPage(Model model, @PathVariable int messNumber){
        if(messNumber == 1) model.addAttribute("message","User with the same LOGIN already exists");
        return "restApi/registerPage";
    }

    @ApiOperation(value = "Login user")
    @PostMapping("/restApi/loginRest")
    public String doRestLogin(@ModelAttribute("user") User newUser) {

        if (newUser.getLogin().isEmpty()||newUser.getPassword().isEmpty()) {
            return "restApi/loginPage";
        }
        User user = userService.findUser(newUser);
        if (user == null) {
            return "redirect:/restApi/1";
        }else if(user.getId() == -1){
            return "redirect:/restApi/2";
        }else{
            userService.registerUser(user);
            return "redirect:/restApi/chatMessages/"+ user.getId();
        }
    }

    @ApiOperation(value = "Register new User")
    @PostMapping("/restApi/register/registerUserRest")
    public String registerNewRestUser(@ModelAttribute("user") User newUser) {

        if (newUser.getLogin().isEmpty()||newUser.getPassword().isEmpty() || newUser.getName().isEmpty()) {
            return "restApi/registerPage";
        }
        if (userService.allLogins().contains(newUser.getLogin())) {
            return "redirect:/restApi/register/1";
        }
        userService.addNewUser(newUser);
        return "redirect:/restApi";
    }

    @ApiOperation(value = "Returns user page")
    @GetMapping("/restApi/chatMessages/{id}")
    public String showRestUser(Model model, @PathVariable int id){
        User user = userService.getUserById(id);
        model.addAttribute("user",user);
        model.addAttribute("mainMessage", userService.getMainMessage(user));
        return "restApi/userPage";
    }
}
