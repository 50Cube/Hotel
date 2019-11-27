package com.mycompany.store.Services;

import com.mycompany.store.Repositories.UserRepository;
import com.mycompany.store.Model.User;
import java.io.Serializable;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;


@Named(value = "userService")
@Dependent
public class UserService implements Serializable{

    @Inject
    private UserRepository userRepository;
    
    public UserService() {
    }
    
    
    public Map<String, User> getUsers()
    {
        return userRepository.getUsers();
    }
    
    public User getUser(String login)
    {
        return userRepository.getUser(login);
    }
    
    public int getUsersAmount()
    {
        return userRepository.getUsersAmount();
    }
    
    public void addUser(User user) throws Exception
    {
        userRepository.addUser(user);
    }
    
    public void updateUser(String id, String newName, String newSurname)
    {
        userRepository.updateUser(id, newName, newSurname);
    }
    
    public void activateUser(String login)
    {
        userRepository.activateUser(login);
    }
    
    public void deactivateUser(String login)
    {
        userRepository.deactivateUser(login);
    }
}