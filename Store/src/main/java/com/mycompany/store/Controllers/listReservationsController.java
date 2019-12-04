package com.mycompany.store.Controllers;

import com.mycompany.store.Reservation;
import com.mycompany.store.Services.ReservationService;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "listReservationsController")
@ViewScoped
public class listReservationsController implements Serializable {
    
    @Inject
    private ReservationService reservationService;
    
    private Map<UUID, Reservation> reservations;

    public listReservationsController() {
    }
    
    @PostConstruct
    public void loadReservations() {
        reservations = reservationService.getReservations();
    }
    
    public Map<UUID, Reservation> getReservations() {
        return reservations;
    }
    
    public void deleteReservation(UUID id) throws Exception {
        reservationService.deleteReservation(id);
        loadReservations();
    }
}
