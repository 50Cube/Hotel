package com.mycompany.store.Controllers;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@Named(value = "dataHolder")
@SessionScoped
public class DataHolder implements Serializable {

    private int roomNumber;
    private double roomArea;
    private int roomBeds;
    
    private int saunaNumber;
    private double saunaPrice;

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
}
