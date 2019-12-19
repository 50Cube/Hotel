package com.mycompany.store.Model;

public class Room extends Rentable {
    private double area;
    private int beds;

    public Room () {}
    
    public Room(int number, double area, int beds) {
        super(number);
        this.area = area;
        this.beds = beds;
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
        return "Room no. " + this.getNumber() + " has " + this.area + " m2 and is designed for " + this.beds + " people.";
    }
    
}
