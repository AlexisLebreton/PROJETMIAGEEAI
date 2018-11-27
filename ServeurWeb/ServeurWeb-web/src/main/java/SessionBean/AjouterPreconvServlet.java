/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;


import entities.PreconventionSingleton;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alexis LEBRETON
 */
public class AjouterPreconvServlet extends HttpServlet {

    private PreconventionSingleton preconventionSingleton = lookupPreconventionSingletonBean();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // recuperation des valeurs
        Date debut = new Date();
        Date fin = new Date();
        
        try {
            /* TODO output your page here. You may use following sample code. */
            debut = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("debut"));
            fin = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("fin"));
                    } catch (ParseException ex) {
            Logger.getLogger(AjouterPreconvServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String numeroEtudiant = request.getParameter("etu");
        String niveau = request.getParameter("niveau");
        String intitule = request.getParameter("diplome");
        String compagnie = request.getParameter("assurance");
        String numeroContrat = request.getParameter("contrat");
        String denomination = request.getParameter("entreprise");
        String siren = request.getParameter("siren");

        int gratification = Integer.parseInt(request.getParameter("gratification"));
        String resume = request.getParameter("resume");
            
        int refConv = preconventionSingleton.ajouterPreConvention(nom,prenom,numeroEtudiant,niveau,intitule,compagnie,numeroContrat,denomination,siren,debut,fin,gratification,resume);
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html> ");
            out.println("<head>");
            out.println("<title>Servlet AjouterPreconvServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>preconv ajouté ref :" + refConv + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private PreconventionSingleton lookupPreconventionSingletonBean() {
        try {
            javax.naming.Context c = new InitialContext();
            return (PreconventionSingleton) c.lookup("java:global/ServeurWeb-ear/ServeurWeb-ejb-1.0-SNAPSHOT/PreconventionSingleton!entities.PreconventionSingleton");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
