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
        saunaRepository.updateSauna(saunaRepository.getSauna(number), price);
    }
    
    public void deleteSauna(int number) throws Exception
    {
        if(saunaRepository.getSaunas().containsKey(number))
            saunaRepository.deleteSauna(saunaRepository.getSauna(number));
        else throw new Exception("Sauna with that number does not exist");
    }
    
    public Map<Integer, Sauna> getFilteredSaunas(String input) {
        return saunaRepository.getFilteredSaunas(input);
    }
}
