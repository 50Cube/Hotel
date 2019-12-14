package com.mycompany.store.Services;

import com.mycompany.store.Repositories.SaunaRepository;
import com.mycompany.store.Model.Sauna;
import java.io.Serializable;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;


@Named(value = "saunaService")
@Dependent
public class SaunaService implements Serializable{

    @Inject
    private SaunaRepository saunaRepository;
    
    public SaunaService() {
    }
    
     public Map<Integer, Sauna> getSaunas()
    {
        return saunaRepository.getSaunas();
    }
    
    public Sauna getSauna(int number)
    {
        return saunaRepository.getSauna(number);
    }
    
    public void addSauna(Sauna sauna)
    {
        saunaRepository.addSauna(sauna);
    }
    
     public void updateSauna(int number, double price)
    {
        saunaRepository.updateSauna(number, price);
    }
    
    public void deleteSauna(int number) throws Exception
    {
        String message = "";
        if(!saunaRepository.deleteSauna(number,message))
            throw new Exception(message);
    }
    
    public Map<Integer, Sauna> getFilteredSaunas(String input) {
        return saunaRepository.getFilteredSaunas(input);
    }
}
