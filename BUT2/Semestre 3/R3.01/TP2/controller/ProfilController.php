<?php

require_once("model/UserDAO.php");
require_once("controller/Controller.php");

use controllers\Controller;

class ProfilController extends Controller {
    
    public function get($request): void 
    {
        // Démarre la session pour vérifier si l'utilisateur est connecté
        session_start();

        // Vérifie si l'utilisateur est connecté en regardant si l'ID est stocké en session
        if (isset($_SESSION['user_id'])) {
            // Récupère l'ID de l'utilisateur depuis la session
            $userId = $_SESSION['user_id'];
            
            // Récupère les informations de l'utilisateur depuis la base de données
            $user = UserDAO::getInstance()->getUserById($userId);
            
            if ($user) {
                // Rendre la vue du profil avec les données de l'utilisateur
                $this->render("profil", [
                    'user' => $user
                ]);
            } else {
                echo "Erreur : utilisateur non trouvé.";
            }
        } else {
            // Si l'utilisateur n'est pas connecté, redirige vers la page de connexion
            header('Location: /connect');
            exit();
        }
    }
}
