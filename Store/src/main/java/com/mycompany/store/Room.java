package com.mycompany.store;

public class Room {
    
    private int number;
    private double area;
    private int beds;
    private boolean isRent;

    public Room(int number, double area, int beds) {
        this.number = number;
        this.area = area;
        this.beds = beds;
        this.isRent = false;
    }
    
    public int getNumber()
    {
        return this.number;
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
    
    public boolean getIsRent()
    {
        return this.isRent;
    }
    
    public void setIsRent(boolean rent)
    {
        this.isRent = rent;
    }
    
    public String toString()
    {
        return "The room nr " + this.number + " has " + this.area + " m2 and is designed for " + this.beds + " people.";
    }
    
}
