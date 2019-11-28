package com.mycompany.store.Controllers;

import com.mycompany.store.Model.Room;
import com.mycompany.store.Services.RoomService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named(value = "updateRoomController")
@ConversationScoped
public class updateRoomController implements Serializable {

    @Inject
    private RoomService roomService;
    
    @Inject
    private Conversation conversation;
    
    private Room room;
    
    public updateRoomController() {
    }
    
    @PostConstruct
    private void init()
    {
        room = new Room();
    }
    
    public Room getRoom() {
        return room;
    }
    
    public String updateRoom() {
        conversation.begin();
        return "updateRoom";
    }
    
    public String updateRoomConfirm() {
        roomService.updateRoom(room.getNumber(), room.getArea(), room.getBeds(), room.getIsRent());
        conversation.end();
        return "listRooms";
    }
}
