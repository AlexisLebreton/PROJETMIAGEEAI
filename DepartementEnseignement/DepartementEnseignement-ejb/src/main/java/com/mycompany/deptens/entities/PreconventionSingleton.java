/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.deptens.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Topic;
import com.mycompany.univshared.utilities.Etudiant;
import com.mycompany.univshared.utilities.Preconvention;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;

/**
 *
 * @author alexis
 * stocke verifie et envoie les preconventions de departement enseignement
 */
@Singleton
@LocalBean
public class PreconventionSingleton {

    // topic source de ses preconventions à valider
    @Resource(lookup = "PreconventionDeposee")
    private Topic topic;

    // queu destination de ses preconventions validés
    @Resource(lookup = "GestionnairePreconventions")
    private Queue queue;

    @Inject
    private JMSContext context;

    private static PreconventionSingleton INSTANCE = null;

    private HashMap<Integer, Preconvention> preconvs = new HashMap<>();
    private Integer lastid = 0;

    public static PreconventionSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PreconventionSingleton();
        }
        return INSTANCE;
    }

    public Preconvention ajouterPreConventionint(int refConv, String nom, String prenom, String numeroEtudiant, String niveau, String intitule, String compagnie, String numeroContrat, String denomination, String siren, Date debut, Date fin, long gratification, String resume) {
        Preconvention prec = new Preconvention(lastid, nom, prenom, numeroEtudiant, niveau, intitule, compagnie, numeroContrat, denomination, siren, debut, fin, gratification, resume);
        this.preconvs.put(lastid, prec);
        this.lastid++;
        return prec;
    }

    public void ajouterPreConvention(Preconvention prec) {
        this.preconvs.put(lastid, prec);
        this.lastid++;
    }

    // envoie de la preconvention
    public Preconvention validerEnseignement(Preconvention prec, boolean b, String cause) {
        // création du message
        prec.getRepEn().setValRep(b);
        prec.getRepEn().setCauseRep(cause);
        ObjectMessage obm = context.createObjectMessage();
        // typage jms
        try {
            obm.setJMSType("Enseignement");
            obm.setObject(prec);
        } catch (JMSException ex) {
            Logger.getLogger(PreconventionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
        // envoie
        context.createProducer().send(queue, obm);
        return prec;
    }

    public Preconvention getPrevention(int refPreconv) {
        return this.preconvs.get(refPreconv);
    }

    public Etudiant getEtudiant(int refPreconv) {
        return this.preconvs.get(refPreconv).getEtudiant();
    }

    public HashMap<Integer, Preconvention> getAll() {
        return this.preconvs;
    }

}
