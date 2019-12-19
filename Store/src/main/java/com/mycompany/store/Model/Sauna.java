package com.mycompany.store.Model;

public class Sauna extends Rentable {
    private double pricePerHour;
    
    public Sauna() {}
    
    public Sauna(int number, double price) {
        super(number);
        this.pricePerHour = price;
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
        return "Sauna no. " + this.getNumber() + " costs " + this.pricePerHour + " per hour.";
    }
}
