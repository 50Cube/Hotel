package com.mycompany.store.Controllers;

import com.mycompany.store.Model.Client;
import com.mycompany.store.Model.Room;
import com.mycompany.store.Rent;
import com.mycompany.store.Services.RentService;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "addRentController")
@ConversationScoped
public class AddRentController implements Serializable {

    @Inject
    private RentService rentService;
    
    @Inject
    private Conversation conversation;
    
    private Rent rent;
    private Date start;
    private Date stop;
    
    public AddRentController() {
    }
    
    @PostConstruct
    private void init() {
        rent = new Rent();
    }
    
    public Rent getRent() {
        return rent;
    }
    
    public String addRent(Room room) {
        if(!conversation.isTransient())
            conversation.end();
        conversation.begin();
        
        rent.setRoom(room);
        rent.setClient(new Client("","","","",true));
        
        return "addRent";
    }
    
    public String addRentConfirm() throws Exception {
        Calendar tmp1 = new GregorianCalendar();
        Calendar tmp2 = new GregorianCalendar();
        tmp1.setTime(start);
        tmp2.setTime(stop);
        rent.setRentStart(tmp1);
        rent.setRentStop(tmp2);
        rentService.addRent(rent);
        
        if(stop.before(start)) throw new Exception("Beginning date must be earlier than end date");
        if(!checkIfRented(tmp1,tmp2)) throw new Exception("Room is already rent");
        conversation.end();
        return "home";
    }
    
    private boolean checkIfRented(Calendar start, Calendar stop) {
        for(Rent rent : rentService.getCurrentRents().values())
            if( ( start.before(rent.getRentStart()) && stop.after(rent.getRentStop()) ) ||
                ( start.after(rent.getRentStart()) && stop.before(rent.getRentStop()) ) ||
                ( start.before(rent.getRentStart()) && stop.after(rent.getRentStart()) ) ||
                ( start.before(rent.getRentStop()) && stop.after(rent.getRentStop()) ) )
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
}
