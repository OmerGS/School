<?php

require_once("model/UserDAO.php");
require_once("controller/Controller.php");

use controllers\Controller;

class FirstPageController extends Controller {
    public function get($request): void 
    {
        // Affiche le formulaire
        $this->render("index", []);
    }

}