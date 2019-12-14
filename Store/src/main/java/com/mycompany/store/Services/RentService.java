package com.mycompany.store.Services;

import com.mycompany.store.Model.Room;
import com.mycompany.store.Model.User;
import com.mycompany.store.Rent;
import com.mycompany.store.Repositories.RentRepository;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Named(value = "rentService")
@Dependent
public class RentService implements Serializable {

    @Inject
    private RentRepository rentRepository;
    
    public RentService() {
    }
    
    public Map<UUID, Rent> getRents() {
        return rentRepository.getRents();
    }
    
    public Map<UUID, Rent> getPastRents() {
        return rentRepository.getPastRents();
    }
    
    public Map<UUID, Rent> getCurrentRents() {
        return rentRepository.getCurrentRents();
    }

    public Map<UUID, Rent> getRentsForClient(User user) {
        return rentRepository.getRentsForClient(user);
    }
    
    public Map<UUID, Rent> getRentsForRoom(Room room) {
        return rentRepository.getRentsForRoom(room);
    }
    
    public Rent getRent(UUID id) {
        return rentRepository.getRent(id);
    }
    
    public void addRent(Rent rent) throws Exception {
        rentRepository.addRent(rent);
    }
    
    public void deleteRent(UUID id) throws Exception {
        String message = "";
        if (!rentRepository.deleteRent(id, message))
            throw new Exception(message);
    }
    
    public Map<UUID, Rent> getFilteredPastRents(String input) {
        return rentRepository.getFilteredPastRents(input);
    }
    
    public Map<UUID, Rent> getFilteredCurrentRents(String input) {
        return rentRepository.getFilteredCurrentRents(input);
    }
}
