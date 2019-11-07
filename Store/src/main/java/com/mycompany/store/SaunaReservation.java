package com.mycompany.store;

import java.util.Calendar;
import javax.inject.Named;
import javax.enterprise.context.Dependent;


@Named(value = "saunaReservation")
@Dependent
public class SaunaReservation {

    private int id;
    private Sauna sauna;
    private Client client;
    private Calendar reservationStart;
    private Calendar reservationStop;
    
    public SaunaReservation(int id, Sauna sauna, Client client, Calendar from, Calendar to) {
        this.id = id;
        this.sauna = sauna;
        this.client = client;
        this.reservationStart = from;
        this.reservationStop = to;
    }
    
    public int getId()
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
