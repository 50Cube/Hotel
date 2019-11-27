package com.mycompany.store.Controllers;

import com.mycompany.store.Model.User;
import com.mycompany.store.Services.UserService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named(value = "addUserController")
@ConversationScoped
public class addUserController implements Serializable{

    @Inject
    private UserService userService;
    
    @Inject
    private Conversation conversation;
    
    private User user;
    
    public addUserController() {
    }
    
    @PostConstruct
    private void init() {
    }
    
    
}
