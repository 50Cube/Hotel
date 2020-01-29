/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.store.Services.CustomExceptions;

/**
 *
 * @author Gabriel
 */
public class RentableRentedException extends Exception{
  
    public RentableRentedException(String errorMessage) {
        super(errorMessage);
    }

}
