package com.mycompany.store.Controllers;

import com.mycompany.store.Model.Room;
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
    
    public Map<UUID, Rent> getRentsForClient() {
        return rentsForClient;
    }
}
