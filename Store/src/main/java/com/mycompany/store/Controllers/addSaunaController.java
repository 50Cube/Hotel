package com.mycompany.store.Controllers;

import com.mycompany.store.Model.Sauna;
import com.mycompany.store.Services.SaunaService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.inject.Inject;


@Named(value = "addSaunaController")
@ConversationScoped
public class addSaunaController implements Serializable{
    
    @Inject
    private SaunaService saunaService;
    
    @Inject
    private Conversation conversation;
    
    private Sauna sauna;
    
    public addSaunaController() {
    }
    
    @PostConstruct
    private void init() {
        sauna = new Sauna();
    }
    
    public Sauna getSauna() {
        return sauna;
    }
    
    public String addSauna() {
        conversation.begin();
        return "addSauna";
    }
    
    public String addSaunaConfirm() {
        saunaService.addSauna(sauna);
        conversation.end();
        return "home";
    }
}
