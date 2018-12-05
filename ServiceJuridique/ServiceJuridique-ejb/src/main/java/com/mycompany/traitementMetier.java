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
    
    private static String cause = "";
    
    @EJB
    PreconventionSingleton ps;

    public traitementMetier() {
    }
    
    
    /*
    vérifie si la durée du stage ne dépasse pas 6 mois et que le stage ne se déroule pas sur 2 annes uiv.
    */
        public  Boolean periodeStageOK(Preconvention p){
        long duree = p.getDureeStage();
        System.out.println(duree);
        int an1 = p.getDebut().getYear();        
        int an2 = p.getFin().getYear();
        if(! (duree < 7  && an1==an2)){
            traitementMetier.cause += " Periode de stage non conforme";
        }
        return duree < 7  && an1==an2;
    }
    
        /*
        on suppose  la seule règle à vérifier et que l'entreprise doit payer l'etudiant si la durée de stage dépasse deux mois
        */
    public  Boolean gratificationOK(Preconvention p){
        Boolean conforme = true;
        
        if(p.getDureeStage() > 2 ){
            if(p.getGratification()==0){
            traitementMetier.cause += " gratification non conforme";
            conforme=false;
            }
        }
       return conforme ;
    }
    
    public  Preconvention validationJuridique(Preconvention p){
        System.out.println("SIREN DEMANDE "+p.getEntreprise().getSiren());
        Boolean validEntreprise = !verificationsEntreprise.getSIREN(p.getEntreprise().getSiren()).equals("");
     // Boolean finV = periodeStageOK(p) && gratificationOK(p)&& validEntreprise;
      //a garder celui en commentaire
      //return ps.validerJuridique(p.getRefConv(), finV, traitementMetier.cause);
      return ps.validerJuridique(p, validEntreprise, "");      
    }
}

