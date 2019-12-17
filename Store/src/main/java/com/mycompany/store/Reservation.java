package com.mycompany.store;

import com.mycompany.store.Model.Sauna;
import com.mycompany.store.Model.Client;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class Reservation {

    private UUID id = UUID.randomUUID();
    private Sauna sauna;
    private Client client;
    private Calendar reservationStart;
    private Calendar reservationStop;
    
    public Reservation() {}
    
    public Reservation(Sauna sauna, Client client, Calendar from, Calendar to) {
        this.sauna = sauna;
        this.client = client;
        this.reservationStart = from;
        this.reservationStop = to;
    }
    
    public UUID getId()
    {
        return this.id;
    }
    
    public Sauna getSauna()
    {
        return this.sauna;
    }
    
    public void setSauna(Sauna sauna) {
        this.sauna = sauna;
    }
    
    public Client getClient()
    {
        return this.client;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }
    
    public Calendar getReservationStart()
    {
        return this.reservationStart;
    }
    
    public void setReservationStart(Calendar date)
    {
        this.reservationStart = date;
    }
    
    public Calendar getReservationStop()
    {
        return this.reservationStop;
    }
    
    public void setReservationStop(Calendar date)
    {
        this.reservationStop = date;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        return "Sauna no. " + this.sauna.getNumber() + " is reserved by " + this.client.getName() + " " + this.client.getSurname() + " from " + sdf.format(this.reservationStart.getTime()) + " to " + sdf.format(this.reservationStop.getTime());
    }
    
    public String toFilterString() {
        return this.id.toString() + " " + this.client.toFilterString() + " " + Integer.toString(this.sauna.getNumber());
    }
}
