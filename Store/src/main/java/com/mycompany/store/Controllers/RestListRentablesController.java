package com.mycompany.store.Controllers;

import com.mycompany.store.Model.Rentable;
import com.mycompany.store.Model.Room;
import com.mycompany.store.Model.Sauna;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

@Named(value = "restListRentablesController")
@ViewScoped
public class RestListRentablesController implements Serializable {
    
    @Inject
    private DataHolder dh;
    
    private Map<Integer, Rentable> rentables;
    private Map<Integer, Room> rooms;
    private Map<Integer, Sauna> saunas;
    private String filter;
    
    private Client client;
    private WebTarget webTarget; 
    
    public RestListRentablesController() {
    }
    
    @PostConstruct
    public void loadRentables()
    {
        client = ClientBuilder.newClient();
        webTarget = client.target("https://localhost:8181/Store/resources/model");
        rentables = webTarget.path("rentables").request(MediaType.APPLICATION_JSON).get(new GenericType<Map<Integer, Rentable>>() {});
        rooms = webTarget.path("rooms").request(MediaType.APPLICATION_JSON).get(new GenericType<Map<Integer, Room>>() {});
        saunas = webTarget.path("saunas").request(MediaType.APPLICATION_JSON).get(new GenericType<Map<Integer, Sauna>>() {});
        client.close();
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
