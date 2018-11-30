/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import com.mycompany.deptens.entities.PreconventionSingleton;
import java.util.Scanner;
import com.mycompany.univshared.utilities.Preconvention;

/**
 *
 * @author maha-
 */
public class verificationsEnseignement {
    public PreconventionSingleton ps;
       
    public Preconvention vérifier(Preconvention p){
       System.out.println("Voici les détails de la pre convention \n \t Nom prenom étudiant:"+p.getEtudiant().getPrenom()+" "+p.getEtudiant().getNom()+
        "\n\t diplome préparé: "+p.getDiplome()+"\n\t sujet "+p.getResume()
        ); 
        
        System.out.println("validez-vous ce sujet? O/N");
        Scanner sc  = new Scanner(System.in);
        String rep = sc.nextLine();
        if(rep=="O"){
            ps.validerEnseignement(p.getRefConv(), true, "");
        }else{
             ps.validerEnseignement(p.getRefConv(), false, "Sujet non adequat à la formation de l etudiant");
        }
        return p;
    }
}
