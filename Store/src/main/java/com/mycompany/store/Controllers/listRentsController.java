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
    
    private Map<UUID, Rent> rents;
    
    public listRentsController() {
    }
    
    @PostConstruct
    public void loadRents() {
        rents = rentService.getRents();
    }
    
    public Map<UUID, Rent> getRents() {
        return rents;
    }
    
    public void deleteRent(UUID id) throws Exception {
        rentService.deleteRent(id);
        loadRents();
    }
}
