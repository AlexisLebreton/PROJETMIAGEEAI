/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestpreconv.msghandler;

import com.mycompany.univshared.utilities.Preconvention;
import com.mycompany.univshared.utilities.ReponseTraitPrec;
import com.mycompany.gestpreconv.entities.PreconventionSingleton;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 *
 * @author maha-
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "GestionnairePreconventions")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class GestionMessagesQueue implements MessageListener {
    
    @EJB
    PreconventionSingleton preconvSing;
    
    public GestionMessagesQueue() {
    }
    
    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            ObjectMessage text = (ObjectMessage) message;
            Preconvention preconvRec;
            try {
                preconvRec = (Preconvention) ((ObjectMessage) message).getObject();
                System.out.println("Received: " + preconvRec.toString());
            
                Preconvention preconvEnr = preconvSing.getPrevention(preconvRec.getRefConv());
                
                if (preconvEnr == null){
                    // si preconv recu est nouvelle, ajout à hashmap de gestion preconv
                    preconvSing.ajouterPreConvention(preconvRec);
                    preconvEnr = preconvRec;
                }else{
                    // sinon maj traitement de la preconv deja presente
                    ReponseTraitPrec repJurRec = preconvRec.getRepJur();
                    ReponseTraitPrec repEnRec = preconvRec.getRepEn();
                    ReponseTraitPrec repScoRec = preconvRec.getRepSco();
                    if (repJurRec != null){ preconvEnr.setRepJur(repJurRec); }
                    if (repEnRec != null){ preconvEnr.setRepEn(repJurRec); }
                    if (repScoRec != null){ preconvEnr.setRepSco(repJurRec); }
                    preconvSing.majPreConvention(preconvEnr);
                }
                
                //envoie vers 
                if (preconvEnr.isAllRep()){
                    preconvSing.depotPreconvTraiteeTopic(preconvEnr.getRefConv());
                }
                
            } catch (JMSException ex) {
                Logger.getLogger(GestionMessagesQueue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
