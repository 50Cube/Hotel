package com.mycompany.store;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.Dependent;


@Named(value = "saunaReservationRepository")
@Dependent
public class SaunaReservationRepository {

    private Map<Integer, SaunaReservation> reservations;
    
    public SaunaReservationRepository() {
        reservations = new HashMap<>();
    }
    
    public Map<Integer, SaunaReservation> getReservations()
    {
        return reservations;
    }
    
    public SaunaReservation getReservatin(int id)
    {
        return reservations.get(id);
    }
    
    public void addReservation(SaunaReservation sr) throws Exception
    {
        if (sr.getClient().getIsActive())
            if (sr.getSauna().getIsReserved())
            {
                reservations.put(sr.getId(), sr);
                sr.getSauna().setIsReserved(true);
            }
            else throw new Exception("Sauna is already reserved");
        else throw new Exception("User is not active");
    }
    
    
    public Map<Integer, SaunaReservation> getReservationsBetween(Calendar startDate, Calendar stopDate)
    {
        Map<Integer, SaunaReservation> tmp = new HashMap<>();
        
        for (SaunaReservation sr : reservations.values()) {
            if(sr.getreservationStart().after(startDate) && sr.getReservationStop().before(stopDate))
                tmp.put(sr.getId(), sr);
        }
        
        return tmp;
    }
    
    public Map<Integer, SaunaReservation> getReservationsForClient(Client client)
    {
        Map<Integer, SaunaReservation> tmp = new HashMap<>();
        
        for (SaunaReservation sr : reservations.values()) {
            if(sr.getClient().getId() == client.getId())
                tmp.put(sr.getId(), sr);
        }
        
        return tmp;
    }
    
    public void deleteReservation(int id) throws Exception
    {
        for (SaunaReservation sr : reservations.values())
            if (sr.getClient().getId() == id)
                if (sr.getReservationStop().before(Calendar.getInstance()))
                    reservations.remove(sr.getId());
                else throw new Exception("Reservation is not finished");
    }
}
