<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <title>Voir etat preconvention</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    
    <body>
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
            
            <h2>Selectionner une preconvention</h2>
            
            <form id="formulaire" action="etatPreconv.jsp" method="post">

                <h3>Numéro de la préconvention</h3>
                <div class="form-group">
                    <label for="nom">Numéro fournis à la création de la preconvention :</label>
                    <input type="text" class="form-control" id="numPreconv" placeholder="Entrer un numéro de préconvention" name="numPreconv">
                </div>
                
                <button type="submit" class="btn btn-default" id="valider" name="valider">Valider</button>
                
            </form>
            
        </div>
    </body>
</html>
