package com.mycompany.store.Model;

public class Sauna {

    private int number;
    private double pricePerHour;
    
    public Sauna() {}
    
    public Sauna(int number, double price) {
        this.number = number;
        this.pricePerHour = price;
    }
    
    public int getNumber()
    {
        return this.number;
    }
    
    public void setNumber(int number)
    {
        this.number = number;
    }
    
    public double getPricePerHour()
    {
        return this.pricePerHour;
    }
    
    public void setPricePerHour(double price)
    {
        this.pricePerHour = price;
    }
    
    @Override
    public String toString()
    {
        return "Sauna no. " + this.number + " costs " + this.pricePerHour + " per hour.";
    }
}
