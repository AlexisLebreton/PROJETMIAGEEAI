/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;


import com.mycompany.scol.entities.EtudiantsSingleton;
import com.mycompany.scol.entities.PreconventionSingleton;
import com.mycompany.univshared.utilities.Diplome;
import com.mycompany.univshared.utilities.Preconvention;



/**
 *
 * @author maha-
 */
public class VérificationScolarite {
    public EtudiantsSingleton etdSingl ;
    public PreconventionSingleton ps;
    
    public Preconvention vérifierEtud(Preconvention p){
        Diplome diplomePrecon= p.getDiplome();
        Diplome diplomeReel = p.getEtudiant().getDipActuel();
        String cause = "";
        Boolean verif = etdSingl.exists(p.getEtudiant().getNumeroEtudiant())&& diplomePrecon.getIntitule().equals(diplomeReel.getIntitule());
        if(!verif ){
        cause="L etudiant mentionne n existe pas, ou son diplome préparé n est pas valide";
    }        
        return ps.validerScolarite(p, verif, cause);
    }
}
