/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

/**
 *
 * @author ben
 */
public class ReponseTraitPrec {
    
    private Boolean valRep;
    private String causeRep;

    public ReponseTraitPrec(Boolean valRep, String causeRep) {
 
        this.valRep = valRep;
        this.causeRep = causeRep;
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
