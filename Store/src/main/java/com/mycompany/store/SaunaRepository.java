package com.mycompany.store;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;


@Named(value = "saunaRepository")
@Dependent
public class SaunaRepository {

    private Map<Integer, Sauna> saunas;
    
    public SaunaRepository() {
        saunas = new HashMap<>();
    }
    
    public Map<Integer, Sauna> getSaunas()
    {
        return this.saunas;
    }
    
    public Sauna getSauna(int number)
    {
        return saunas.get(number);
    }
    
    public void addSauna(Sauna sauna)
    {
        saunas.put(sauna.getNumber(), sauna);
    }
    
     public void updateSauna(int number, double price, boolean reserved)
    {
        for(Sauna sauna : saunas.values())
            if(sauna.getNumber() == number)
            {
                sauna.setPricePerHour(price);
                sauna.setIsReserved(reserved);
            }
    }
    
    public void deleteSauna(int number) throws Exception
    {
        for(Sauna sauna : saunas.values())
            if(sauna.getNumber() == number){
                if(!sauna.getIsReserved())
                    saunas.remove(sauna.getNumber());
                else throw new Exception("The sauna is currently reserved");}
            else throw new Exception("Sauna with that number does not exist");
    }
    
    @PostConstruct
    private void initDataSauna()
    {
        addSauna(new Sauna(1,20));
    }
}