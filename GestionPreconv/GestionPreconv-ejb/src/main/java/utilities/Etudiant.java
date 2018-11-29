/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.io.Serializable;
import java.util.HashMap;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 *
 * @author ben
 */

public class Etudiant implements Serializable{
      
    private String nom;
    private String prenom;
    private String numeroEtudiant;
    private String diplomeActuel;

    public Etudiant(String nom, String prenom, String numeroEtudiant) {
        this.nom = nom;
        this.prenom = prenom;
        this.numeroEtudiant = numeroEtudiant;
    }

    public String getDipActuel() {
        return diplomeActuel;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNumeroEtudiant(String numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }
    
}
