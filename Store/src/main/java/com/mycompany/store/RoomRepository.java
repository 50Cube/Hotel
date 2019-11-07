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
    
    public void deleteRoom(int number) throws Exception
    {
        for(Room room : rooms.values())
            if(room.getNumber() == number){
                if(!room.getIsRent())
                    rooms.remove(room.getNumber());
                else throw new Exception("The room is currently rent");}
            else throw new Exception("Room with that number does not exist");
    }
}
