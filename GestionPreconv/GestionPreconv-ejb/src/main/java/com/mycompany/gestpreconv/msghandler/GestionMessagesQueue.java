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

    // sinon maj traitement de la preconv deja presente
    ReponseTraitPrec repJurRec;
    ReponseTraitPrec repEnRec;
    ReponseTraitPrec repScoRec;
    HashMap <Integer,ArrayList<Boolean>> validations=new HashMap<>();
    ArrayList arrayBooleans = new ArrayList<Boolean>();
    
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
                            if(!validations.containsKey(preconvRec.getRefConv())){
                                arrayBooleans.add(preconvRec.getRepJur().getValRep());
                                validations.put(preconvRec.getRefConv(),arrayBooleans);
                            }else{
                                validations.get(preconvRec.getRefConv()).add(preconvRec.getRepJur().getValRep());
                            }
                            break;
                            
                        case"Enseignement":
                             if(!validations.containsKey(preconvRec.getRefConv())){
                                arrayBooleans.add(preconvRec.getRepEn().getValRep());
                                validations.put(preconvRec.getRefConv(),arrayBooleans);
                            }else{
                                validations.get(preconvRec.getRefConv()).add(preconvRec.getRepEn().getValRep());
                            }
                             break;
                    
                        case"Scolarite":
                             if(!validations.containsKey(preconvRec.getRefConv())){
                                arrayBooleans.add(preconvRec.getRepSco().getValRep());
                                validations.put(preconvRec.getRefConv(),arrayBooleans);
                            }else{
                                validations.get(preconvRec.getRefConv()).add(preconvRec.getRepSco().getValRep());
                            }
                             break;                            
                }
                    //envoie vers 
                    if (preconvRec.isAllRep()) {
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
