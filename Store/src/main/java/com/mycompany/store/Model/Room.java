package com.mycompany.store.Model;

public class Room {
    
    private int number;
    private double area;
    private int beds;

    public Room () {}
    
    public Room(int number, double area, int beds) {
        this.number = number;
        this.area = area;
        this.beds = beds;
    }
    
    public int getNumber()
    {
        return this.number;
    }
    
    public void setNumber(int number)
    {
        this.number = number;
    }
    
    public double getArea()
    {
        return this.area;
    }
    
    public void setArea(double area)
    {
        this.area = area;
    }
    
    public int getBeds()
    {
        return this.beds;
    }
    
    public void setBeds(int beds)
    {
        this.beds = beds;
    }
    
    @Override
    public String toString()
    {
        return "Room no. " + this.number + " has " + this.area + " m2 and is designed for " + this.beds + " people.";
    }
    
}
