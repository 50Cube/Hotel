package com.mycompany.store.Services;

import com.mycompany.store.Model.Admin;
import com.mycompany.store.Model.Client;
import com.mycompany.store.Model.Manager;
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
    
    public void addClient(String login, String password, String name, String surname, boolean active) throws Exception {
            userRepository.addUser(new Client(login,password,name,surname,active));
    }
    
    public void addManager(String login, String password, String name, String surname, boolean active) throws Exception {
            userRepository.addUser(new Manager(login,password,name,surname,active));
    }
    
    public void addAdmin(String login, String password, String name, String surname, boolean active) throws Exception {
            userRepository.addUser(new Admin(login,password,name,surname,active));
    }
    
    public void updateUser(String id, String newPassword, String newName, String newSurname)
    {
        userRepository.updateUser(id, newPassword, newName, newSurname);
    }
    
    public void activateUser(String login)
    {
        userRepository.activateUser(login);
    }
    
    public void deactivateUser(String login)
    {
        userRepository.deactivateUser(login);
    }
    
    public Map<String, User> getFilterUsers(String input) {
        return userRepository.getFilteredUsers(input);
    }
}