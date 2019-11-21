package com.mycompany.store;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Named(value = "listSaunasController")
@Dependent
public class listSaunasController {

    @Inject
    private SaunaService saunaService;
    
    private Map<Integer, Sauna> saunas;
    
    public listSaunasController() {
    }
    
    @PostConstruct
    public void loadSaunas()
    {
        saunas = saunaService.getSaunas();
    }
    
    public void removeSauna(int number) throws Exception
    {
        saunaService.deleteSauna(number);
        loadSaunas();
    }
    
}
