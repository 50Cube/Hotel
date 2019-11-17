package com.mycompany.store;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;


@Named(value = "saunaRepository")
@Dependent
public class SaunaRepository {

    private List<Sauna> saunas;
    
    public SaunaRepository() {
        saunas = new ArrayList<>();
    }
    
    public List<Sauna> getSaunas()
    {
        return this.saunas;
    }
    
    public Sauna getSauna(int number)
    {
        return saunas.get(number);
    }
    
    public void addSauna(Sauna sauna)
    {
        saunas.add(sauna);
    }
    
     public void updateSauna(int number, double price, boolean reserved)
    {
        for(Sauna sauna : saunas)
            if(sauna.getNumber() == number)
            {
                sauna.setPricePerHour(price);
                sauna.setIsReserved(reserved);
            }
    }
    
    public void deleteSauna(int number) throws Exception
    {
        for(Sauna sauna : saunas)
            if(sauna.getNumber() == number){
                if(!sauna.getIsReserved())
                    saunas.remove(sauna.getNumber());
                else throw new Exception("The room is currently rent");}
            else throw new Exception("Room with that number does not exist");
    }
    
    @PostConstruct
    private void initDataSauna()
    {
        addSauna(new Sauna(1,20));
    }
}