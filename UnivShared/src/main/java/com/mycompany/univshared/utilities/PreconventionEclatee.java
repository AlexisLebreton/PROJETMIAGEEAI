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

/**
 *
 * @author Alexis LEBRETON
 * Cette classe a pour seul but d'accueilir les donnée recu en REST
 */
public class PreconventionEclatee implements Serializable {

    private int refConv;
    private String nom;
    private String prenom;
    private String numeroEtudiant;
    private String niveau;
    private String intitule;
    private String compagnie;
    private String numeroContrat;
    private String denomination;
    private String siren;
    private Date debut;
    private Date fin;
    private long gratification;
    private String resume;
    private ReponseTraitPrec RepJur;
    private ReponseTraitPrec RepEn;
    private ReponseTraitPrec RepSco;

    public PreconventionEclatee() {
    }

    public int getRefConv() {
        return refConv;
    }

    public void setRefConv(int refConv) {
        this.refConv = refConv;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public void setNumeroEtudiant(String numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getCompagnie() {
        return compagnie;
    }

    public void setCompagnie(String compagnie) {
        this.compagnie = compagnie;
    }

    public String getNumeroContrat() {
        return numeroContrat;
    }

    public void setNumeroContrat(String numeroContrat) {
        this.numeroContrat = numeroContrat;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getSiren() {
        return siren;
    }

    public void setSiren(String siren) {
        this.siren = siren;
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

    public long getGratification() {
        return gratification;
    }

    public void setGratification(long gratification) {
        this.gratification = gratification;
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

    

    public long getDuréeStage() {
        LocalDate d1 = LocalDate.parse((CharSequence) this.debut, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate d2 = LocalDate.parse((CharSequence) this.fin, DateTimeFormatter.ISO_LOCAL_DATE);
        Duration diff = Duration.between(d2.atStartOfDay(), d1.atStartOfDay());
        long diffDays = diff.toDays();
        return diffDays / 30;
    }
    
    public boolean isAllRep(){
        if (this.RepJur != null && this.RepEn != null && this.RepSco != null){
            return true;
        }else{
            return false;
        }
    }
}
