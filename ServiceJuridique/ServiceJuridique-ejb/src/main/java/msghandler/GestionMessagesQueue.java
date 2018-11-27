/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexis.lebreton.utilities.server;

import com.alexis.lebreton.utilities.Preconvention;
import com.alexis.lebreton.utilities.ReponseTraitPrec;
import com.alexis.lebreton.utilities.entities.PreconventionSingleton;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
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
                
                PreconventionSingleton preconvSing = PreconventionSingleton.getInstance();
                
                Preconvention preconvEnr = preconvSing.getPrevention(preconvRec.getRefConv());
                
                if (preconvEnr == null){
                    preconvSing.ajouterPreConvention(preconvRec);
                }else{
                    ReponseTraitPrec repJurRec = preconvRec.getRepJur();
                    ReponseTraitPrec repEnRec = preconvRec.getRepEn();
                    ReponseTraitPrec repScoRec = preconvRec.getRepSco();
                    if (repJurRec != null){ preconvEnr.setRepJur(repJurRec); }
                    if (repEnRec != null){ preconvEnr.setRepEn(repJurRec); }
                    if (repScoRec != null){ preconvEnr.setRepSco(repJurRec); }
                }
                
                if (preconvEnr.isAllRep()){
                    //TODO Envoie à 2nd queu
                }
                
                
                
            } catch (JMSException ex) {
                Logger.getLogger(GestionMessagesQueue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
