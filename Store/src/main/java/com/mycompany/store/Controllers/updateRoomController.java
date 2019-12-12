package com.mycompany.store.Controllers;

import com.mycompany.store.Model.Room;
import com.mycompany.store.Services.RoomService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named(value = "updateRoomController")
@RequestScoped
public class updateRoomController implements Serializable {

    @Inject
    DataHolder dh;
    
    @Inject
    private RoomService roomService;
    
    private Room room;
    
    public updateRoomController() {
    }
    
    @PostConstruct
    private void init()
    {
        room = new Room(dh.getRoomNumber(), dh.getRoomArea(), dh.getRoomBeds());
    }
    
    public Room getRoom() {
        return room;
    }
    
    public String updateRoom() {
        roomService.updateRoom(room.getNumber(), room.getArea(), room.getBeds());
        return "listRooms";
    }
}
