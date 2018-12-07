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
                System.out.println("TYPE DU MSG RECU " + type);
                Object obj = om.getObject();
                System.out.println("object msg " + obj.getClass());
                if (obj instanceof Preconvention) {
                    Preconvention preconvRec = (Preconvention) obj;
                    System.out.println("Received: " + preconvRec.toString());
                    
                    switch(type){
                        case "Juridique":
                            if(!preconvSing.getAll().containsKey(preconvRec.getRefConv())){
                                preconvSing.ajouterPreConvention(preconvRec);
                                
                            System.out.println("enter juri");
                            }else{
                                 preconvSing.getPrevention(preconvRec.getRefConv()).setRepJur(preconvRec.getRepJur());
                            }
                            break;
                            
                        case"Enseignement":
                             if(!preconvSing.getAll().containsKey(preconvRec.getRefConv())){
                                preconvSing.ajouterPreConvention(preconvRec);
                            System.out.println("enter ens");
                            }else{
                                 preconvSing.getPrevention(preconvRec.getRefConv()).setRepEn(preconvRec.getRepEn());
                            }
                             break;
                    
                        case"Scolarite":
                             if(!preconvSing.getAll().containsKey(preconvRec.getRefConv())){
                                preconvSing.ajouterPreConvention(preconvRec);
                            System.out.println("enter sco");
                            }else{
                                 preconvSing.getPrevention(preconvRec.getRefConv()).setRepSco(preconvRec.getRepSco());
                            }
                             break;                            
                }
                    System.out.println(" valEN "+preconvSing.getPrevention(preconvRec.getRefConv()).getRepEn().getValRep()+" valJur "+preconvSing.getPrevention(preconvRec.getRefConv()).getRepJur().getValRep()+" val sco "+preconvSing.getPrevention(preconvRec.getRefConv()).getRepSco().getValRep());
                    //envoie vers 
                    if (preconvSing.getPrevention(preconvRec.getRefConv()).isAllRep()) {
                        System.out.println("SENDING TO TOPIC FINAAAAL");
                        preconvSing.depotPreconvTraiteeTopic(preconvRec.getRefConv());
                    }
                }
            } catch (JMSException ex) {
                Logger.getLogger(GestionMessagesQueue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
