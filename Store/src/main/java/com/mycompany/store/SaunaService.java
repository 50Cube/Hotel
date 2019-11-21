package com.mycompany.store;

import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;


@Named(value = "saunaService")
@Dependent
public class SaunaService {

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
    
     public void updateSauna(int number, double price, boolean reserved)
    {
        saunaRepository.updateSauna(number, price, reserved);
    }
    
    public void deleteSauna(int number) throws Exception
    {
        saunaRepository.deleteSauna(number);
    }
}
