package com.mycompany.store.Repositories;

import com.mycompany.store.Model.Client;
import com.mycompany.store.Model.Room;
import com.mycompany.store.Rent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;


@Named(value = "rentRepository")
@Dependent
public class RentRepository {

    @Inject
    private UserRepository userRepository;
    
    @Inject
    private RoomRepository roomRepository;
    
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
    
    public void addRent(Rent rent)
    {
        if (rent.getClient().getIsActive())
            rents.put(rent.getId(), rent);
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
    
    public boolean deleteRent(UUID id, String message)
    {
        for (Rent rent : rents.values())
            if (rent.getId().equals(id))
                if (rent.getRentStop().before(Calendar.getInstance())) {
                    rents.remove(rent.getId());
                    return true;
                }
                else message = "Rent is not finished";
        return false;
    }
    
    @PostConstruct
    private void initDataRent() {
        addRent(new Rent(roomRepository.getRoom(1), (Client) userRepository.getUser("herb"), new GregorianCalendar(2019,12,05), new GregorianCalendar(2020,02,28)));
    }
}
