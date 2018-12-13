/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestpreconv.msghandler;

import com.mycompany.univshared.utilities.Preconvention;
import com.mycompany.univshared.utilities.ReponseTraitPrec;
import com.mycompany.gestpreconv.entities.PreconventionSingleton;
import java.util.ArrayList;
import java.util.HashMap;
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
        System.out.println("msg in queue: to handle by gestion preconv");
        if (message instanceof ObjectMessage) {
            try {
                ObjectMessage om = (ObjectMessage) message;
                String type = om.getJMSType();
                Object obj = om.getObject();
                if (obj instanceof Preconvention) {
                    Preconvention preconvRec = (Preconvention) obj;
                    
                    switch(type){
                        case "Juridique":
                            if(!preconvSing.getAll().containsKey(preconvRec.getRefConv())){
                                preconvSing.ajouterPreConvention(preconvRec);
                                
                            }else{
                                 preconvSing.getPrevention(preconvRec.getRefConv()).setRepJur(preconvRec.getRepJur());
                            }
                            break;
                            
                        case"Enseignement":
                             if(!preconvSing.getAll().containsKey(preconvRec.getRefConv())){
                                preconvSing.ajouterPreConvention(preconvRec);
                            }else{
                                 preconvSing.getPrevention(preconvRec.getRefConv()).setRepEn(preconvRec.getRepEn());
                            }
                             break;
                    
                        case"Scolarite":
                             if(!preconvSing.getAll().containsKey(preconvRec.getRefConv())){
                                preconvSing.ajouterPreConvention(preconvRec);
                            }else{
                                 preconvSing.getPrevention(preconvRec.getRefConv()).setRepSco(preconvRec.getRepSco());
                            }
                             break;                            
                }
                    //envoie vers topic des résultat
                    if (preconvSing.getPrevention(preconvRec.getRefConv()).isAllRep()) {
                        preconvSing.depotPreconvTraiteeTopic(preconvRec.getRefConv());
                    }
                }
            } catch (JMSException ex) {
                Logger.getLogger(GestionMessagesQueue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
