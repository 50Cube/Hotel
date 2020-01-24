package com.mycompany.store.Services;

import com.mycompany.store.Model.Rentable;
import com.mycompany.store.Model.Room;
import com.mycompany.store.Model.Sauna;
import com.mycompany.store.Repositories.RentRepository;
import com.mycompany.store.Repositories.RentableRepository;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Named(value = "restRentableService")
@RequestScoped
@Path("model")
public class RestRentableService {
    
    @Inject
    private RentableRepository rentableRepository;
    
    @Inject
    private RentRepository rentRepository;

    public RestRentableService() {
    }
    
    @GET
    @Path("/rentables")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Map<Integer, Rentable> getRentables() {
        return rentableRepository.getRentables();
    }
    
    @GET
    @Path("/rooms")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Map<Integer, Room> getRooms() {
        return rentableRepository.getRooms();
    }
    
    @GET
    @Path("/saunas")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Map<Integer, Sauna> getSaunas() {
        return rentableRepository.getSaunas();
    }
    
    @GET
    @Path("/rentable/{number}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Rentable getRentable(@PathParam("number") int number) {
        return rentableRepository.getRentable(number);
    }
    
    @DELETE
    @Path("/rentable/{number}")
    public void deleteRentable(@PathParam("number") int number)
    {
        if(rentableRepository.getRentables().containsKey(number)) {
            if(checkIfIsRented(number))
                rentableRepository.deleteRentable(rentableRepository.getRentable(number));
            else throw new IllegalArgumentException("Cannot remove room or sauna which is rented");
        }
        else throw new IllegalArgumentException("Room or sauna with that number does not exist");
    }
    
    private boolean checkIfIsRented(int number) {
        return rentRepository.getCurrentRents().values().stream().noneMatch((rent) -> (rent.getRentable().getNumber() == number));
    }
    
    @POST
    @Path("/room")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void addRoom(Room room) {
        if(rentableRepository.getRentables().containsKey(room.getNumber()))
            throw new IllegalArgumentException("Room or sauna with this number already exists.");
        else rentableRepository.addRentable(room);
    }
    
    @POST
    @Path("/sauna")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void addSauna(Sauna sauna) {
        if(rentableRepository.getRentables().containsKey(sauna.getNumber()))
            throw new IllegalArgumentException("Room or sauna with this number already exists.");
        else rentableRepository.addRentable(sauna);
    }
    
    @PUT
    @Path("/room/{number}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateRoom(@PathParam("number") int number, Room room) {
        if(rentableRepository.getRentables().containsKey(number)) {
            if(rentableRepository.getRentable(number) instanceof Room)
                rentableRepository.updateRentable(number, room);
        }
        else throw new IllegalArgumentException("Room does not exists");
    }
    
    @PUT
    @Path("/sauna/{number}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateSauna(@PathParam("number") int number, Sauna sauna) {
        if(rentableRepository.getRentables().containsKey(number)) {
            if(rentableRepository.getRentable(number) instanceof Sauna)
                rentableRepository.updateRentable(number, sauna);
        }
        else throw new IllegalArgumentException("Room does not exists");
    }
}
