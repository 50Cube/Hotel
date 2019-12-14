package com.mycompany.store.Repositories;

import com.mycompany.store.Model.Client;
import com.mycompany.store.Model.Admin;
import com.mycompany.store.Model.Manager;
import com.mycompany.store.Model.User;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named(value = "userRepository")
@ApplicationScoped
public class UserRepository {

    private Map<String, User> users;
    
    public UserRepository() {
        users = new HashMap<>();
    }
    
    public Map<String, User> getUsers()
    {
        return new HashMap<>(users);
    }
    
    public User getUser(String login)
    {
        return this.users.get(login);
    }
    
    public int getUsersAmount()
    {
        return users.size();
    }
    
    public synchronized void addUser(User user) throws Exception
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
    
    public synchronized void updateUser(String id, String newPassword, String newName, String newSurname)
    {
        for(User user : users.values())
            if (user.getLogin().equals(id))
            {
                user.setPassword(newPassword);
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
    
    public Map<String, User> getFilteredUsers(String input) {
        Map<String, User> tmp = new HashMap<>();
        
        users.values().stream().filter((user) -> ((user.toFilterString().toLowerCase()).contains(input.trim()))).forEachOrdered((user) -> {
            tmp.put(user.getLogin(), user);
        });
        
        return tmp;
    }
    
    
    @PostConstruct
    private void initDataUser()
    {
        Admin admin = new Admin("Norbert", "Gierczak", "admin", "", true);
        Manager manager = new Manager("Marcin", "Krasucki", "manager", "", true);
        Client client1 = new Client("Gabriel", "Nowak", "client1", "", true);
        Client client2 = new Client("Jakub", "Bogdan", "client2", "", true);
        Client client3 = new Client("Szymon", "Rutkowski", "client3", "", false);
        
        users.put(admin.getLogin(), admin);
        users.put(manager.getLogin(), manager);
        users.put(client1.getLogin(), client1);
        users.put(client2.getLogin(), client2);
        users.put(client3.getLogin(), client3);
    }
}