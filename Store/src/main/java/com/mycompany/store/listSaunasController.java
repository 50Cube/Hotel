package com.mycompany.store;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;

@Named(value = "listSaunasController")
@ViewScoped
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
    
    public Map<Integer, Sauna> getSaunas()
    {
        return saunas;
    }
    
    public void deleteSauna(int number) throws Exception
    {
        saunaService.deleteSauna(number);
        loadSaunas();
    }
    
}
