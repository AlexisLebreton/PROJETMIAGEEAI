/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msghandler;

import com.mycompany.VérificationScolarite;
import entities.PreconventionSingleton;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import utilities.Preconvention;

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
    
   public VérificationScolarite scolarite;
    
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
                     //déclencher la vérifications : enseignement                      
                     Preconvention p = scolarite.vérifierEtud(prec);
                     System.out.println("vérifications terminés pour "+p.toString());
                 }
             } catch (JMSException ex) {
                 Logger.getLogger(MiseajourPreconv.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
    }
    
}
