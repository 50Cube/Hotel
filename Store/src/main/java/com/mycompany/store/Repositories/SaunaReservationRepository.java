package com.mycompany.store.Repositories;

import com.mycompany.store.Model.Client;
import com.mycompany.store.SaunaReservation;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.inject.Named;
import javax.enterprise.context.Dependent;


@Named(value = "saunaReservationRepository")
@Dependent
public class SaunaReservationRepository {

    private Map<UUID, SaunaReservation> reservations;
    
    public SaunaReservationRepository() {
        reservations = new HashMap<>();
    }
    
    public Map<UUID, SaunaReservation> getReservations()
    {
        return reservations;
    }
    
    public SaunaReservation getReservatin(UUID id)
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
    
    
    public Map<UUID, SaunaReservation> getReservationsBetween(Calendar startDate, Calendar stopDate)
    {
        Map<UUID, SaunaReservation> tmp = new HashMap<>();
        
        for (SaunaReservation sr : reservations.values()) {
            if(sr.getreservationStart().after(startDate) && sr.getReservationStop().before(stopDate))
                tmp.put(sr.getId(), sr);
        }
        
        return tmp;
    }
    
    public Map<UUID, SaunaReservation> getReservationsForClient(Client client)
    {
        Map<UUID, SaunaReservation> tmp = new HashMap<>();
        
        for (SaunaReservation sr : reservations.values()) {
            if(sr.getClient().getLogin().equals(client.getLogin()))
                tmp.put(sr.getId(), sr);
        }
        
        return tmp;
    }
    
    public void deleteReservation(UUID id) throws Exception
    {
        for (SaunaReservation sr : reservations.values())
            if (sr.getId().equals(id))
                if (sr.getReservationStop().before(Calendar.getInstance()))
                    reservations.remove(sr.getId());
                else throw new Exception("Reservation is not finished");
    }
}
