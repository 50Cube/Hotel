package com.mycompany.store.Controllers;

import com.mycompany.store.Reservation;
import com.mycompany.store.Services.ReservationService;
import java.io.Serializable;
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
    private Conversation conversation;
    
    private Reservation reservation;
    
    public AddReservationController() {
    }
    
    @PostConstruct
    private void init() {
        reservation = new Reservation();
    }
    
    public Reservation getReservation() {
        return reservation;
    }
    
    public String addReservation() {
        if(!conversation.isTransient())
            conversation.end();
        conversation.begin();
        return "addReservation";
    }
    
    public String addReservationConfirm() throws Exception {
        reservationService.addReservation(reservation);
        conversation.end();
        return "home";
    }
}
