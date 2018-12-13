/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.scol.msghandler;

import com.mycompany.scol.entities.EtudiantsSingleton;
import com.mycompany.scol.entities.PreconventionSingleton;
import com.mycompany.univshared.utilities.Diplome;
import com.mycompany.univshared.utilities.Etudiant;

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

/**
 *
 * @author maha-
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "PreconventionDeposee")

    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "PreconventionDeposeeSC")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class MiseajourPreconvScol implements MessageListener {

    @EJB
    PreconventionSingleton precs;

    @EJB
    EtudiantsSingleton etdSingl;

    public MiseajourPreconvScol() {
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            try {
                ObjectMessage om = (ObjectMessage) message;
                Object obj = om.getObject();
                if (obj instanceof Preconvention) {
                    Preconvention prec = (Preconvention) obj;
                    System.out.println("Preconvention " + prec.getRefConv() + " déposée");

                    //déclencher la vérifications : scolarité                      
                    Preconvention p = vérifierEtud(prec);
                    System.out.println("vérifications scolarité terminés pour " + p.getRefConv());
                }
            } catch (JMSException ex) {
                Logger.getLogger(MiseajourPreconvScol.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.err.print("msg sco non valid");
        }
    }

    public Preconvention vérifierEtud(Preconvention p) {
        //verification de l existance de l etudiant et son diplome
        
        String num = p.getEtudiant().getNumeroEtudiant();
        if (etdSingl.exists(num)) {
            Etudiant etuSing = etdSingl.getEtudiant(num);
            if (etuSing.getNom().equals(p.getEtudiant().getNom()) && etuSing.getPrenom().equals(p.getEtudiant().getPrenom())) {
                //etudiant existe bien : vérifier son diplome
                if (p.getDiplome().getIntitule().equals(etdSingl.getEtudiant(num).getDipActuel().getIntitule())) {
                    //diplome renseigné conforme a la base
                    return precs.validerScolarite(p, true, "");
                } else {
                    return precs.validerScolarite(p, false, "mauvais diplome renseigné");
                }

            } else {
                return precs.validerScolarite(p, false, "nom/prenom etudiant erroné");
            }
        } else {
            return precs.validerScolarite(p, false, "numero etudiant introuvable");
        }

    }

}
