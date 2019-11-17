package com.mycompany.store;

public class Sauna {

    private int number;
    private double pricePerHour;
    private boolean isReserved;
    
    public Sauna(int number, double price) {
        this.number = number;
        this.pricePerHour = price;
        this.isReserved = false;
    }
    
    public int getNumber()
    {
        return this.number;
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
}
