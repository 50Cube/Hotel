package com.mycompany.store.Controllers;

import com.mycompany.store.Model.Client;
import com.mycompany.store.Model.Sauna;
import com.mycompany.store.Model.Reservation;
import com.mycompany.store.Services.ReservationService;
import com.mycompany.store.Services.UserService;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "addReservationController")
@ConversationScoped
public class AddReservationController implements Serializable {

    @Inject
    private ReservationService reservationService;
    
    @Inject
    private UserService userService;
    
    @Inject
    private Conversation conversation;
    private Reservation reservation;
    private Date start;
    private Date stop;
    private Map<String, Client> clients;
    private String clientLogin;
    
    public AddReservationController() {
    }
    
    @PostConstruct
    private void init() {
        reservation = new Reservation();
        clients = userService.getClients();
    }
    
    public Reservation getReservation() {
        return reservation;
    }
    
    public String addReservation(Sauna sauna) {
        if(!conversation.isTransient())
            conversation.end();
        conversation.begin();
        
        reservation.setSauna(sauna);
        
        return "addReservation";
    }
    
    public String addReservationConfirm() throws Exception {
        Calendar tmp1 = new GregorianCalendar();
        Calendar tmp2 = new GregorianCalendar();
        tmp1.setTime(start);
        tmp2.setTime(stop);
        reservation.setReservationStart(tmp1);
        reservation.setReservationStop(tmp2);
        chooseClient();
        reservationService.addReservation(reservation);
        
        if(stop.before(start)) throw new Exception("Beginning date must be earlier than end date");
        if(!checkIfReserved(tmp1,tmp2)) throw new Exception("Sauna is already reserved");
        conversation.end();
        return "home";
    }
    
    private boolean checkIfReserved(Calendar start, Calendar stop) {
        for(Reservation reservation : reservationService.getReservations().values())
            if( ( start.before(reservation.getReservationStart()) && stop.after(reservation.getReservationStop()) ) ||
                ( start.after(reservation.getReservationStart()) && stop.before(reservation.getReservationStop()) ) ||
                ( start.before(reservation.getReservationStart()) && stop.after(reservation.getReservationStart()) ) ||
                ( start.before(reservation.getReservationStop()) && stop.after(reservation.getReservationStop()) ) )
                return false;
        return true;
    }
    
    public Date getStart() {
        return this.start;
    }
    
    public void setStart(Date start) {
        this.start = start;
    }
    
    public Date getStop() {
        return this.stop;
    }
    
    public void setStop(Date stop) {
        this.stop = stop;
    }
    
    public TimeZone getTimeZone() {
        return TimeZone.getDefault();
    }
    
    public Map<String, Client> getClients() {
        return this.clients;
    }
    
    public String getClientLogin() {
        return this.clientLogin;
    }
    
    public void setClientLogin(String string) {
        this.clientLogin = string;
    }
    
    private void chooseClient() {
        if(userService.getUser(clientLogin) != null)
            if(userService.getUser(clientLogin) instanceof Client)
                if(userService.getUser(clientLogin).getIsActive())
                    reservation.setClient((Client) userService.getUser(clientLogin));
                else throw new IllegalArgumentException("Client is inactive");
            else throw new IllegalArgumentException("Only clients can reserve saunas");
        else throw new IllegalArgumentException("Client with this login does not exists");
    }
}
