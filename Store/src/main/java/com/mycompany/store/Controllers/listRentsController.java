package com.mycompany.store.Controllers;

import com.mycompany.store.Rent;
import com.mycompany.store.Services.RentService;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "listRentsController")
@ViewScoped
public class listRentsController implements Serializable {

    @Inject
    private RentService rentService;
    
    private Map<UUID, Rent> pastRents;
    private Map<UUID, Rent> currentRents;
    
    public listRentsController() {
    }
    
    @PostConstruct
    public void loadRents() {
        pastRents = rentService.getPastRents();
        currentRents = rentService.getCurrentRents();
    }
    
    public Map<UUID, Rent> getPastRents() {
        return pastRents;
    }
    
    public Map<UUID, Rent> getCurrentRents() {
        return currentRents;
    }
    
    public void deleteRent(UUID id) throws Exception {
        rentService.deleteRent(id);
        loadRents();
    }
}
