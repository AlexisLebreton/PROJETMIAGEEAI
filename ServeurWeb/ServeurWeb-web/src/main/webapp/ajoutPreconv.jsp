<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <title>Ajouter preconvention</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    
    <body>
        
        <%
        
            String prenom="",nom="",num="";
            if(request.getParameter("prenom")!=null){prenom = request.getParameter("prenom");}
            if(request.getParameter("nom")!=null){nom = request.getParameter("nom");}
            if(request.getParameter("num")!=null){num = request.getParameter("num");}

            String niveau="",diplome="";
            if(request.getParameter("niveau")!=null){niveau = request.getParameter("niveau");}
            if(request.getParameter("diplome")!=null){diplome = request.getParameter("diplome");}

            String assurance="",contrat="";
            if(request.getParameter("assurance")!=null){assurance = request.getParameter("assurance");}
            if(request.getParameter("contrat")!=null){contrat = request.getParameter("contrat");}

            String entreprise="",siren="";
            if(request.getParameter("entreprise")!=null){entreprise = request.getParameter("entreprise");}
            if(request.getParameter("siren")!=null){siren = request.getParameter("siren");}

            String debut="",fin="",gratification="",resume="";
            if(request.getParameter("debut")!=null){debut = request.getParameter("debut");}
            if(request.getParameter("fin")!=null){prenom = request.getParameter("fin");}
            if(request.getParameter("gratification")!=null){prenom = request.getParameter("gratification");}
            if(request.getParameter("resume")!=null){prenom = request.getParameter("resume");}
            
            

        %>
        
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand">Gestion preconv app</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/preconventions-web/saisirPreconv.jsp">Ajouter preconvention</a></li>
                    <li><a href="/preconventions-web/selectPreconv.jsp">Voir etat preconv</a></li>
                </ul>
            </div>
        </nav>
          
        <div class="container">
            
            <h2>Ajout de la preconvention</h2>
            
            <h3>Etudiant</h3>
            <div class="form-group">
                <label for="nom">Nom : <%=nom%></label>
            </div>
            <div class="form-group">
                <label for="prenom">Prénom : <%=prenom%></label>
            </div>
            <div class="form-group">
                <label for="num">Numéro étudiant : <%=num%></label>
            </div>

            <h3>Diplôme préparé</h3>
            <div class="form-group">
                <label for="niveau">Selectionner un niveau : <%=niveau%></label>
            </div>
            <div class="form-group">
                <label for="diplome">Diplome preparer : <%=diplome%></label>
            </div>

            <h3>Responsabilité civile</h3>
            <div class="form-group">
                <label for="assurance">Compagnie assurance : <%=assurance%></label>
            </div>
            <div class="form-group">
                <label for="contrat">Numéro de contrat : <%=contrat%></label>
            </div>

            <h3>Entreprise/Institution</h3>
            <div class="form-group">
                <label for="entreprise">Nom : <%=entreprise%></label>
            </div>
            <div class="form-group">
                <label for="contrat">Numéro de siren : <%=contrat%></label>
            </div>

            <h3>Stage</h3>
            <div class="form-group">
                <label for="debut">Date de début : <%=debut%></label>
            </div>
            <div class="form-group">
                <label for="fin">Date de fin : <%=fin%></label>
            </div>
            <div class="form-group">
                <label for="gratification">Gratification : <%=gratification%></label>
            </div>
            <div class="form-group">
                <label for="resume">Résumé sujet et activités: <%=resume%></label>
            </div>
                
        </div>
    </body>
</html>
