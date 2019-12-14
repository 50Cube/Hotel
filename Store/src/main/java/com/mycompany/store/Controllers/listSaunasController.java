package com.mycompany.store.Controllers;

import com.mycompany.store.Services.SaunaService;
import com.mycompany.store.Model.Sauna;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;

@Named(value = "listSaunasController")
@ViewScoped
public class listSaunasController implements Serializable{

    @Inject
    private SaunaService saunaService;
    
    @Inject
    private DataHolder dh;
    
    private Map<Integer, Sauna> saunas;
    private String filter;
    
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
    
    public String saveData(Sauna sauna) {
        dh.setSaunaNumber(sauna.getNumber());
        dh.setSaunaPrice(sauna.getPricePerHour());
        return "updateSauna.xhtml";
    }
    
    public void getFilteredSaunas() {
        saunas = saunaService.getFilteredSaunas(this.filter);
    }
    
    public String getFilter() {
        return this.filter;
    }
    
    public void setFilter(String filter) {
        this.filter = filter;
    }
}
