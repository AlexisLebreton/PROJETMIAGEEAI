/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

/**
 *
 * @author Alexis LEBRETON
 */
public class ResponsabiliteCivile {
    
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
