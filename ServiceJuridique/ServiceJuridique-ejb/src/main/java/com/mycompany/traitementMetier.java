/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;


import com.mycompany.juridique.entities.PreconventionSingleton;
import com.mycompany.univshared.utilities.Preconvention;

/**
 *
 * @author maha-
 */
public class traitementMetier {
    
    private static String cause = "";
    
    public PreconventionSingleton ps;
    /*
    vérifie si la durée du stage ne dépasse pas 6 mois et que le stage ne se déroule pas sur 2 annes uiv.
    */
        public static Boolean periodeStageOK(Preconvention p){
        long duree = p.getDuréeStage();
        int an1 = p.getDebut().getYear();        
        int an2 = p.getFin().getYear();
        if(! (duree < 7  && an1==an2)){
            traitementMetier.cause = "Periode de stage non conforme";
        }
        return duree < 7  && an1==an2;
    }
    
        /*
        on suppose  la seule règle à vérifier et que l'entreprise doit payer l'etudiant si la durée de stage dépasse deux mois
        */
    public static Boolean gratificationOK(Preconvention p){
        if(!(p.getDuréeStage() > 2 && p.getGratification()>0)){
            traitementMetier.cause += " gratification non conforme";
        }
       return p.getDuréeStage() > 2 && p.getGratification()>0 ;
    }
    
    public Preconvention validationJuridique(Preconvention p){
      Boolean validEntreprise = verificationsEntreprise.getSIREN(p.getEntreprise().toString()).equals("");
      Boolean finV = periodeStageOK(p) && gratificationOK(p)&& validEntreprise;
      return ps.validerJuridique(p.getRefConv(), finV, traitementMetier.cause);      
    }
}

