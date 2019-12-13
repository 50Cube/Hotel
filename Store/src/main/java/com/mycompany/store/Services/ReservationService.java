package com.mycompany.store.Services;

import com.mycompany.store.Model.Client;
import com.mycompany.store.Model.Sauna;
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
    
    public Map<UUID, Reservation> getPastReservations() {
        return reservationRepository.getPastReservations();
    }
    
    public Map<UUID, Reservation> getCurrentReservations() {
        return reservationRepository.getCurrentReservations();
    }
    
    public Map<UUID, Reservation> getReservationsForClient(Client client) {
        return reservationRepository.getReservationsForClient(client);
    }
    
    public Map<UUID, Reservation> getReservationsForSauna(Sauna sauna) {
        return reservationRepository.getReservationsForSauna(sauna);
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
