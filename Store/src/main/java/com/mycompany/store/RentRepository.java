package com.mycompany.store;

import java.util.Calendar;
import java.util.HashMap;
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
    
    public void addRent(Rent rent) throws Exception
    {
        if (rent.getClient().getIsActive())
            if (rent.getRoom().getIsRent())
            {
                rents.put(rent.getId(), rent);
                rent.getRoom().setIsRent(true);
            }
            else throw new Exception("The room is already rent"); 
        else throw new Exception("User is not active");
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
    
    public void deleteRent(int id) throws Exception
    {
        for (Rent rent : rents.values())
            if (rent.getId() == id)
                if (rent.getRentStop().before(Calendar.getInstance()))
                    rents.remove(rent.getId());
                else throw new Exception("Rent is not finished");
    }
}
