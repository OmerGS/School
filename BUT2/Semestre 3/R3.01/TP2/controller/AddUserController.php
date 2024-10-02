<?php

require_once("model/UserDAO.php");
require_once("controller/Controller.php");

use controllers\Controller;

class AddUserController extends Controller {
    public function get($request): void 
    {
        // Affiche le formulaire
        $this->render("user_add", []);
    }

    public function post($request): void
    {
        session_start();

        // Récupérer les données du formulaire
        $nom = $_POST['nom'] ?? null;
        $prenom = $_POST['prénom'] ?? null;
        $dateNaissance = $_POST['dateNaissance'] ?? null;
        $sexe = $_POST['sexe'] ?? null;
        $taille = $_POST['taille'] ?? null;
        $poids = $_POST['poids'] ?? null;
        $mail = $_POST['mail'] ?? null;
        $password = $_POST['password'] ?? null;

        // Vérifier que tous les champs sont remplis
        if ($nom && $prenom && $dateNaissance && $sexe && $taille && $poids && $mail && $password) {
            $newUser = new User();
            $newUser->init(1, $nom, $prenom, $dateNaissance, $sexe, $taille, $poids, $mail, $password);
            
            try {
                // Insérer l'utilisateur dans la base de données
                UserDAO::getInstance()->insert($newUser);
                
                // Stocker l'ID de l'utilisateur dans la session
                $_SESSION['user_id'] = $newUser->getId();
                $_SESSION['firstname'] = $newUser->getFirstname();

                // Message de confirmation
                echo '<div style="color: green; font-weight: bold;">Vous êtes inscrit avec succès !</div>';
                
                // Compte à rebours et redirection après succès
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
                        }, 1000);
                      </script>';

                // Fin de l'exécution pour éviter d'autres affichages
                exit();
                
            } catch(Exception $e) {
                // Afficher l'erreur si une exception est levée
                echo '<div style="color: red; font-weight: bold;">Erreur : ' . htmlspecialchars($e->getMessage()) . '</div>';
            }
            
        } else {
            // Si tous les champs ne sont pas remplis, afficher un message d'erreur
            echo '<div style="color: red; font-weight: bold;">Veuillez remplir tous les champs.</div>';
        }
    }
}
?>
