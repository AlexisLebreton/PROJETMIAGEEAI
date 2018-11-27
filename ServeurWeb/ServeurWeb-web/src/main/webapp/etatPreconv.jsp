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
        
            String numPreconv="";
            if(request.getParameter("numPreconv")!=null){numPreconv = request.getParameter("numPreconv");}
            
            String etat="en traitement";


        %>
        
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand">Gestion preconv app</a>
                </div>
                <ul class="nav navbar-nav">
                    <li><a href="/preconventions-web/saisirPreconv.jsp">Ajouter preconvention</a></li>
                    <li class="active"><a href="/preconventions-web/selectPreconv.jsp">Voir etat preconv</a></li>
                </ul>
            </div>
        </nav>
          
        <div class="container">
            
            <h2>Etat de la preconvention</h2>
            
            <h3>Preconvention n°: <%=numPreconv%></h3>
            <div class="form-group">
                <label for="etat">Etat : <%=etat%></label>
            </div>
            
                
        </div>
    </body>
</html>
