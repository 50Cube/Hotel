package com.mycompany.store;

import javax.inject.Named;
import javax.enterprise.context.Dependent;


@Named(value = "admin")
@Dependent
public class Admin extends User
{
    public Admin(int id, String name, String surname, String login, String password, boolean active) {
        super(id, name, surname, login, password, active);
    }
    
}
