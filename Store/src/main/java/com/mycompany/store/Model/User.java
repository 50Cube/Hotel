package com.mycompany.store.Model;

public class User {
    private String login;
    private String password;
    private String name;
    private String surname;
    private boolean isActive;
    
    public User(String name, String surname, String login, String password, boolean active) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.isActive = active;
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
    
    public String getPassword()
    {
        return this.password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
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
