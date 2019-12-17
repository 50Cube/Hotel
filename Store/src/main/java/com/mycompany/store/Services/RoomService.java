package com.mycompany.store.Services;

import com.mycompany.store.Repositories.RoomRepository;
import com.mycompany.store.Model.Room;
import java.io.Serializable;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Named(value = "roomService")
@Dependent
public class RoomService implements Serializable{
    
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
 
    public void addRoom(Room room)
    {
        roomRepository.addRoom(room);
    }
    
    public void updateRoom(int number, double newArea, int newBeds)
    {
        roomRepository.updateRoom(roomRepository.getRoom(number), newArea, newBeds);
    }
    
    public void deleteRoom(int number) throws Exception
    {
        if(roomRepository.getRooms().containsKey(number))
            roomRepository.deleteRoom(roomRepository.getRoom(number));
        else throw new Exception("Room with that number does not exist");
    }
    
    public Map<Integer, Room> getFilteredRooms(String input) {
        return roomRepository.getFilteredRooms(input);
    }
}
