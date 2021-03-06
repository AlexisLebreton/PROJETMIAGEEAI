/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.univshared.utilities;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.annotation.Resource;


/**
 *
 * @author ben
 */
public class Preconvention implements Serializable {

    private int refConv; // 1
    private Etudiant etudiant; // Maha chaouch 123
    private Diplome diplome; // M2 MIAGE
    private ResponsabiliteCivile responsabiliteCivile; // MATMUT 123455
    private Entreprise entreprise; // EDF 3431662346
    private long gratification; // 1233.4
    private Date debut; // 12/05/2018
    private Date fin; // 12/06/2018
    private String resume; // bla bla
    private ReponseTraitPrec RepJur; 
    private ReponseTraitPrec RepEn;
    private ReponseTraitPrec RepSco;

    public Preconvention(int refConv, String nom, String prenom, String numeroEtudiant, String niveau, String intitule, String compagnie, String numeroContrat, String denomination, String siren, Date debut, Date fin, long gratification, String resume) {
        this.refConv = refConv;
        this.diplome = new Diplome(niveau,intitule);
        this.etudiant = new Etudiant(nom,prenom,numeroEtudiant,this.diplome);
        this.responsabiliteCivile = new ResponsabiliteCivile(compagnie, numeroContrat);
        this.entreprise = new Entreprise(denomination,siren);
        this.debut = debut;
        this.fin = fin;
        this.gratification = gratification;
        this.resume = resume;
        this.RepJur = new ReponseTraitPrec();
        this.RepEn = new ReponseTraitPrec();
        this.RepSco = new ReponseTraitPrec();
    }
    
    // retourne l'état de la préconvention en fonction des validation qu'elle possède
    public String getEtatPreconv(){
        if (this.RepJur.getValRep() == null && this.RepEn.getValRep() == null && this.RepSco.getValRep() == null){
            return "En cours de traitement";
        }else{
            if (this.RepJur.getValRep() && this.RepEn.getValRep()&& this.RepSco.getValRep()){
                return "Preconvention validé";
            }else{
                return "Preconvention refusé. Cause : "+this.RepJur.getCauseRep()+" ; "+this.RepEn.getCauseRep()+" ; "+this.RepSco.getCauseRep();
            }
        }
    }

    public int getRefConv() {
        return refConv;
    }

    public void setRefConv(int refConv) {
        this.refConv = refConv;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Diplome getDiplome() {
        return diplome;
    }

    public void setDiplome(Diplome diplome) {
        this.diplome = diplome;
    }

    public ResponsabiliteCivile getResponsabiliteCivile() {
        return responsabiliteCivile;
    }

    public void setResponsabiliteCivile(ResponsabiliteCivile responsabiliteCivile) {
        this.responsabiliteCivile = responsabiliteCivile;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public long getGratification() {
        return gratification;
    }

    public void setGratification(int gratification) {
        this.gratification = gratification;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public ReponseTraitPrec getRepJur() {
        return RepJur;
    }

    public void setRepJur(ReponseTraitPrec RepJur) {
        this.RepJur = RepJur;
    }

    public ReponseTraitPrec getRepEn() {
        return RepEn;
    }

    public void setRepEn(ReponseTraitPrec RepEn) {
        this.RepEn = RepEn;
    }

    public ReponseTraitPrec getRepSco() {
        return RepSco;
    }

    public void setRepSco(ReponseTraitPrec RepSco) {
        this.RepSco = RepSco;
    }

    public long getDureeStage() {
        long difference = this.fin.getTime() - this.debut.getTime();
	long daysBetween = (difference / (1000*60*60*24));
        return daysBetween;
    }
    
    @Override
    public String toString(){
         return "Voici les détails de la pre convention \n \t Nom prenom étudiant:"+this.getEtudiant().getPrenom()+" "+this.getEtudiant().getNom()+
        "\n\t diplome préparé: "+this.getDiplome().getIntitule()+"\n\t sujet "+this.getResume();
        
    }
    
    public boolean isAllRep(){
        if (this.RepJur.getValRep() != null && this.RepEn.getValRep() != null && this.RepSco.getValRep() != null){
            return true;
        }else{
            return false;
        }
    }

}
