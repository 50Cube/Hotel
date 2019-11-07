package com.mycompany.store;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

@Named(value = "userRepository")
@Dependent
public class UserRepository {

    private Map<Integer, Client> users;
    
    public UserRepository() {
        users = new HashMap<>();
    }
    
    public Map<Integer, Client> getUsers()
    {
        return users;
    }
    
    public Client getUser(int id)
    {
        return this.users.get(id);
    }
    
    public int getUsersAmount()
    {
        return users.size();
    }
    
    public void addUser(Client client)
    {
        users.put(client.getId(), client);
    }
    
    public void updateUser(int id, String newName, String newSurname)
    {
        for(User user : users.values())
            if (user.getId() == id)
            {
                user.setName(newName);
                user.setSurname(newSurname);
            }
    }
    
    public void activateUser(int id)
    {
        for(User user : users.values())
            if (user.getId() == id && !user.getIsActive())
                user.setIsActive(true);
    }
    
    public void deactivateUser(int id)
    {
        for(User user : users.values())
            if (user.getId() == id && user.getIsActive())
                user.setIsActive(false);
    }
}
