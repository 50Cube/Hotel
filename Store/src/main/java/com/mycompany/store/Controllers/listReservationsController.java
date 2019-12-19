package com.mycompany.store.Controllers;

import com.mycompany.store.Model.Sauna;
import com.mycompany.store.Model.User;
import com.mycompany.store.Model.Reservation;
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
    
    @Inject
    private DataHolder dh;
    
    private Sauna sauna;
    private User user;
    private String filterPast;
    private String filterCurrent;
    
    private Map<UUID, Reservation> pastReservations;
    private Map<UUID, Reservation> currentReservations;
    private Map<UUID, Reservation> reservationsForClient;
    private Map<UUID, Reservation> reservationsForSauna;

    public listReservationsController() {
    }
    
    @PostConstruct
    public void loadReservations() {
        pastReservations = reservationService.getPastReservations();
        currentReservations = reservationService.getCurrentReservations();
    }
    
    public Map<UUID, Reservation> getPastReservations() {
        return pastReservations;
    }
    
    public Map<UUID, Reservation> getCurrentReservations() {
        return currentReservations;
    }
    
    public void deleteReservation(UUID id) throws Exception {
        reservationService.deleteReservation(id);
        loadReservations();
    }
    
    public String getReservationsForSaunaPrepare(Sauna sauna) {
        dh.setSauna(sauna);
        return "listReservationsForSauna.xhtml";
    }
    
    public Map<UUID, Reservation> getReservationsForSauna() {
        this.sauna = dh.getSauna();
        reservationsForSauna = reservationService.getReservationsForSauna(sauna);
        return reservationsForSauna;
    }
    
    public Sauna getSauna() {
        this.sauna = dh.getSauna();
        return this.sauna;
    }
    
    public String getReservationsForClientPrepare(User user) {
        dh.setUser(user);
        return "listReservationsForClient.xhtml";
    }
    
    public Map<UUID, Reservation> getReservationsForClient() {
        this.user = dh.getUser();
        reservationsForClient = reservationService.getReservationsForClient(user);
        return reservationsForClient;
    }
    
    public User getUser() {
        this.user = dh.getUser();
        return this.user;
    }
    
    public void getFilteredPastReservations() {
        pastReservations = reservationService.getFilteredPastReservations(this.filterPast);
    }
    
    public void getFilteredCurrentReservations() {
        currentReservations = reservationService.getFilteredCurrentReservations(this.filterCurrent);
    }
    
    public String getFilterPast() {
        return this.filterPast;
    }
    
    public void setFilterPast(String filter) {
        this.filterPast = filter;
    }
    
    public String getFilterCurrent() {
        return this.filterCurrent;
    }
    
    public void setFilterCurrent(String filter) {
        this.filterCurrent = filter;
    }
}
