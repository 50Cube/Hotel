package com.mycompany.store.Repositories;

import com.mycompany.store.Model.Client;
import com.mycompany.store.Model.Admin;
import com.mycompany.store.Model.Manager;
import com.mycompany.store.Model.User;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
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
    
    
    @PostConstruct
    private void initDataUser()
    {
        Admin admin = new Admin("Norbert", "Gierczak", "dis", "", true);
        Manager manager = new Manager("Marcin", "Krasucki", "jd", "", true);
        Client client1 = new Client("Gabriel", "Nowak", "gabor", "", true);
        Client client2 = new Client("Jakub", "Bogdan", "herb", "", true);
        Client client3 = new Client("Szymon", "Rutkowski", "4", "", false);
        
        users.put(admin.getLogin(), admin);
        users.put(manager.getLogin(), manager);
        users.put(client1.getLogin(), client1);
        users.put(client2.getLogin(), client2);
        users.put(client3.getLogin(), client3);
    }
}