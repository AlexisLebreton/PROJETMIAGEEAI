/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.deptens.msghandler;


import com.mycompany.deptens.entities.PreconventionSingleton;
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
import java.util.Scanner;

/**
 *
 * @author maha-
 */
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "PreconventionDeposee")
   
    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "PreconventionDeposeeDE")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class MiseajourPreconvEns implements MessageListener {
    @EJB
    PreconventionSingleton precs;
    
    
    public MiseajourPreconvEns() {
    }
    
    @Override
    public void onMessage(Message message) {
        System.out.println("here");
           if (message instanceof ObjectMessage) {
             try {
                 ObjectMessage om = (ObjectMessage) message;
                 Object obj = om.getObject();
                 if (obj instanceof Preconvention) {
                     Preconvention prec = (Preconvention) obj;
                     System.out.println("Preconvention " + prec.getRefConv() + " déposée");
                     
                     //déclencher lA vérification
                     Preconvention p = vérifier(prec);
                     System.out.println("vérifications terminés pour ");
                 }
             } catch (JMSException ex) {
                 Logger.getLogger(MiseajourPreconvEns.class.getName()).log(Level.SEVERE, null, ex);
             }
        }

        }
    
        public Preconvention vérifier(Preconvention p){
       System.out.println("Voici les détails de la pre convention \n \t Nom prenom étudiant:"+p.getEtudiant().getPrenom()+" "+p.getEtudiant().getNom()+
        "\n\t diplome préparé: "+p.getDiplome()+"\n\t sujet "+p.getResume()
        ); 
        
        System.out.println("validez-vous ce sujet? O/N");
        Scanner sc  = new Scanner(System.in);
        String rep = sc.nextLine();
        if(rep=="O"){
            precs.validerEnseignement(p.getRefConv(), true, "");
        }else{
             precs.validerEnseignement(p.getRefConv(), false, "Sujet non adequat à la formation de l etudiant");
        }
        return p;
    }
}
