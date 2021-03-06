/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExposRest;

import com.google.gson.Gson;
import com.mycompany.serverw.entities.PreconventionSingleton;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Alexis LEBRETON
 */
@Path("preconvention/{refConv}")
@RequestScoped
public class PreconventionResource {
    
    PreconventionSingleton preconvSingleton = lookupPreconventionSingletonBean();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PreconventionResource
     */
    public PreconventionResource() {
    }

    /**
     * Retrieves representation of an instance of ExposRest.PreconventionResource
     * @return an instance of java.lang.String
     * recuperer l'état d'une préconventions enregistrés
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("refConv") int refConv) {
        Gson gson = new Gson();
        if (this.preconvSingleton.getPrevention(refConv) == null){
            return gson.toJson("Préconvention introuvable");
        }else{
            return gson.toJson(this.preconvSingleton.getPrevention(refConv).getEtatPreconv());
        }
        
    }

    /**
     * PUT method for updating or creating an instance of PreconventionResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
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
