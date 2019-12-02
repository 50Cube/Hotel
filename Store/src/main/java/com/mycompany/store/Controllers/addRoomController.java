package com.mycompany.store.Controllers;

import com.mycompany.store.Services.RoomService;
import com.mycompany.store.Model.Room;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.inject.Inject;

@Named(value = "addRoomController")
@ConversationScoped
public class addRoomController implements Serializable{

    @Inject
    private RoomService roomService;
    
    @Inject
    private Conversation conversation;
    
    private Room room;
    
    public addRoomController() {
    }
    
    @PostConstruct
    private void init()
    {
        room = new Room();
    }
    
    public Room getRoom() {
        return room;
    }
    
    public String addRoom() {
        if(!conversation.isTransient())
            conversation.end();
        conversation.begin();
        return "addRoom";
    }
    
    public String addRoomConfirm() {
        roomService.addRoom(room);
        conversation.end();
        return "home";
    }
    
}
