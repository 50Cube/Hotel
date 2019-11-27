package com.mycompany.store;

import com.mycompany.store.Model.Room;
import com.mycompany.store.Model.Client;
import java.util.Calendar;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import java.util.UUID;


@Named(value = "rent")
@Dependent
public class Rent {

    private UUID id = UUID.randomUUID();
    private Room room;
    private Client client;
    private Calendar rentStart;
    private Calendar rentStop;
    
    public Rent(Room room, Client client, Calendar start, Calendar stop) {
        this.room = room;
        this.client = client;
        this.rentStart = start;
        this.rentStop = stop;
    }
    
    public UUID getId()
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
