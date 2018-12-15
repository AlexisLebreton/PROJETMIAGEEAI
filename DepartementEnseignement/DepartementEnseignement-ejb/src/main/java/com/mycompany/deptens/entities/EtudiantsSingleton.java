/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.deptens.entities;

import java.util.HashMap;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import com.mycompany.univshared.utilities.Etudiant;

/**
 *
 * @author maha-
 * Singleton stocke les Etudiants du departement enseignement
 */

@Singleton
@LocalBean
public class EtudiantsSingleton {
    
    private HashMap<String, Etudiant> etds = new HashMap<>();
    // exemple d'étudiant stocké
    public Etudiant e1 = new Etudiant("CHAOUCH","Maha","123");
    public  Etudiant e2 = new Etudiant("LEBRETON","Alexis","124");

    public EtudiantsSingleton() {
        etds.put("123", e1);
        etds.put("124", e2);
    }
    
    
    
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
