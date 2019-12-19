package com.mycompany.store.Services;

import com.mycompany.store.Model.User;
import com.mycompany.store.Model.Rent;
import com.mycompany.store.Model.Rentable;
import com.mycompany.store.Repositories.RentRepository;
import java.io.Serializable;
import java.util.Calendar;
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
    
    public Map<UUID, Rent> getRentsForRentable(Rentable rentable) {
        return rentRepository.getRentsForRentable(rentable);
    }
    
    public Rent getRent(UUID id) {
        return rentRepository.getRent(id);
    }
    
    public void addRent(Rent rent) throws Exception {
        if(rent.getClient().getIsActive())
            rentRepository.addRent(rent);
        else throw new Exception("Client is inactive");
    }
    
    public void deleteRent(UUID id) throws Exception {
        if(rentRepository.getRents().containsKey(id))
            if(rentRepository.getRent(id).getRentStop().after(Calendar.getInstance()))
                rentRepository.deleteRent(rentRepository.getRent(id));
            else throw new Exception("Rent is not finished");
    }
    
    public Map<UUID, Rent> getFilteredPastRents(String input) {
        return rentRepository.getFilteredPastRents(input);
    }
    
    public Map<UUID, Rent> getFilteredCurrentRents(String input) {
        return rentRepository.getFilteredCurrentRents(input);
    }
}
