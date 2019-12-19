package com.mycompany.store.Model;

public abstract class Rentable {
    private int number;

    public Rentable () {}
    
    public Rentable(int number) {
        this.number = number;
    }
    
    public int getNumber()
    {
        return this.number;
    }
    
    public void setNumber(int number)
    {
        this.number = number;
    }
}