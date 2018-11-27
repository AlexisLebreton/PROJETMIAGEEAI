/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.HashMap;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import utilities.Etudiant;

/**
 *
 * @author maha-
 */

@Singleton
@LocalBean
public class EtudiantsSingleton {
    
    private HashMap<String, Etudiant> etds = new HashMap<>();;
    
         public Etudiant ajouterEtudiant(String nom,String prenom,String n) {
        Etudiant et= new Etudiant(nom,prenom,n);
        etds.put(n,et);
        return et;
    }
    
        /*
            permet de vérifier si l'étudiant existe, et donc un num etudiant valide
        */
    public Boolean exists(String numE){
        return etds.containsKey(numE);
    }    
    
    public Etudiant getEtudiant(String numE){
        return etds.get(numE);
    }
    
}
