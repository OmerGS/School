<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="view/index.css">
    <link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin><link href="https://fonts.googleapis.com/css2?family=Nunito:wght@500&display=swap" rel="stylesheet">
    <title>SportTrack</title>
</head>
<body>
    <?php 
    session_start();
    ?>
    <div class="navbar">
        <div class="logo">
            
        </div>
        <div class="titre">
            <h1>SportTrack</h1>
        </div>
        <div class="bouton">
        <?php if (!isset($_SESSION['user_id'])): ?>
            <!-- Le bouton "S'inscrire" n'apparaît que si l'utilisateur n'est pas connecté -->
            <button class="boutonFormulaire" type="button"><a href="/user_add">S'inscrire</a></button>
            <button class="boutonFormulaire" type="submit"><a href="/connect">Se Connecter</a></button>
        <?php else: ?>
            <!-- Si l'utilisateur est connecté, on affiche son prénom -->
            <p style="color: white;">Bienvenue, <?php echo htmlspecialchars($_SESSION['firstname']); ?> !</p>
            <button class="boutonFormulaire" type="button"><a href="/upload">Profil</a></button>
        <?php endif; ?>
    </div>
    </div>

    <div id="contenu-principal1">
        <div class="text">
            <p class="textPresentation">🚀 Bienvenue sur SportTrack - Votre Portail de Performance Sportive 🏆<br><br>

                Prêt à repousser vos limites et à atteindre de nouveaux sommets dans votre parcours sportif? <br>SportTrack est là pour vous aider à suivre, améliorer et célébrer vos exploits sportifs comme jamais auparavant! <br><br>
                
                🏃‍♂️🚴‍♀️🏊‍♀️ Que vous soyez un athlète professionnel ou un amateur passionné, <br>SportTrack vous offre les outils et les ressources dont vous avez besoin pour briller sur la piste,<br> sur le terrain ou dans l'eau.
            </p>
        </div>
    </div>

    <div id="contenu-principal2">
        <div class="text">
            <p class="textPresentation">Sur notre site de sport tracker 🏃‍♂️🚴‍♀️, <br><br>
                vous pouvez bénéficier d'un suivi personnalisé de toutes vos activités sportives 📊,<br><br>
                 vous permettant ainsi de garder un œil sur votre progression 📈💪<br><br>
                 et d'obtenir des informations précieuses sur vos performances 🏆📆
            </p>
        </div>
    </div>
    
    <div id="contenu-principal3">
        <div class="text">
            <p class="textPresentation">Rejoignez notre communauté de passionnés du sport 🏃‍♂️🚴‍♀️ !<br><br>
                 Plus de <bold>100 000</bold> utilisateurs font déjà partie de notre famille de sportifs dévoués. <br><br>
                Relevez le défi et mesurez-vous aux meilleurs 💪🏆
            </p>
        </div>
    </div>

    <footer>
        <p><a class="tos" href="view/tos.html">Terms of Service</a></p>
    </footer>
</body>
</html>