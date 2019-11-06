package com.mycompany.store;

import java.util.Calendar;
import javax.inject.Named;
import javax.enterprise.context.Dependent;


@Named(value = "rent")
@Dependent
public class Rent {

    private int id;
    private Room room;
    private Client client;
    private Calendar rentStart;
    private Calendar rentStop;
    
    public Rent(int id, Room room, Client client, Calendar start, Calendar stop) {
        this.id = id;
        this.room = room;
        this.client = client;
        this.rentStart = start;
        this.rentStop = stop;
    }
    
    public int getId()
    {
        return this.id;
    }
    
    public Room getRoom()
    {
        return this.room;
    }
    
    public Client getClient()
    {
        return this.client;
    }
    
    public Calendar getRentStart()
    {
        return this.rentStart;
    }
    
    public void setRentStart(Calendar date)
    {
        this.rentStart = date;
    }
    
    public Calendar getRentStop()
    {
        return this.rentStop;
    }
    
    public void setRentStop(Calendar date)
    {
        this.rentStop = date;
    }
}
