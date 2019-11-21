package com.mycompany.store;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Named(value = "addRoomController")
@Dependent
public class addRoomController {

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
    
    public String addRoom()
    {
        conversation.begin();
        return "";
    }
    
}
