package com.mycompany.store;

import javax.inject.Named;
import javax.enterprise.context.Dependent;


@Named(value = "product")
@Dependent
public class Product {

    private String name;
    private double price;
    
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    public String getName(){
        return this.name;
    }
    
    public double getPrice(){
        return this.price;
    }
    
}
