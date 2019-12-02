package com.mycompany.store.Repositories;

import com.mycompany.store.Model.Client;
import com.mycompany.store.Model.Room;
import com.mycompany.store.Rent;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.inject.Named;
import javax.enterprise.context.Dependent;


@Named(value = "rentRepository")
@Dependent
public class RentRepository {

    private Map<UUID, Rent> rents;
    
    public RentRepository() {
        rents = new HashMap<>();
    }
    
    public Map<UUID, Rent> getRents()
    {
        return this.rents;
    }
    
    public Rent getRent(UUID id)
    {
        return rents.get(id);
    }
    
    public void addRent(Rent rent) throws Exception
    {
        if (rent.getClient().getIsActive())
            rents.put(rent.getId(), rent);
        else throw new Exception("User is not active");
    }
    
    public Map<UUID, Rent> getRentsBetween(Calendar startDate, Calendar stopDate)
    {
        Map<UUID, Rent> tmp = new HashMap<>();
        
        for (Rent rent : rents.values()) {
            if(rent.getRentStart().after(startDate) && rent.getRentStop().before(stopDate))
                tmp.put(rent.getId(), rent);
        }
        
        return tmp;
    }
    
    public Map<UUID, Rent> getRentsForClient(Client client)
    {
        Map<UUID, Rent> tmp = new HashMap<>();
        
        for (Rent rent : rents.values()) {
            if(rent.getClient().getLogin().equals(client.getLogin()))
                tmp.put(rent.getId(), rent);
        }
        
        return tmp;
    }
    
    public Map<UUID, Rent> getRentsForRoom(Room room)
    {
        Map<UUID, Rent> tmp = new HashMap<>();
        
        for (Rent rent : rents.values()) {
            if(rent.getRoom().getNumber() == room.getNumber())
                tmp.put(rent.getId(), rent);
        }
        
        return tmp;
    }
    
    public void deleteRent(UUID id) throws Exception
    {
        for (Rent rent : rents.values())
            if (rent.getId().equals(id))
                if (rent.getRentStop().before(Calendar.getInstance()))
                    rents.remove(rent.getId());
                else throw new Exception("Rent is not finished");
    }
}
