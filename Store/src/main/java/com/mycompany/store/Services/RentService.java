package com.mycompany.store.Services;

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
}
