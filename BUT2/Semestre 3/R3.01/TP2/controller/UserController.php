<?php

require_once("controller/Controller.php");

use controllers\Controller;

class UserController extends Controller {

    public function disconnect(): void 
    {
        // Démarre la session pour accéder aux données de session
        session_start();

        // Détruit toutes les variables de session
        $_SESSION = [];

        // Détruire la session si elle existe
        if (ini_get("session.use_cookies")) {
            $params = session_get_cookie_params();
            setcookie(session_name(), '', time() - 42000,
                $params["path"], $params["domain"],
                $params["secure"], $params["httponly"]
            );
        }

        // Détruire la session
        session_destroy();

        // Message de déconnexion
        echo '<div style="color: green; font-weight: bold;">Vous êtes déconnecté avec succès !</div>';
        
        // Compte à rebours et redirection
        echo '<div id="countdown" style="color: blue; font-weight: bold;">Redirection dans 3 secondes...</div>';
        
        echo '<script>
                let countdown = 3;
                const countdownElement = document.getElementById("countdown");
                
                const interval = setInterval(function() {
                    countdown--;
                    if (countdown > 0) {
                        countdownElement.innerHTML = "Redirection dans " + countdown + " secondes...";
                    } else {
                        clearInterval(interval);
                        window.location.href = "/";  // Redirection vers la page de connexion
                    }
                }, 1000);  // Met à jour chaque seconde
              </script>';
        
        exit();  // Assure que le reste du script n'est pas exécuté
    }
}
