package com.mycompany.store.Controllers;

import com.mycompany.store.Model.Sauna;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import java.io.Serializable;
import com.mycompany.store.Services.SaunaService;
import javax.enterprise.context.RequestScoped;


@Named(value = "updateSaunaController")
@RequestScoped
public class updateSaunaController implements Serializable {

    @Inject
    DataHolder dh;
    
    @Inject
    private SaunaService saunaService;
    
    private Sauna sauna;
    
    public updateSaunaController() {
    }
    
    @PostConstruct
    private void init()
    {
        sauna = new Sauna(dh.getSaunaNumber(), dh.getSaunaPrice());
    }
    
    public Sauna getSauna() {
        return sauna;
    }
    
    public String updateSauna() {
        saunaService.updateSauna(sauna.getNumber(), sauna.getPricePerHour());
        return "listSaunas";
    }
}
