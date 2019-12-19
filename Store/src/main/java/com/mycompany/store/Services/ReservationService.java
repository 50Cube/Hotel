package com.mycompany.store.Services;

import com.mycompany.store.Model.Sauna;
import com.mycompany.store.Model.User;
import com.mycompany.store.Repositories.ReservationRepository;
import com.mycompany.store.Model.Reservation;
import java.io.Serializable;
import java.util.Calendar;
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
    
    public Map<UUID, Reservation> getPastReservations() {
        return reservationRepository.getPastReservations();
    }
    
    public Map<UUID, Reservation> getCurrentReservations() {
        return reservationRepository.getCurrentReservations();
    }
    
    public Map<UUID, Reservation> getReservationsForClient(User user) {
        return reservationRepository.getReservationsForClient(user);
    }
    
    public Map<UUID, Reservation> getReservationsForSauna(Sauna sauna) {
        return reservationRepository.getReservationsForSauna(sauna);
    }
    
    public Reservation getReservation(UUID id) {
        return reservationRepository.getReservation(id);
    }
    
    public void addReservation(Reservation reservation) throws Exception {
        if(reservation.getClient().getIsActive())
            reservationRepository.addReservation(reservation);
        else throw new Exception("Client is inactive");
    }
    
    public void deleteReservation(UUID id) throws Exception {
        if(reservationRepository.getReservations().containsKey(id))
            if(reservationRepository.getReservation(id).getReservationStop().after(Calendar.getInstance()))
                reservationRepository.deleteReservation(reservationRepository.getReservation(id));
            else throw new Exception("Reservation is not finished");
    }
    
    public Map<UUID, Reservation> getFilteredPastReservations(String input) {
        return reservationRepository.getFilteredPastReservations(input);
    }
    
    public Map<UUID, Reservation> getFilteredCurrentReservations(String input) {
        return reservationRepository.getFilteredCurrentReservations(input);
    }
}
