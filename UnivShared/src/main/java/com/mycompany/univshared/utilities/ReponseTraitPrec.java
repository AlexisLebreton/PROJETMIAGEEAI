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
    
    private Boolean valRep;
    private String causeRep;

    public ReponseTraitPrec(Boolean valRep, String causeRep) {
 
        this.valRep = valRep;
        this.causeRep = causeRep;
    }

    public ReponseTraitPrec() {
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
