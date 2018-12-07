/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverw.msghandler;

import com.mycompany.serverw.entities.PreconventionSingleton;
import com.mycompany.univshared.utilities.Preconvention;
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
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "PreconventionTraitee"),

        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "PreconventionTraiteeWS")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class EnvoieReponse implements MessageListener {
    
    @EJB
    PreconventionSingleton preconvSing;
    
    public EnvoieReponse() {
    }
    
    @Override
    public void onMessage(Message message) {
System.out.println("ICIIII");
     /*   if (message instanceof ObjectMessage) {
            try {
                ObjectMessage om = (ObjectMessage) message;
                Object obj = om.getObject();
                if (obj instanceof Preconvention) {
                    Preconvention prec = (Preconvention) obj;
                    System.out.println("Preconvention " + prec.getRefConv() + " déposée");

                    //déclencher la réponse finale
                     preconvSing.majPreConvention(prec);
                    System.out.println("envoi finale vers le serveur Web" + prec.toString());
                }
            } catch (JMSException ex) {
                Logger.getLogger(EnvoieReponse.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("non objectMsg sent");
        } */
        
    }
    
}
