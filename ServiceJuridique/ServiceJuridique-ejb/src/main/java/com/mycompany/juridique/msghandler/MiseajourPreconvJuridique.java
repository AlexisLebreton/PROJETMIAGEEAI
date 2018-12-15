/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.juridique.msghandler;

import com.mycompany.juridique.entities.PreconventionSingleton;
import com.mycompany.traitementMetier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import com.mycompany.univshared.utilities.Preconvention;
import com.mycompany.verificationsEntreprise;


/**
 *
 * @author maha-
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "PreconventionDeposee")

    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "PreconventionDeposeeSJ")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class MiseajourPreconvJuridique implements MessageListener {

    @EJB
    PreconventionSingleton precs;

    public traitementMetier juridique ;

    public MiseajourPreconvJuridique() {
    }

    //reception et lancement des traitements de la préconvention 
    @Override
    public void onMessage(Message message) {

        if (message instanceof ObjectMessage) {
            try {
                ObjectMessage om = (ObjectMessage) message;
                Object obj = om.getObject();
                if (obj instanceof Preconvention) {
                    Preconvention prec = (Preconvention) obj;

                    //déclencher lA vérification
                    Preconvention p = this.validationJuridique(prec);
                    System.out.println("vérifications juridiques terminés pour " + prec.toString());
                }
            } catch (JMSException ex) {
                Logger.getLogger(MiseajourPreconvJuridique.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("non objectMsg sent");
        }
    }

    public Preconvention validationJuridique(Preconvention p) {
        juridique =new traitementMetier();
        
        //récupérer les boolean des 3 vérifications
        Boolean finV = juridique.periodeStageOK(p) && juridique.gratificationOK(p)&& juridique.sirenOK(p);
        //envoie de la preconvention
        return precs.validerJuridique(p, finV, p.getRepJur().getCauseRep());
       
    }
}
