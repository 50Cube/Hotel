package com.mycompany.store.Controllers;

import com.mycompany.store.Model.Room;
import com.mycompany.store.Model.User;
import com.mycompany.store.Rent;
import com.mycompany.store.Services.RentService;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "listRentsController")
@ViewScoped
public class listRentsController implements Serializable {

    @Inject
    private RentService rentService;
    
    @Inject
    private DataHolder dh;
    
    private Room room;
    private User user;
    private String filterPast;
    private String filterCurrent;
    
    private Map<UUID, Rent> pastRents;
    private Map<UUID, Rent> currentRents;
    private Map<UUID, Rent> rentsForClient;
    private Map<UUID, Rent> rentsForRoom;
    
    public listRentsController() {
    }
    
    @PostConstruct
    public void loadRents() {
        pastRents = rentService.getPastRents();
        currentRents = rentService.getCurrentRents();
    }
    
    public Map<UUID, Rent> getPastRents() {
        return pastRents;
    }
    
    public Map<UUID, Rent> getCurrentRents() {
        return currentRents;
    }
    
    public void deleteRent(UUID id) throws Exception {
        rentService.deleteRent(id);
        loadRents();
    }
    
    public String getRentsForRoomPrepare(Room room) {
        dh.setRoom(room);
        return "listRentsForRoom.xhtml";
    }

    public Map<UUID, Rent> getRentsForRoom() {
        this.room = dh.getRoom();
        rentsForRoom = rentService.getRentsForRoom(room);
        return rentsForRoom;
    }
    
    public Room getRoom() {
        this.room = dh.getRoom();
        return this.room;
    }
    
    public String getRentsForClientPrepare(User user) {
        dh.setUser(user);
        return "listRentsForClient.xhtml";
    }
    
    public Map<UUID, Rent> getRentsForClient() {
        this.user = dh.getUser();
        rentsForClient = rentService.getRentsForClient(user);
        return rentsForClient;
    }
    
    public User getUser() {
        this.user = dh.getUser();
        return this.user;
    }
    
    public void getFilteredPastRents() {
        pastRents = rentService.getFilteredPastRents(this.filterPast);
    }
    
    public void getFilteredCurrentRents() {
        currentRents = rentService.getFilteredCurrentRents(this.filterCurrent);
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
