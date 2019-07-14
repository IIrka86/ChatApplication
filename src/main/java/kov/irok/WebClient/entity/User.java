package kov.irok.WebClient.entity;

import io.swagger.annotations.ApiModelProperty;

public class User {

    @ApiModelProperty(notes = "id of the User")
    private int id;
    @ApiModelProperty(notes = "id of the User chat")
    private int chatId;
    @ApiModelProperty(notes = "name of the User")
    private String name;
    @ApiModelProperty(notes = "login of the User")
    private String login;
    @ApiModelProperty(notes = "password of the User")
    private String password;
    @ApiModelProperty(notes = "type of the User")
    private UserType type;

    public enum UserType{
        AGENT,CLIENT
    }

    public User() {
    }

    public User(int id, String name, UserType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
