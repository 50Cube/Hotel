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
    
    @Inject
    private DataHolder dh;
    
    private Map<Integer, Room> rooms;
    private String filter;
    
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
    
    public String saveData(Room room) {
        dh.setRoomNumber(room.getNumber());
        dh.setRoomArea(room.getArea());
        dh.setRoomBeds(room.getBeds());
        return "updateRoom.xhtml";
    }
    
    public void getFilteredRooms() {
        rooms = roomService.getFilteredRooms(this.filter);
    }
    
        
    public String getFilter() {
        return this.filter;
    }
    
    public void setFilter(String filter) {
        this.filter = filter;
    }
}
