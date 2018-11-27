/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alexis LEBRETON
 */
public class AjouterPreconvServlet extends HttpServlet {

    @EJB
    private entities.PreconventionSingleton preconventionSingleton;

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
        Date debut = new Date();
        Date fin = new Date();
        try (PrintWriter out = response.getWriter()) {
            try {
                /* TODO output your page here. You may use following sample code. */
                debut = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("debut"));
                fin = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("fin"));
                        } catch (ParseException ex) {
                Logger.getLogger(AjouterPreconvServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            int refConv = preconventionSingleton.ajouterPreConvention(request.getParameter("nom"),
                                                            request.getParameter("prenom"),
                                                            request.getParameter("etu"),
                                                            request.getParameter("niveau"),
                                                            request.getParameter("diplome"),
                                                            request.getParameter("assurance"),
                                                            request.getParameter("contrat"),
                                                            request.getParameter("entreprise"),
                                                            request.getParameter("siren"),
                                                            debut,
                                                            fin,
                                                            Integer.parseInt(request.getParameter("gratification")),
                                                            request.getParameter("resume"));
            
            out.println("<!DOCTYPE html>");
            out.println("<html> ");
            out.println("<head>");
            out.println("<title>Servlet AjouterPreconvServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AjouterPreconvServlet at " + refConv + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
