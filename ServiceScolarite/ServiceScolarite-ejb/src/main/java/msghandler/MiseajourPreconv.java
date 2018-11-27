/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexis.lebreton.utilities.server;

import com.alexis.lebreton.serviceEnseignement.verificationsEnseignement;
import com.alexis.lebreton.serviceJuridique.traitementMetier;
import com.alexis.lebreton.serviceScolarite.VérificationAdministrative;
import com.alexis.lebreton.utilities.Preconvention;
import com.alexis.lebreton.utilities.entities.PreconventionSingleton;
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
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "PreconventionDeposee")
    ,
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "PreconventionDeposee")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "PreconventionDeposee")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class MiseajourPreconv implements MessageListener {
    @EJB
    PreconventionSingleton precs;
    
    public traitementMetier juridique;
    public VérificationAdministrative scolarité;
    public verificationsEnseignement enseign;
    
    public MiseajourPreconv() {
    }
    
    @Override
    public void onMessage(Message message) {
               if (message instanceof ObjectMessage) {
             try {
                 ObjectMessage om = (ObjectMessage) message;
                 Object obj = om.getObject();
                 if (obj instanceof Preconvention) {
                     Preconvention prec = (Preconvention) obj;
                     System.out.println("Preconvention " + prec.getRefConv() + " tdéposée");
                     //déclencher les 3 vérifications à faire (juridique + enseignement +
                     Preconvention p = juridique.validationJuridique(prec);
                     p = scolarité.vérifierEtud(p);
                     p= enseign.vérifier(p);
                     System.out.println("vérifications terminés pour "+p.toString());
                 }
             } catch (JMSException ex) {
                 Logger.getLogger(MiseajourPreconv.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
    }
    
}
