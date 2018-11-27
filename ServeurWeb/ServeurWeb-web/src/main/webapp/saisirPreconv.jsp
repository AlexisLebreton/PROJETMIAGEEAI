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
            
            <h2>Saisir preconvention</h2>
            
            <form id="formulaire" action="AjouterPreconvServlet" method="post">

                <h3>Etudiant</h3>
                <div class="form-group">
                    <label for="nom">Nom :</label>
                    <input type="text" class="form-control" id="nom" placeholder="Entrer un nom" name="nom">
                </div>
                <div class="form-group">
                    <label for="prenom">Prénom :</label>
                    <input type="text" class="form-control" id="prenom" placeholder="Entrer un prénom" name="prenom">
                </div>
                <div class="form-group">
                    <label for="num">Numéro étudiant :</label>
                    <input type="text" class="form-control" id="num" placeholder="Entrer un numéro étudiant" name="num">
                </div>

                <h3>Diplôme préparé</h3>
                <div class="form-group">
                    <label for="niveau">Selectionner un niveau :</label>
                    <select class="form-control" id="niveau" name="niveau">
                        <option value="L1">L1</option>
                        <option value="L2">L2</option>
                        <option value="L3">L3</option>
                        <option value="M1">M1</option>
                        <option value="M2">M2</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="diplome">Diplome preparer :</label>
                    <input type="text" class="form-control" id="diplome" placeholder="Entrer un diplome" name="diplome">
                </div>

                <h3>Responsabilité civile</h3>
                <div class="form-group">
                    <label for="assurance">Compagnie assurance :</label>
                    <input type="text" class="form-control" id="assurance" placeholder="Entrer un assureur" name="assurance">
                </div>
                <div class="form-group">
                    <label for="contrat">Numéro de contrat :</label>
                    <input type="text" class="form-control" id="contrat" placeholder="Entrer un numéro de contrat" name="contrat">
                </div>

                <h3>Entreprise/Institution</h3>
                <div class="form-group">
                    <label for="entreprise">Nom :</label>
                    <input type="text" class="form-control" id="entreprise" placeholder="Entrer un assureur" name="entreprise">
                </div>
                <div class="form-group">
                    <label for="contrat">Numéro de siren :</label>
                    <input type="text" class="form-control" id="siren" placeholder="Entrer un numéro de siren" name="siren">
                </div>

                <h3>Stage</h3>
                <div class="form-group">
                    <label for="debut">Date de début :</label>
                    <input type="date" class="form-control" id="debut" name="debut">
                </div>
                <div class="form-group">
                    <label for="fin">Date de fin :</label>
                    <input type="date" class="form-control" id="fin" placeholder="Entrer un numéro de siren" name="fin">
                </div>
                <div class="form-group">
                    <label for="gratification">Gratification :</label>
                    <input type="number" class="form-control" id="gratification" placeholder="Entrer un montant de gratification mensuel (en euro)" name="gratification">
                </div>
                <div class="form-group">
                    <label for="resume">Résumé sujet et activités:</label>
                    <textarea class="form-control" rows="5" id="resume"></textarea>
                </div>

                <button type="submit" class="btn btn-default" id="creer" name="creer">Creer</button>
                
            </form>
            
        </div>

    </body>
</html>
