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
        System.out.println("msg in queue: to handle by gestion preconv");
        System.out.println("MSG!!!!!!"+message.getClass());
        if (message instanceof ObjectMessage) {
            try {
                ObjectMessage om = (ObjectMessage) message;
                System.out.println("om is "+om.toString());
                Object obj = om.getObject();
                System.out.println("object msg "+obj.getClass());
                if (obj instanceof Preconvention) {
                    Preconvention preconvRec = (Preconvention) obj;
                    System.out.println("Received: " + preconvRec.toString());

                    Preconvention preconvEnr = preconvSing.getPrevention(preconvRec.getRefConv());

                    if (preconvEnr == null) {
                        // si preconv recu est nouvelle, ajout à hashmap de gestion preconv
                        preconvSing.ajouterPreConvention(preconvRec);
                        preconvEnr = preconvRec;
                    } else {
                        // sinon maj traitement de la preconv deja presente
                        ReponseTraitPrec repJurRec = preconvRec.getRepJur();
                        ReponseTraitPrec repEnRec = preconvRec.getRepEn();
                        ReponseTraitPrec repScoRec = preconvRec.getRepSco();
                        if (repJurRec != null) {
                            preconvEnr.setRepJur(repJurRec);
                        }
                        System.out.println("repJurRep est " + repJurRec == null);
                        if (repEnRec != null) {
                            preconvEnr.setRepEn(repJurRec);
                        }
                        System.out.println("repEnRec est " + repEnRec == null);
                        if (repScoRec != null) {
                            preconvEnr.setRepSco(repJurRec);
                        }
                        System.out.println("repScoRec est " + repScoRec == null);
                        preconvSing.majPreConvention(preconvEnr);
                    }

                    //envoie vers 
                    if (preconvEnr.isAllRep()) {
                        preconvSing.depotPreconvTraiteeTopic(preconvEnr.getRefConv());
                    }
                }
            } catch (JMSException ex) {
                Logger.getLogger(GestionMessagesQueue.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("ERROR!!!!!!!!!!");
            }
        }
    }

}
