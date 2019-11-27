package com.mycompany.store;

import com.mycompany.store.Model.Sauna;
import com.mycompany.store.Model.Client;
import java.util.Calendar;
import java.util.UUID;
import javax.inject.Named;
import javax.enterprise.context.Dependent;


@Named(value = "saunaReservation")
@Dependent
public class SaunaReservation {

    private UUID id = UUID.randomUUID();
    private Sauna sauna;
    private Client client;
    private Calendar reservationStart;
    private Calendar reservationStop;
    
    public SaunaReservation(Sauna sauna, Client client, Calendar from, Calendar to) {
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
    
    public void setreservationStart(Calendar date)
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
    
}
