package com.mycompany.store.Controllers;

import com.mycompany.store.Model.Sauna;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.inject.Inject;
import java.io.Serializable;
import com.mycompany.store.Services.SaunaService;


@Named(value = "updateSaunaController")
@ConversationScoped
public class updateSaunaController implements Serializable {

    @Inject
    private SaunaService saunaService;
    
    @Inject
    private Conversation conversation;
    
    private Sauna sauna;
    
    public updateSaunaController() {
    }
    
    @PostConstruct
    private void init()
    {
        sauna = new Sauna();
    }
    
    public Sauna getSauna() {
        return sauna;
    }
    
    public String updateSauna() {
        if(!conversation.isTransient())
            conversation.end();
        conversation.begin();
        return "updateSauna";
    }
    
    public String updateSaunaConfirm() {
        saunaService.updateSauna(sauna.getNumber(), sauna.getPricePerHour());
        conversation.end();
        return "listSaunas";
    }
}
