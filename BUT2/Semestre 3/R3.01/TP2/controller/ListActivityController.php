<?php

require_once("controller/Controller.php");
require_once('model/ActivityDAO.php');
require_once('model/ActivityEntryDAO.php');

use controllers\Controller;

class ListActivityController extends Controller {

    public function get($request): void 
    {
        session_start();

        if (isset($_SESSION['user_id'])) {
            $userId = $_SESSION['user_id'];
            $user = UserDAO::getInstance()->getUserById($userId);
            $activitiesEntries = ActivityEntryDAO::getInstance()->findAll();
            
            
            if ($user) {
                // Fetch activities only
                $activities = ActivityDAO::getInstance()->findById($user->getId());
                
                // Render the view with activities and user data
                $this->render("listeActivite", [
                    'user' => $user,
                    'activitiesEntries' => $activitiesEntries,
                    'activities' => $activities,
                    
                ]);
            } else {
                echo "Erreur : utilisateur non trouvé.";
            }
        } else {
            header('Location: /connect');
            exit();
        }
    }
}


?>
