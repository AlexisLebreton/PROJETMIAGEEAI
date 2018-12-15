/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.univshared.utilities;

import java.io.Serializable;

/**
 *
 * @author Alexis LEBRETON
 */
public class Entreprise implements Serializable {
    
    private String denomination; // EDF
    private String siren; // 2132145

    public Entreprise(String denomination, String siren) {
        this.denomination = denomination;
        this.siren = siren;
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
    
    
}
