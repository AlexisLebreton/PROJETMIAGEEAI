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

/**
 *
 * @author ben
 */
@Singleton
@LocalBean
public class PreconventionSingleton {
    @Resource(lookup = "jms/PreconventionDeposee")
    private Topic topic;
    
    @Resource(lookup="jms/GestionnairePreconventions")
    private Queue queue;
    
    @Inject
    private JMSContext context;
    
    private static PreconventionSingleton INSTANCE = null;
    
    private HashMap<Integer, Preconvention> preconvs = new HashMap<>();
    private Integer lastid=0;
    
    public static PreconventionSingleton getInstance()
    {           
        if (INSTANCE == null)
        {   INSTANCE = new PreconventionSingleton(); 
        }
        return INSTANCE;
    }
    
     public Preconvention ajouterPreConvention(String nom, String prenom, String numeroEtudiant, String niveau, String intitule, String compagnie, String numeroContrat, String denomination, String siren, Date debut, Date fin, int gratification, String resume) {      
        Preconvention prec = new Preconvention(lastid, nom, prenom, numeroEtudiant, niveau, intitule, compagnie, numeroContrat, denomination, siren, debut, fin, gratification, resume);
        this.preconvs.put(lastid, prec);
       // deposerPreconv(lastid);
        this.lastid ++;        
        return prec;
    }
     
    public void ajouterPreConvention(Preconvention prec) {      
        this.preconvs.put(lastid, prec);
        this.lastid ++;   
    }

    public Preconvention validerJuridique(int refPreConv, boolean v,String cause) {
        Preconvention prec = preconvs.get(refPreConv);
        prec.getRepJur().setValRep(v);
        prec.getRepJur().setCauseRep(cause);      
        context.createProducer().send(queue, prec);
        return prec;
    }

    public Preconvention validerScolarite(int refPreConv, boolean v,String cause) {
        Preconvention prec = preconvs.get(refPreConv);
        prec.getRepSco().setValRep(v);
        prec.getRepSco().setCauseRep(cause);
        context.createProducer().send(queue, prec);
        return prec;
    }

    public Preconvention validerEnseignement(int refPreconv,boolean b, String cause) {
        Preconvention prec = preconvs.get(refPreconv);
        prec.getRepEn().setValRep(b);
        prec.getRepEn().setCauseRep(cause);
        context.createProducer().send(queue, prec);
        return prec;
    }
    
    public Preconvention getPrevention(int refPreconv) {
        return this.preconvs.get(refPreconv);
    }

    public Etudiant getEtudiant(int refPreconv) {
       return this.preconvs.get(refPreconv).getEtudiant();
    }

    public HashMap<Integer,Preconvention> getAll(){
        return this.preconvs;
    }
    
    //sert à déposer une preconvention sur le topic dédié au dépot de demande préconventions
    public Preconvention deposerPreconv(int refPrec) {
        Preconvention prec = preconvs.get(refPrec);
        //ObjectMessage om = context.createObjectMessage(prec);
        context.createProducer().send(topic, prec);
        return prec;
    }
}
