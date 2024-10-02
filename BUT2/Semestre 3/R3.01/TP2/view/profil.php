<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin><link href="https://fonts.googleapis.com/css2?family=Nunito:wght@500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="view/Profil.css">
    <title>Profil - SporTrack</title>
</head>
<body>
    <div class="navbar">
        <div class="logo">
            
        </div>
        <div class="titre">
            <h1>SportTrack</h1>
        </div>
        <div class="bouton">
            <button class="boutonFormulaire" type="button"><a href="/">Page d'Acceuil</a></button>
            <button class="boutonFormulaire" type="button"><a href="/disconnect">Deconnexion</a></button>
            <button class="boutonFormulaireResponsive" type="button"><a href="index.html"><img src="/Image/7133312.png" height="30px" width="30px"></a></button>
        </div>
    </div>

    <div id="formulaireConnection">
        <form action="/upload" method="POST" enctype="multipart/form-data">
            <p style="font-size: 25px">Televerser des données</p>
            <label for="fichier">Sélectionnez un article</label>
            <input type="file" id="fichier" name="fichier" accept=".json">
            <input type="submit" value="Envoyer le fichier">
        </form>    
    </div>


    <div id="formulaireConnection">
        <form action="/activities" method="POST" enctype="multipart/form-data">
            <p style="font-size: 25px">Voir vos données</p>
            <input type="submit" value="VOIR">
        </form>    
    </div>

    <footer>
        <p><a class="tos" href="view/tos.html">Terms of Service</a></p>
    </footer>
</body>
</html>