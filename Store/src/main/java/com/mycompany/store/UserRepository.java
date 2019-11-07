package com.mycompany.store;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

@Named(value = "userRepository")
@Dependent
public class UserRepository {

    private Map<UUID, Client> users;
    
    public UserRepository() {
        users = new HashMap<>();
    }
    
    public Map<UUID, Client> getUsers()
    {
        return users;
    }
    
    public Client getUser(UUID id)
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
    
    public void updateUser(UUID id, String newName, String newSurname)
    {
        for(User user : users.values())
            if (user.getId().equals(id))
            {
                user.setName(newName);
                user.setSurname(newSurname);
            }
    }
    
    public void activateUser(UUID id)
    {
        for(User user : users.values())
            if (user.getId().equals(id) && !user.getIsActive())
                user.setIsActive(true);
    }
    
    public void deactivateUser(UUID id)
    {
        for(User user : users.values())
            if (user.getId().equals(id) && user.getIsActive())
                user.setIsActive(false);
    }
}
