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
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "PreconventionTraitee")
    ,
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "PreconventionTraitee")
    
    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "PreconventionTraitee")
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
        if (message instanceof ObjectMessage) {
            ObjectMessage text = (ObjectMessage) message;
            Preconvention preconvTraite;
            try {
                preconvTraite = (Preconvention) ((ObjectMessage) message).getObject();
                System.out.println("Server Web Received: " + preconvTraite.toString());
                preconvSing.majPreConvention(preconvTraite);
            } catch (JMSException ex) {
                //Logger.getLogger(GestionMessagesTopic.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
