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
public class ResponsabiliteCivile implements Serializable {
    
    private String compagnie;
    private String numeroContrat;

    public ResponsabiliteCivile(String compagnie, String numeroContrat) {
        this.compagnie = compagnie;
        this.numeroContrat = numeroContrat;
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
    
}
