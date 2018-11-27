/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;
import com.google.gson.Gson;
import fr.miage.toulouse.m2.eai.clientrest.metiersiren.Records;
import fr.miage.toulouse.m2.eai.clientrest.metiersiren.SirenPOJO;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 *
 * @author maha-
 */
public class verificationsEntreprise {
    
  public static String getSIREN(String siren){
        // I/O JSON
        Gson gson = new Gson();

        // TOKEN BEARER a récuperer sur INSEE
        String token = "Bearer 87faafeb-b34f-39d4-8cc0-cb9e7a15a8d9";
        // URI Service INSEE
        String uri = "http://data.opendatasoft.com/api/records/1.0/search/?dataset=sirene%40public";

        // a ajuster selon requete voir mode emploi INSEE
        String query = "&lang=fr";

        Client client = ClientBuilder.newClient();
        WebTarget wt = client.target(uri + "&q=" + siren + query);

        //WebResource webResource = client.resource(uri + siren + query);
        System.out.println("uri appel: " + uri + "&q=" + siren + query);

        Invocation.Builder invocationBuilder = wt.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        String reponse = response.readEntity(String.class);

        // Convertisseur JSON
        SirenPOJO model = gson.fromJson(reponse, SirenPOJO.class);

        System.out.println("Résultat: " + response.getStatus());
        Records [] rec = model.getRecords();
        if (rec.length == 0)
            System.out.println("Rien n'a été trouvé. Mauvais SIREN ?");
        else {
            for(int i = 0; i < rec.length ; i++)
                System.out.println("Raison sociale : " + model.getRecords()[i].getFields().getL1_normalisee() + ", Date création entité : " + model.getRecords()[i].getFields().getDcren() + ", Activité : " + model.getRecords()[i].getFields().getActivite());
        }
      return "test";
    }
  

  
}
