package com.mycompany.store;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;


@Named(value = "listRoomsController")
@ViewScoped
public class listRoomsController {

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
