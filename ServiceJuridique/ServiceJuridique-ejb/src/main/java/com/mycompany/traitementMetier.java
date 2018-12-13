/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;


import com.mycompany.juridique.entities.PreconventionSingleton;
import com.mycompany.univshared.utilities.Preconvention;
import javax.ejb.EJB;

/**
 *
 * @author maha-
 */
public class traitementMetier {
    
    private static String cause ;
    
    @EJB
    PreconventionSingleton ps;

    public traitementMetier() {
        cause="";
    }
    
    
    /*
    vérifie si la durée du stage ne dépasse pas 6 mois et que le stage ne se déroule pas sur 2 annes uiv.
    */
        public  Boolean periodeStageOK(Preconvention p){
        long duree = p.getDureeStage()/30;
        System.out.println(duree);
        if(! (duree < 7 )){
            traitementMetier.cause += " Periode de stage non conforme";
            p.getRepJur().setCauseRep(traitementMetier.cause );
        }
        return duree < 7 ;
    }
    
        /*
        on suppose  la seule règle à vérifier et que l'entreprise doit payer l'etudiant si la durée de stage dépasse deux mois
        */
    public  Boolean gratificationOK(Preconvention p){
        Boolean conforme = true;
        
        if(p.getDureeStage() > 2 ){
            if(p.getGratification()==0){
            traitementMetier.cause += " gratification non conforme";
            p.getRepJur().setCauseRep(traitementMetier.cause );
            conforme=false;
            }
        }
       return conforme ;
    }
    
    public Boolean sirenOK(Preconvention p){
        Boolean verifSiren = !verificationsEntreprise.getSIREN(p.getEntreprise().getSiren()).equals("");
        if(!verifSiren){
            traitementMetier.cause += " Siren introuvable";
            p.getRepJur().setCauseRep(traitementMetier.cause );
        }
        return verifSiren;
    }
    
}

