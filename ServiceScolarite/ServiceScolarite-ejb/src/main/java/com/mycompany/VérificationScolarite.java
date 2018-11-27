/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import entities.EtudiantsSingleton;
import entities.PreconventionSingleton;
import utilities.Diplome;
import utilities.Preconvention;



/**
 *
 * @author maha-
 */
public class VérificationScolarite {
    public EtudiantsSingleton etdSingl ;
    public PreconventionSingleton ps;
    
    public Preconvention vérifierEtud(Preconvention p){
        Diplome diplomePrecon= p.getDiplome();
        String diplomeReel = p.getEtudiant().getDipActuel();
        String cause = "";
        Boolean verif = etdSingl.exists(p.getEtudiant().getNumeroEtudiant())&& diplomePrecon.equals(diplomeReel);
        if(!verif ){
        cause="L etudiant mentionne n existe pas, ou son diplome préparé n est pas valide";
    }        
        return ps.validerScolarite(p.getRefConv(), verif, cause);
    }
}
