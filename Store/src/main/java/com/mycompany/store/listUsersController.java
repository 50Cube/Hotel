package com.mycompany.store;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;


@Named(value = "listUsersController")
@ViewScoped
public class listUsersController {

    @Inject
    private UserService userService;
    
    private Map<String,User> users;
    
    public listUsersController() {
    }
    
    @PostConstruct
    public void loadUsers()
    {
        users = userService.getUsers();
    }
    
    public Map<String, User> getUsers()
    {
        return users;
    }
}
