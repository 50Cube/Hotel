package com.mycompany.store;

import java.util.UUID;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

@Named(value = "user")
@Dependent
public class User {

    private UUID id = UUID.randomUUID();
    private String name;
    private String surname;
    private String login;
    private String password;
    private boolean isActive;
    
    public User(String name, String surname, String login, String password, boolean active) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.isActive = active;
    }
    
    public UUID getId()
    {
        return this.id;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getSurname()
    {
        return this.surname;
    }
    
    public void setSurname(String surname)
    {
        this.surname = surname;
    }
    
    public String getLogin()
    {
        return this.login;
    }
    
    public boolean getIsActive()
    {
        return this.isActive;
    }
    
    public void setIsActive(boolean active)
    {
        this.isActive = active;
    }
    
    public String toString()
    {
        return "User details:\nName: " + this.getName() + "\nSurname: " + this.getSurname() + "\nLogin: " + this.getLogin() + "\nActive: " + this.getIsActive();
    }
}
