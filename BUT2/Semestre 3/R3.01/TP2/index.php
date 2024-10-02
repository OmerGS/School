<?php

require_once("controller/Controller.php");
require_once("controller/FirstPageController.php");
require_once('controller/AProposController.php');
require_once('controller/AddUserController.php');
require_once('controller/ConnectUserController.php');
require_once('controller/ProfilController.php');
require_once('controller/UserController.php');
require_once('controller/UploadActivityController.php');
require_once('controller/ListActivityController.php');

$request = $_SERVER['REQUEST_URI'];
$viewDir = '/';

switch ($request) {
    case '/apropos':
        $controller = new AProposController();
        $controller->get($request);
        break;

    case '/user_add':
        $controller = new AddUserController();
        if ($_SERVER['REQUEST_METHOD'] === 'POST') {
            $controller->post($request);
        } else {
            $controller->get($request);
        }
        break;

    case '/connect':
        $controller = new ConnectUserController();
        if ($_SERVER['REQUEST_METHOD'] === 'POST') {
            $controller->post($request);
        } else {
            $controller->get($request);
        }
        break;

    case '/':  // Gestion de la route racine pour la page d'accueil
        $controller = new FirstPageController();
        $controller->get($request);
        break;

    case '/disconnect':
        $controller = new UserController();
        $controller->disconnect();
        break;
    
    case '/upload':
        $controller = new UploadActivityController();
        if ($_SERVER['REQUEST_METHOD'] === 'POST') {
            $controller->post($request);
        } else {
            $controller->get($request);
        }
        break;

    case '/activities':
        $controller = new ListActivityController();
        $controller->get($request);
        break;

    default:
        // Page d'erreur 404 si la route n'existe pas
        http_response_code(404);
        require __DIR__ . $viewDir . 'view/error.php';
        break;
}
