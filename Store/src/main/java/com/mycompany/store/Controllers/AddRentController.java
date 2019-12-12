package com.mycompany.store.Controllers;

import com.mycompany.store.Rent;
import com.mycompany.store.Services.RentService;
import java.io.Serializable;
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
    
    public AddRentController() {
    }
    
    @PostConstruct
    private void init() {
        rent = new Rent();
    }
    
    public Rent getRent() {
        return rent;
    }
    
    public String addRent() {
        if(!conversation.isTransient())
            conversation.end();
        conversation.begin();
        return "addRent";
    }
    
    public String addRentConfirm() throws Exception {
        rentService.addRent(rent);
        conversation.end();
        return "home";
    }
}
