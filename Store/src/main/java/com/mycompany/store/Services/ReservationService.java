package com.mycompany.store.Services;

import com.mycompany.store.Repositories.ReservationRepository;
import com.mycompany.store.Reservation;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Named(value = "reservationService")
@Dependent
public class ReservationService implements Serializable {

    @Inject
    private ReservationRepository reservationRepository;
    
    public ReservationService() {
    }
    
    public Map<UUID, Reservation> getReservations() {
        return reservationRepository.getReservations();
    }
    
    public Reservation getReservation(UUID id) {
        return reservationRepository.getReservation(id);
    }
    
    public void addReservation(Reservation reservation) throws Exception {
        reservationRepository.addReservation(reservation);
    }
    
    public void deleteReservation(UUID id) throws Exception {
        String message = "";
        if (!reservationRepository.deleteReservation(id, message))
            throw new Exception(message);
    }
}