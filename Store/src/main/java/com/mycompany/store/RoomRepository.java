package com.mycompany.store;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.Dependent;


@Named(value = "roomRepository")
@Dependent
public class RoomRepository {

    private Map<Integer, Room> rooms;
    
    public RoomRepository() {
        rooms = new HashMap<>();
    }
    
    public Map<Integer, Room> getRooms()
    {
        return this.rooms;
    }
    
    public Room getRoom(int number)
    { 
        return rooms.get(number);
    }
    
    public int getRoomsAmount()
    {
        return this.rooms.size();
    }
    
    public int getRentedRoomsAmount()
    {
        int tmp = 0;
        for(Room room : rooms.values())
            if (room.getIsRent())
                tmp++;
        
        return tmp;
    }
    
    public int getFreeRoomsAmount()
    {
        return getRoomsAmount() - getRentedRoomsAmount();
    }
    
    public void addRoom(Room room)
    {
        rooms.put(room.getNumber(), room);
    }
    
}
