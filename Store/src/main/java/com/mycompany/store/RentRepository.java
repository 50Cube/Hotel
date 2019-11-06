package com.mycompany.store;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.Dependent;


@Named(value = "rentRepository")
@Dependent
public class RentRepository {

    private Map<Integer, Rent> rents;
    
    public RentRepository() {
        rents = new HashMap<>();
    }
    
    public Map<Integer, Rent> getRents()
    {
        return this.rents;
    }
    
    public Rent getRent(int id)
    {
        return rents.get(id);
    }
    
    public void addRent(Rent rent)
    {
        rents.put(rent.getId(), rent);
    }
    
    public Map<Integer, Rent> getRentsBetween(Calendar startDate, Calendar stopDate)
    {
        Map<Integer, Rent> tmp = new HashMap<>();
        
        for (Rent rent : rents.values()) {
            if(rent.getRentStart().after(startDate) && rent.getRentStop().before(stopDate))
                tmp.put(rent.getId(), rent);
        }
        
        return tmp;
    }
    
    public Map<Integer, Rent> getRentsForClient(Client client)
    {
        Map<Integer, Rent> tmp = new HashMap<>();
        
        for (Rent rent : rents.values()) {
            if(rent.getClient().getId() == client.getId())
                tmp.put(rent.getId(), rent);
        }
        
        return tmp;
    }
    
    public Map<Integer, Rent> getRentsForRoom(Room room)
    {
        Map<Integer, Rent> tmp = new HashMap<>();
        
        for (Rent rent : rents.values()) {
            if(rent.getRoom().getNumber() == room.getNumber())
                tmp.put(rent.getId(), rent);
        }
        
        return tmp;
    }
}
