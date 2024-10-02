<?php
namespace controllers;
/**
 * Classe abstraite qui est étendue par les contrôleurs pour traiter
 * les requêtes HTTP POST, GET, ...
 */

abstract class Controller
{
    /**
     * Méthode appelée pour traiter une requête HTTP de type GET.
     * @@param $request requête HTTP de type GET
     */
    public function get($request): void
    {
        $this->render("error", ["message" => "Method HTTP GET not allowed!"]);
    }

    /**
     * Méthode appelée pour traiter une requête HTTP de type POST.
     * @param $request
     */
    public function post($request): void
    {
        $this->render("error", ["message" => "Method HTTP POST not allowed!"]);
    }

    /**
     * Méthode appelée pour traiter une requête HTTP de type PUT.
     * @param $request
     */
    public function put($request): void
    {
        $this->render("error", ["message" => "Method HTTP PUT not allowed!"]);
    }

    /**
     * Méthode appelée pour traiter une requête HTTP de type PUT.
     * @param $request
     */
    public function delete($request): void
    {
        $this->render("error", [
            "message" => "Method HTTP DELETE not allowed!",
        ]);
    }

    /**
     * Méthode appelée pour afficher une vue
     * @param String $view Le nom du fichier contenant la vue qui doit
     * être retournée au client.
     * @param Array $data Un tableau associatif contenant les données
     * qui doivent être passées à la vue.
     * @param bool $print
     */
    public function render($view, $data, $print = true): string
    {
        $filePath = "view/" . $view . ".php";

        $output = null;
        if (file_exists($filePath)) {
            // Extract the variables to a local namespace
            extract($data);

            // Start output buffering
            ob_start();

            // Include the template file
            include $filePath;

            // End buffering and return its contents
            $output = ob_get_clean();
        }
        if ($print) {
            print $output;
        }
        return $output;
    }
}
?>
