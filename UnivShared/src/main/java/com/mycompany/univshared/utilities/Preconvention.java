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

    private int refConv;
    private Etudiant etudiant;
    private Diplome diplome;
    private ResponsabiliteCivile responsabiliteCivile;
    private Entreprise entreprise;
    private long gratification;
    private Date debut;
    private Date fin;
    private String resume;
    private ReponseTraitPrec RepJur;
    private ReponseTraitPrec RepEn;
    private ReponseTraitPrec RepSco;

    public Preconvention(int refConv, String nom, String prenom, String numeroEtudiant, String niveau, String intitule, String compagnie, String numeroContrat, String denomination, String siren, Date debut, Date fin, long gratification, String resume) {
        this.refConv = refConv;
        this.etudiant = new Etudiant(nom,prenom,numeroEtudiant);
        this.diplome = new Diplome(niveau,intitule);
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
