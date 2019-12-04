package com.mycompany.store.Repositories;

import com.mycompany.store.Model.Client;
import com.mycompany.store.Reservation;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.inject.Named;
import javax.enterprise.context.Dependent;


@Named(value = "ReservationRepository")
@Dependent
public class ReservationRepository {

    private Map<UUID, Reservation> reservations;
    
    public ReservationRepository() {
        reservations = new HashMap<>();
    }
    
    public Map<UUID, Reservation> getReservations()
    {
        return reservations;
    }
    
    public Reservation getReservation(UUID id)
    {
        return reservations.get(id);
    }
    
    public void addReservation(Reservation sr) throws Exception
    {
        if (sr.getClient().getIsActive())
            reservations.put(sr.getId(), sr);
        else throw new Exception("User is not active");
    }
    
    
    public Map<UUID, Reservation> getReservationsBetween(Calendar startDate, Calendar stopDate)
    {
        Map<UUID, Reservation> tmp = new HashMap<>();
        
        for (Reservation sr : reservations.values()) {
            if(sr.getreservationStart().after(startDate) && sr.getReservationStop().before(stopDate))
                tmp.put(sr.getId(), sr);
        }
        
        return tmp;
    }
    
    public Map<UUID, Reservation> getReservationsForClient(Client client)
    {
        Map<UUID, Reservation> tmp = new HashMap<>();
        
        for (Reservation sr : reservations.values()) {
            if(sr.getClient().getLogin().equals(client.getLogin()))
                tmp.put(sr.getId(), sr);
        }
        
        return tmp;
    }
    
    public boolean deleteReservation(UUID id, String message)
    {
        for (Reservation sr : reservations.values())
            if (sr.getId().equals(id))
                if (sr.getReservationStop().before(Calendar.getInstance())) {
                    reservations.remove(sr.getId());
                    return true;
                }
                else message = "Reservation is not finished";
        return false;
    }
}
