<?php

require_once("model/UserDAO.php");
require_once("controller/Controller.php");

use controllers\Controller;

class ConnectUserController extends Controller {
    
    public function get($request): void 
    {
        session_start();

        if (isset($_SESSION['user_id'])) {
            $this->render("profil", []);
        } else {
            $this->render("connect", []);
        }
    }

    public function post($request): void
    {
        // Démarre la session avant toute manipulation des variables de session
        session_start();

        $email = $_POST['mail'] ?? 'Email non fourni';
        $password = $_POST['password'] ?? 'Mot de passe non fourni';

        $mailExist = UserDAO::getInstance()->emailExist($email);
        
        if ($mailExist) {
            $user = UserDAO::getInstance()->getUserByEmail($email);
            $passwordFromDatabase = $user->getPassword();

            if ($password === $passwordFromDatabase) {
                // Stocker l'ID de l'utilisateur dans la session
                $_SESSION['user_id'] = $user->getId();
                $_SESSION['firstname'] = $user->getFirstname();
                
                // Message de confirmation
                echo '<div style="color: green; font-weight: bold;">Vous êtes connecté avec succès !</div>';
                
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
                                window.location.href = "/upload";
                            }
                        }, 1000);  // Met à jour chaque seconde
                      </script>';
                exit();
            } else {
                echo('<div style="color: red;">Mot de passe incorrect</div>');
            }
        } else {
            echo('<div style="color: red;">Aucun compte appartenant à cette adresse mail</div>');
        }
    }
}
