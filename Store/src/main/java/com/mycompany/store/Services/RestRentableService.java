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
import javax.validation.constraints.Min;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Named(value = "restRentableService")
@RequestScoped
@Path("model")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestRentableService {
    
    @Inject
    private RentableRepository rentableRepository;
    
    @Inject
    private RentRepository rentRepository;

    public RestRentableService() {
    }
    
    @GET
    @Path("/rentables")
    public Response getRentables() {
        Map<Integer, Rentable> rentables = rentableRepository.getRentables();
        if(rentables != null){
            return Response.ok(rentables).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @GET
    @Path("/rooms")
    public Response getRooms() {
        Map<Integer, Room> rooms = rentableRepository.getRooms();
        if(rooms != null){
            return Response.ok(rooms).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @GET
    @Path("/saunas")
    public Response getSaunas() {
        Map<Integer, Sauna> saunas = rentableRepository.getSaunas();
        if(saunas != null){
            return Response.ok(saunas).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @GET
    @Path("/rentable/{number}")
    public Response getRentable(@PathParam("number") @Min(0) int number) {
        Rentable r = rentableRepository.getRentable(number);
        if(r != null){
            return Response.ok(r).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Rentable with number:"+ number +" doesn't exist").build();
    }
    
    @DELETE
    @Path("/rentable/{number}")
    public Response deleteRentable(@PathParam("number") @Min(0) int number)
    {
        Rentable r = rentableRepository.getRentable(number);
        if(r != null) {
            if(checkIfIsRented(number)){
                rentableRepository.deleteRentable(rentableRepository.getRentable(number));
                return Response.ok("Room " + number + " deleted").build();
            }
            else return Response.status(Response.Status.FORBIDDEN).entity("Can't delete rented rentable").build(); 
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Rentable with number:"+ number +" doesn't exist").build();
    }
    
    private boolean checkIfIsRented(int number) {
        return rentRepository.getCurrentRents().values().stream().noneMatch((rent) -> (rent.getRentable().getNumber() == number));
    }
    
    @POST
    @Path("/room")
    public void addRoom(Room room) {
        if(rentableRepository.getRentables().containsKey(room.getNumber()))
            throw new IllegalArgumentException("Room or sauna with this number already exists.");
        else rentableRepository.addRentable(room);
    }
    
    @POST
    @Path("/sauna")
    public void addSauna(Sauna sauna) {
        if(rentableRepository.getRentables().containsKey(sauna.getNumber()))
            throw new IllegalArgumentException("Room or sauna with this number already exists.");
        else rentableRepository.addRentable(sauna);
    }
    
    @PUT
    @Path("/room/{number}")
    public void updateRoom(@PathParam("number") @Min(0) int number, Room room) {
        if(rentableRepository.getRentables().containsKey(number)) {
            if(number == room.getNumber()) {
                if(rentableRepository.getRentable(number) instanceof Room)
                    rentableRepository.updateRentable(number, room);
                }
            else throw new IllegalArgumentException("You cannot change room`s number");
        }
        else throw new IllegalArgumentException("Room does not exists");
    }
    
    @PUT
    @Path("/sauna/{number}")
    public void updateSauna(@PathParam("number") @Min(0) int number, Sauna sauna) {
        if(rentableRepository.getRentables().containsKey(number)) {
            if(number == sauna.getNumber()) {
                if(rentableRepository.getRentable(number) instanceof Sauna)
                    rentableRepository.updateRentable(number, sauna);
            }
            else throw new IllegalArgumentException("You cannot change sauna`s number");
        }
        else throw new IllegalArgumentException("Room does not exists");
    }
}