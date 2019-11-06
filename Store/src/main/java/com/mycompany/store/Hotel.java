package com.mycompany.store;

import javax.inject.Named;
import javax.enterprise.context.Dependent;


@Named(value = "hotel")
@Dependent
public class Hotel {

    private RoomRepository roomRepository;
    private RentRepository rentRepository;
    
    public Hotel(RoomRepository rop, RentRepository rep) {
        this.roomRepository = rop;
        this.rentRepository = rep;
    }
    
}
