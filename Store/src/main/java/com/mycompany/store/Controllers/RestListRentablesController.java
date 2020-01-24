package com.mycompany.store.Controllers;

import com.mycompany.store.Model.Rentable;
import com.mycompany.store.Model.Room;
import com.mycompany.store.Model.Sauna;
import com.mycompany.store.Services.RestRentableService;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "restListRentablesController")
@ViewScoped
public class RestListRentablesController implements Serializable {

    @Inject
    private RestRentableService rentableService;
    
    @Inject
    private DataHolder dh;
    
    private Map<Integer, Rentable> rentables;
    private Map<Integer, Room> rooms;
    private Map<Integer, Sauna> saunas;
    private String filter;
    
    public RestListRentablesController() {
    }
    
    @PostConstruct
    public void loadRentables()
    {
        rentables = rentableService.getRentables();
        rooms = rentableService.getRooms();
        saunas = rentableService.getSaunas();
    }
    
    public Map<Integer, Rentable> getRentables()
    {
        return rentables;
    }
    
    public Map<Integer, Room> getRooms()
    {
        return rooms;
    }
    
    public Map<Integer, Sauna> getSaunas()
    {
        return saunas;
    }
}
