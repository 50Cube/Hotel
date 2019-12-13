package com.mycompany.store.Controllers;

import com.mycompany.store.Services.UserService;
import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named(value = "addUserController")
@ConversationScoped
public class addUserController implements Serializable{

    private String userType;
    private String login;
    private String password;
    private String name;
    private String surname;
    private boolean isActive;
    
    @Inject
    private UserService userService;
    
    @Inject
    private Conversation conversation;
    
    public addUserController() {
    }
    
    public void setUserType(String type) {
        this.userType = type;
    }
    
    public String getUserType() {
        return this.userType;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    public String addUser() {
        if(!conversation.isTransient())
            conversation.end();
        conversation.begin();
        return "addUser";
    }
    
    public String addUserConfirm() throws Exception {
        if (userType.equals("Client"))
            userService.addClient(name, surname, login, password, isActive);
        else if (userType.equals("Manager"))
            userService.addManager(name, surname, login, password, isActive);
        else if (userType.equals("Admin"))
            userService.addAdmin(name, surname, login, password, isActive);
        
        conversation.end();
        return "home";
    }
}
