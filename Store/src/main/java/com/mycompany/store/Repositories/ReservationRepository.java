package com.mycompany.store.Repositories;

import com.mycompany.store.Model.Client;
import com.mycompany.store.Model.Sauna;
import com.mycompany.store.Model.User;
import com.mycompany.store.Reservation;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.inject.Inject;


@Named(value = "ReservationRepository")
@ApplicationScoped
public class ReservationRepository {

    @Inject
    private SaunaRepository saunaRepository;
    
    @Inject
    private UserRepository userRepository;
    
    private Map<UUID, Reservation> reservations;
    
    public ReservationRepository() {
        reservations = new HashMap<>();
    }
    
    public Map<UUID, Reservation> getReservations()
    {
        return new HashMap<>(reservations);
    }
    
    public Map<UUID, Reservation> getPastReservations() {
        Map<UUID, Reservation> tmp = new HashMap<>();
        
        reservations.values().stream().filter((r) -> (r.getReservationStop().before(Calendar.getInstance()))).forEachOrdered((r) -> {
            tmp.put(r.getId(), r);
        });
        
        return tmp;
    }
    
    public Map<UUID, Reservation> getCurrentReservations() {
        Map<UUID, Reservation> tmp = new HashMap<>();
        
        reservations.values().stream().filter((r) -> (r.getReservationStop().after(Calendar.getInstance()))).forEachOrdered((r) -> {
            tmp.put(r.getId(), r);
        });
        
        return tmp;
    }
    
    public Reservation getReservation(UUID id)
    {
        return reservations.get(id);
    }
    
    public synchronized void addReservation(Reservation sr)
    {
        if (sr.getClient().getIsActive())
            reservations.put(sr.getId(), sr);
    }
    
    
    public Map<UUID, Reservation> getReservationsBetween(Calendar startDate, Calendar stopDate)
    {
        Map<UUID, Reservation> tmp = new HashMap<>();
        
        for (Reservation sr : reservations.values()) {
            if(sr.getReservationStart().after(startDate) && sr.getReservationStop().before(stopDate))
                tmp.put(sr.getId(), sr);
        }
        
        return tmp;
    }
    
    public Map<UUID, Reservation> getReservationsForClient(User user)
    {
        Map<UUID, Reservation> tmp = new HashMap<>();
        
        for (Reservation sr : reservations.values()) {
            if(sr.getClient().getLogin().equals(user.getLogin()))
                tmp.put(sr.getId(), sr);
        }
        
        return tmp;
    }
    
    public Map<UUID, Reservation> getReservationsForSauna(Sauna sauna)
    {
        Map<UUID, Reservation> tmp = new HashMap<>();
        
        for (Reservation sr : reservations.values()) {
            if(sr.getSauna().getNumber() == sauna.getNumber())
                tmp.put(sr.getId(), sr);
        }
        
        return tmp;
    }
    
    public synchronized boolean deleteReservation(UUID id, String message)
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
    
    public Map<UUID, Reservation> getFilteredPastReservations(String input) {
        Map<UUID, Reservation> tmp = new HashMap<>();
        
        this.getPastReservations().values().stream().filter((reservation) -> (reservation.toFilterString().toLowerCase().contains(input.trim())))
                .forEachOrdered((reservation) -> {
            tmp.put(reservation.getId(), reservation);
                });
        
        return tmp;
    }
    
    public Map<UUID, Reservation> getFilteredCurrentReservations(String input) {
        Map<UUID, Reservation> tmp = new HashMap<>();
        
        this.getCurrentReservations().values().stream().filter((reservation) -> (reservation.toFilterString().toLowerCase().contains(input.trim())))
                .forEachOrdered((reservation) -> {
                    tmp.put(reservation.getId(), reservation);
                });
        
        return tmp;
    }
    
    @PostConstruct
    private void initDataReservation() {
        addReservation(new Reservation(saunaRepository.getSauna(1), (Client) userRepository.getUser("client2"), new GregorianCalendar(2019,12,06), new GregorianCalendar(2019,12,24)));
        addReservation(new Reservation(saunaRepository.getSauna(1), (Client) userRepository.getUser("client2"), new GregorianCalendar(2019,07,15), new GregorianCalendar(2019,07,16)));
        addReservation(new Reservation(saunaRepository.getSauna(2), (Client) userRepository.getUser("client1"), new GregorianCalendar(2019,01,15), new GregorianCalendar(2019,01,16)));
    }
}
