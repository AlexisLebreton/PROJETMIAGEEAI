/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.univshared.utilities;

import java.io.Serializable;

/**
 *
 * @author ben
 */
public class ReponseTraitPrec implements Serializable{
    
    private Boolean valRep; // true false
    private String causeRep; // cause qui justifi le refus / nom du professeur en cas de validation de dep enseignement

    public ReponseTraitPrec(Boolean valRep, String causeRep) {
 
        this.valRep = valRep;
        this.causeRep = causeRep;
    }

    public ReponseTraitPrec() {
        this.causeRep="";
    }

 

    public void setValRep(Boolean valRep) {
        this.valRep = valRep;
    }

    public void setCauseRep(String causeRep) {
        this.causeRep = causeRep;
    }

    public Boolean getValRep() {
        return valRep;
    }

    public String getCauseRep() {
        return causeRep;
    }
    
    
    
}
