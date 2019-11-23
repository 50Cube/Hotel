package com.mycompany.store;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;


@Named(value = "roomRepository")
@ApplicationScoped
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
    
    public void updateRoom(int number, double newArea, int newBeds, boolean rent)
    {
        for(Room room : rooms.values())
            if(room.getNumber() == number)
            {
                room.setArea(newArea);
                room.setBeds(newBeds);
                room.setIsRent(rent);
            }
    }
    
    public boolean deleteRoom(int number, String message)
    {
        for(Room room : rooms.values())
            if(room.getNumber() == number)
            {
                if(!room.getIsRent())
                {
                    rooms.remove(room.getNumber());
                    return true;
                }
                else message = "This room is currently rent";
            }
            else message = "Room with that number does not exist";
        return false;
    }
    
    
    @PostConstruct
    private void initDataRoom()
    {
        addRoom(new Room(1,50,2));
        addRoom(new Room(2,30,1));
        addRoom(new Room(3,62,3));
        addRoom(new Room(4,78,5));
        addRoom(new Room(5,75,4));
    }
}
