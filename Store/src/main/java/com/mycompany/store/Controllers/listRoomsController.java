package com.mycompany.store.Controllers;

import com.mycompany.store.Services.RoomService;
import com.mycompany.store.Model.Room;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;


@Named(value = "listRoomsController")
@ViewScoped
public class listRoomsController implements Serializable{

    @Inject
    private RoomService roomService;
    
    private Map<Integer, Room> rooms;
    
    public listRoomsController() {
    }
    
    @PostConstruct
    public void loadRooms()
    {
        rooms = roomService.getRooms();
    }
    
    public Map<Integer, Room> getRooms()
    {
        return rooms;
    }
    
    public void deleteRoom(int number) throws Exception
    {
        roomService.deleteRoom(number);
        loadRooms();
    }
}
