package com.mycompany.store;

import javax.inject.Named;
import javax.enterprise.context.Dependent;


@Named(value = "manager")
@Dependent
public class Manager extends User
{
    public Manager(int id, String name, String surname, String login, String password, boolean active) {
        super(id, name, surname, login, password, active);
    }
    
}
