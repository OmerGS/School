<?php
namespace controllers;

require CONTROLLERS_DIR . "/Controller.php";
use controllers\Controller;

class ErrorController extends Controller
{
    public function get($request): void
    {
        $this->render("error", []);
    }
}