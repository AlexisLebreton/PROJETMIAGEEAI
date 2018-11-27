
package ExposRest;

//import com.alexis.lebreton.utilities.entities.PreconventionSingleton;
//import com.alexis.lebreton.utilities.Preconvention;
//import com.google.gson.Gson;
import java.util.logging.Level;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author maha-
 */
@Path("preconventions/{refPreconv}")
public class PreconvRessource {
   PreconventionSingleton preconvSingleton = lookupPreconventionSingletonBean();
    
    
    @Context
    private UriInfo context;
    private Gson gson;

    public PreconvRessource(Gson gson) {
        this.gson = gson;
    }

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJsonPreconv(@PathParam("refPreconv") String refPreconv) {
        return this.gson.toJson(this.preconvSingleton.getPrevention(Integer.parseInt(refPreconv)));
    }
    
    @GET
    @Path("preconventions")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPreconv(){
        return this.gson.toJson(this.preconvSingleton.getAll());
    }
    
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response validerS(@PathParam("refPreconv") String refPreconv, @QueryParam("validS") String validS,@QueryParam("cause") String cause){
       int ref = Integer.parseInt(refPreconv); 
       boolean val= Boolean.parseBoolean(validS);
        Preconvention prec = this.preconvSingleton.validerScolarite(ref,val,cause);
        return Response.ok(this.gson.toJson(prec)).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response validerE(@PathParam("refPreconv") String refPreconv, @QueryParam("validE") String validE,@QueryParam("cause") String cause){
       int ref = Integer.parseInt(refPreconv); 
       boolean val= Boolean.parseBoolean(validE);
        Preconvention prec = this.preconvSingleton.validerEnseignement(ref,val,cause);
        return Response.ok(this.gson.toJson(prec)).build();
    }
 
     /**
     * Permet de créditer ou de débiter (en fonction du signe de la somme) un compte Pour l'appeler on doit utiliser une URL du type :
     * http://localhost:8080/BanqueREST/webresources/comptes/1?validJ=true
     *
     * @param refPreconv la pré-convention
     * @param validJ    le boolean de validation juridique (true = validée, false= refusée)
     *
     * @return la nouvelle preconvention en notation JSON après miseà jour de son boolean de validation juridique
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response validerj(@PathParam("refPreconv") String refPreconv, @QueryParam("validJ") String validJ,@QueryParam("cause") String cause){
       int ref = Integer.parseInt(refPreconv); 
       boolean val= Boolean.parseBoolean(validJ);
        Preconvention prec = this.preconvSingleton.validerJuridique(ref,val,cause);
        return Response.ok(this.gson.toJson(prec)).build();
    }
    
    @POST
    @Path("depotPreconv/{preconvention}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Preconvention ajoutPreconv(@PathParam("preconvention")  String prec) {
       Preconvention p;
       p = this.gson.fromJson(prec, Preconvention.class);
        return preconvSingleton.ajouterPreConvention( p.getEtudiant(),p.getDiplome(),p.getEntreprise(), p.getEntreprise(), p.getGratification(), p.getDebut(), p.getFin(), p.getSujetStage());
    }
    
    private PreconventionSingleton lookupPreconventionSingletonBean() {
        try {
            javax.naming.Context c = new InitialContext();
            return (PreconventionSingleton) c.lookup("java:global/preconventions/preconventions-ejb/PreconventionSingleton!com.alexis.lebreton.utilities.entities.PreconventionSingleton");
        } catch (NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
    
    
}
