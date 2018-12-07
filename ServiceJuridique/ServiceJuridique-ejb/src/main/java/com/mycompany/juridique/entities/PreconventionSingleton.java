/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.juridique.entities;

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
 * @author ben
 */
@Singleton
@LocalBean
public class PreconventionSingleton {

    @Resource(lookup = "PreconventionDeposee")
    private Topic topic;

    @Resource(lookup = "GestionnairePreconventions")
    private Queue queue;

    @Inject
    private JMSContext context;

    private HashMap<Integer, Preconvention> preconvs = new HashMap<>();
    private Integer lastid = 0;

    public void ajouterPreConvention(Preconvention prec) {
        this.preconvs.put(lastid, prec);
        this.lastid++;
    }

    public Preconvention validerJuridique(Preconvention prec, boolean v, String cause) {
        System.out.println("preparing to send to queue after update juridique");
        prec.getRepJur().setValRep(v);
        prec.getRepJur().setCauseRep(cause);
        ObjectMessage obm = context.createObjectMessage();
        try {
            obm.setJMSType("Juridique");
            obm.setObject(prec);
        } catch (JMSException ex) {
            Logger.getLogger(PreconventionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
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
