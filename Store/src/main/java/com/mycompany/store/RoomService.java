package com.mycompany.store;

import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Named(value = "roomService")
@Dependent
public class RoomService {
    
    @Inject
    private RoomRepository roomRepository;
    
    public RoomService() {
    }
    
    public Map<Integer, Room> getRooms()
    {
        return roomRepository.getRooms();
    }
    
    public Room getRoom(int number)
    { 
        return roomRepository.getRoom(number);
    }
    
    public int getRoomsAmount()
    {
        return roomRepository.getRoomsAmount();
    }
    
    public int getRentedRoomsAmount()
    {
        return roomRepository.getRentedRoomsAmount();
    }
    
    public int getFreeRoomsAmount()
    {
        return roomRepository.getFreeRoomsAmount();
    }
    
    public void addRoom(Room room)
    {
        roomRepository.addRoom(room);
    }
    
    public void updateRoom(int number, double newArea, int newBeds, boolean rent)
    {
        roomRepository.updateRoom(number, newArea, newBeds, rent);
    }
    
    public void deleteRoom(int number) throws Exception
    {
        roomRepository.deleteRoom(number);
    }
    
}
