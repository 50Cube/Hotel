package com.mycompany.store;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

@Named(value = "userRepository")
@Dependent
public class UserRepository {

    private Map<String, User> users;
    
    public UserRepository() {
        users = new HashMap<>();
    }
    
    public Map<String, User> getUsers()
    {
        return users;
    }
    
    public User getUser(String login)
    {
        return this.users.get(login);
    }
    
    public int getUsersAmount()
    {
        return users.size();
    }
    
    public void addUser(User user) throws Exception
    {
        boolean tmp = false;
        for(User u : users.values())
            if(u.getLogin().equals(user.getLogin()))
            {
                tmp = true;
                break;
            }
        
        if(tmp)
            throw new Exception("User already exists");
        else users.put(user.getLogin(), user);
    }
    
    public void updateUser(String id, String newName, String newSurname)
    {
        for(User user : users.values())
            if (user.getLogin().equals(id))
            {
                user.setName(newName);
                user.setSurname(newSurname);
            }
    }
    
    public void activateUser(String login)
    {
        for(User user : users.values())
            if (user.getLogin().equals(login) && !user.getIsActive())
                user.setIsActive(true);
    }
    
    public void deactivateUser(String login)
    {
        for(User user : users.values())
            if (user.getLogin().equals(login) && user.getIsActive())
                user.setIsActive(false);
    }
}
