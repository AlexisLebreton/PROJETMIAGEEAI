/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExposRest;

import com.google.gson.Gson;
import com.mycompany.serverw.entities.PreconventionSingleton;
import com.mycompany.univshared.utilities.PreconventionEclatee;
import java.util.logging.Level;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Alexis LEBRETON
 */
@Path("preconventions")
@RequestScoped
public class PreconventionsResource {

    PreconventionSingleton preconvSingleton = lookupPreconventionSingletonBean();
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PreconventionsResource
     */
    public PreconventionsResource() {
    }

    /**
     * Retrieves representation of an instance of ExposRest.PreconventionsResource
     * @return an instance of java.lang.String
     * recuperer la liste des préconventions
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        Gson gson = new Gson();
        return gson.toJson(this.preconvSingleton.getAll());
    }

    /**
     * PUT method for updating or creating an instance of PreconventionsResource
     * @param content representation for the resource
     * ajout d'une préconvention 
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String putJson(String content) {
        Gson g = new Gson(); 
        PreconventionEclatee p = g.fromJson(content, PreconventionEclatee.class);
        int numPreconv = preconvSingleton.ajouterPreConvention(p.getNom(), p.getPrenom(), p.getNumeroEtudiant(),p.getNiveau(),p.getIntitule(),p.getCompagnie(),p.getNumeroContrat(),p.getDenomination(), p.getSiren(),p.getDebut(), p.getFin(), (int) p.getGratification(), p.getResume());
        return numPreconv+"";
    }
    
    // acces au singleton de l'ejb
    private PreconventionSingleton lookupPreconventionSingletonBean() {
        try {
            javax.naming.Context c = new InitialContext();
            return (PreconventionSingleton) c.lookup("java:global/ServeurWeb-ear/ServeurWeb-ejb-1.0-SNAPSHOT/PreconventionSingleton!com.mycompany.serverw.entities.PreconventionSingleton");
        } catch (NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
