package com.mycompany.store;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

@Named(value = "client")
@Dependent
public class Client {

    private int id;
    private String name;
    private String surname;
    
    public Client(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
    
    public int getId()
    {
        return this.id;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public String getSurname()
    {
        return this.surname;
    }
    
}
