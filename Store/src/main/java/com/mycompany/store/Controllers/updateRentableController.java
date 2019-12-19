package com.mycompany.store.Controllers;

import com.mycompany.store.Model.Room;
import com.mycompany.store.Model.Sauna;
import com.mycompany.store.Services.RentableService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named(value = "updateRentableController")
@RequestScoped
public class updateRentableController implements Serializable {

    @Inject
    DataHolder dh;
    
    @Inject
    private RentableService rentableService;
    
    private Room room;
    private Sauna sauna;
    
    public updateRentableController() {
    }
    
    @PostConstruct
    private void init()
    {
        room = new Room(dh.getRoom().getNumber(), dh.getRoom().getArea(), dh.getRoom().getBeds());
        sauna = new Sauna(dh.getSauna().getNumber(), dh.getSauna().getPricePerHour());
    }
    
    public Room getRoom() {
        return room;
    }
    
    public Sauna getSauna() {
        return sauna;
    }
    
    public String updateRoom() {
        rentableService.updateRoom(room.getNumber(), room.getArea(), room.getBeds());
        return "listRentables";
    }
    
    public String updateSauna() {
        rentableService.updateSauna(sauna.getNumber(), sauna.getPricePerHour());
        return "listRentables";
    }
}
