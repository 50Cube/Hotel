package com.mycompany.store.Repositories;

import com.mycompany.store.Model.Room;
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
        return new HashMap<>(rooms);
    }
    
    public Room getRoom(int number)
    { 
        return rooms.get(number);
    }
    
    public int getRoomsAmount()
    {
        return this.rooms.size();
    }
    
    public synchronized void addRoom(Room room)
    {
        rooms.put(room.getNumber(), room);
    }
    
    public synchronized void updateRoom(int number, double newArea, int newBeds)
    {
        for(Room room : rooms.values())
            if(room.getNumber() == number)
            {
                room.setArea(newArea);
                room.setBeds(newBeds);
            }
    }
    
    public synchronized boolean deleteRoom(int number, String message)
    {
        for(Room room : rooms.values())
            if(room.getNumber() == number)
            {
                rooms.remove(room.getNumber());
                return true;
            }
            else message = "Room with that number does not exist";
        return false;
    }
    
    public Map<Integer, Room> getFilteredRooms(String input) {
        Map<Integer, Room> tmp = new HashMap<>();
        
        rooms.values().stream().filter((room) -> (Integer.toString(room.getNumber()).contains(input.trim()))).forEachOrdered((room) -> {
            tmp.put(room.getNumber(), room);
        });
        
        return tmp;
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
