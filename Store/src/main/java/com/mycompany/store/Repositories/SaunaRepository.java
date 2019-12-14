package com.mycompany.store.Repositories;

import com.mycompany.store.Model.Sauna;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;


@Named(value = "saunaRepository")
@ApplicationScoped
public class SaunaRepository {

    private Map<Integer, Sauna> saunas;
    
    public SaunaRepository() {
        saunas = new HashMap<>();
    }
    
    public Map<Integer, Sauna> getSaunas()
    {
        return new HashMap<>(saunas);
    }
    
    public Sauna getSauna(int number)
    {
        return saunas.get(number);
    }
    
    public synchronized void addSauna(Sauna sauna)
    {
        saunas.put(sauna.getNumber(), sauna);
    }
    
     public synchronized void updateSauna(int number, double price)
    {
        for(Sauna sauna : saunas.values())
            if(sauna.getNumber() == number)
            {
                sauna.setPricePerHour(price);
            }
    }
    
    public synchronized boolean deleteSauna(int number, String message)
    {
        for(Sauna sauna : saunas.values())
            if(sauna.getNumber() == number)
            {
                saunas.remove(sauna.getNumber());
                return true;
            }
            else message = "Sauna with that number does not exist";
        return false;
    }
    
    public Map<Integer, Sauna> getFilteredSaunas(String input) {
        Map<Integer, Sauna> tmp = new HashMap<>();
        
        saunas.values().stream().filter((sauna) -> (Integer.toString(sauna.getNumber()).contains(input.trim()))).forEachOrdered((sauna) -> {
            tmp.put(sauna.getNumber(), sauna);
        });
        
        return tmp;
    }
    
    @PostConstruct
    private void initDataSauna()
    {
        addSauna(new Sauna(1,20));
        addSauna(new Sauna(2,25));
    }
}