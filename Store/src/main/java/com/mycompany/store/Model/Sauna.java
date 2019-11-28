package com.mycompany.store.Model;

public class Sauna {

    private int number;
    private double pricePerHour;
    private boolean isReserved;
    
    public Sauna() {}
    
    public Sauna(int number, double price) {
        this.number = number;
        this.pricePerHour = price;
        this.isReserved = false;
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
    
    public boolean getIsReserved()
    {
        return this.isReserved;
    }
    
    public void setIsReserved(boolean is)
    {
        this.isReserved = is;
    }
    
    @Override
    public String toString()
    {
        return "Sauna no. " + this.number + " costs " + this.pricePerHour + " per hour.";
    }
}
