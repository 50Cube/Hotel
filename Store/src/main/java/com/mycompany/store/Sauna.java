package com.mycompany.store;

import javax.inject.Named;
import javax.enterprise.context.Dependent;


@Named(value = "sauna")
@Dependent
public class Sauna {

    private double pricePerHour;
    private boolean isReserved;
    
    public Sauna(double price) {
        this.pricePerHour = price;
        this.isReserved = false;
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
