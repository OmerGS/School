<?php
use controllers\Controller;

require_once("Controller.php");

class AProposController extends Controller {
    public function get($request): void 
    {
        $this->render("apropos", []);
    }



}
?>
