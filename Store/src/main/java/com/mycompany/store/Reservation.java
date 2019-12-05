package com.mycompany.store;

import com.mycompany.store.Model.Sauna;
import com.mycompany.store.Model.Client;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;
import javax.inject.Named;
import javax.enterprise.context.Dependent;


@Named(value = "Reservation")
@Dependent
public class Reservation {

    private UUID id = UUID.randomUUID();
    private Sauna sauna;
    private Client client;
    private Calendar reservationStart;
    private Calendar reservationStop;
    
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
    
    public Client getClient()
    {
        return this.client;
    }
    
    public Calendar getreservationStart()
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
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        
        return "Sauna no. " + this.sauna.getNumber() + " is reservated by " + this.client.getName() + " " + this.client.getSurname() + " from " + sdf.format(this.reservationStart.getTime()) + " to " + sdf.format(this.reservationStop.getTime());
    }
}
