package com.mycompany.store.Model;


public class Admin extends User
{
    public Admin(String name, String surname, String login, String password, boolean active) {
        super(name, surname, login, password, active);
    }
    
    @Override
    public String getType() {
        return "Admin";
    }
}
