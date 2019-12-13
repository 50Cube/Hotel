package com.mycompany.store.Controllers;

import com.mycompany.store.Model.Room;
import com.mycompany.store.Model.Sauna;
import com.mycompany.store.Model.User;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@Named(value = "dataHolder")
@SessionScoped
public class DataHolder implements Serializable {

    private Room room;
    
    private int roomNumber;
    private double roomArea;
    private int roomBeds;
    
    private Sauna sauna;
    
    private int saunaNumber;
    private double saunaPrice;

    private User user;
    
    public Room getRoom() {
        return this.room;
    }
    
    public void setRoom(Room room) {
        this.room = room;
    }
    
    
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getRoomArea() {
        return roomArea;
    }
    
    public void setRoomArea(double roomArea) {
        this.roomArea = roomArea;
    }

    public int getRoomBeds() {
        return roomBeds;
    }

    public void setRoomBeds(int roomBeds) {
        this.roomBeds = roomBeds;
    }
    
    
    
    public Sauna getSauna() {
        return this.sauna;
    }
    
    public void setSauna(Sauna sauna) {
        this.sauna = sauna;
    }
    
    
    public int getSaunaNumber() {
        return saunaNumber;
    }

    public void setSaunaNumber(int saunaNumber) {
        this.saunaNumber = saunaNumber;
    }

    public double getSaunaPrice() {
        return saunaPrice;
    }

    public void setSaunaPrice(double saunaPrice) {
        this.saunaPrice = saunaPrice;
    }
    
    
    
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
}
