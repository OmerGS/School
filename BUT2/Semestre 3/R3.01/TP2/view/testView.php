<?php
session_start();

if (isset($_SESSION['user_id'])) {
    echo "Utilisateur connecté, ID : " . $_SESSION['user_id'];
} else {
    echo "Vous n'êtes pas connecté.";
}
