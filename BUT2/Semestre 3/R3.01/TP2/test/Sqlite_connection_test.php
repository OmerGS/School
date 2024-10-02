<?php
try {
    require_once("../model/UserDAO.php");
    require_once("../model/ActivityDAO.php");
    require_once("../model/ActivityEntryDAO.php");

    // TEST USER DAO - Fetch all users and display their details
    echo "<h2>Test: Fetch All Users</h2>";
    $users = UserDAO::getInstance()->findAll();

    if (!empty($users)) {
        foreach ($users as $user) {
            echo $user->getLastname() . " / " . $user->getFirstname() . " / " . $user->getPassword() . "</br>";
        }
    } else {
        echo "No users found in the database.</br>";
    }


    // Example of deleting a user before insertion
    echo "<h2>Test: Delete User</h2>";
    $userToDelete = new User();
    $userToDelete->init(25, "Blahaj", "Rian", "2005-10-13", "MAN", 170, 70, "blohoj@teamcroissant.fr", "leplusdictateurdeskruacent");
    UserDAO::getInstance()->delete($userToDelete);
    echo "User deleted successfully with ID: 25</br>";



    // Example of inserting a new user after deletion
    echo "<h2>Test: Insert New User</h2>";
    $newUser = new User();
    $newUser->init(0, "Blahaj", "Rian", "2005-10-13", "MAN", 170, 70, "blohoj@teamcroissant.fr", "leplusdictateurdeskruacent");

    UserDAO::getInstance()->insert($newUser);
    echo "New user inserted successfully: " . $newUser->getLastname() . "</br>";



    // Example of updating a user
    echo "<h2>Test: Update User</h2>";
    // Create a new User object and initialize it with the updated details
    $userToUpdate = new User();
    $userToUpdate->init(1, "Blhoj", "Rian", "2005-10-13", "MAN", 170, 70, "blohoj@teamcroissant.fr", "leplusdictateurdeskruacent");
    // Call the update method
    UserDAO::getInstance()->update($userToUpdate);










    // TEST ActivityDAO - Fetch all activities and display their details
    echo "<h2>Test: Fetch All Activities</h2>";
    $activities = ActivityDAO::getInstance()->findAll();

    if (!empty($activities)) {
        foreach ($activities as $activity) {
            echo $activity->getId() . " / " . $activity->getDate() . " / " . $activity->getDescription() . "</br>";
        }
    } else {
        echo "No activities found in the database.</br>";
    }

    // Example of deleting an activity before insertion
    echo "<h2>Test: Delete Activity</h2>";
    $activityToDelete = new Activity();
    $activityToDelete->init(45, "2023-08-20", "Course à Pied", 68, 157, 106, 5000, "00:37:17", 1);
    ActivityDAO::getInstance()->delete($activityToDelete);
    echo "Activity deleted successfully: " . $activityToDelete->getDescription() . "</br>";  

    /*
    // Example of inserting a new activity after deletion
    echo "<h2>Test: Insert New Activity</h2>";
    $newActivity = new Activity();
    $newActivity->init(6, "2023-08-21", "Course", 98, 200, 153, 5126, "00:29:59", 1);

    echo "New activity inserted successfully: " . $newActivity->getDescription() . "</br>";

    ActivityDAO::getInstance()->insert($newActivity);
    */


    // Example of updating an activity
    echo "<h2>Test: Update Activity</h2>";
    $activityToUpdate = new Activity();
    $activityToUpdate->init(44, "2023-08-25", "Updated Course à Pied", 70, 160, 108, 5100, "00:38:20", 1);
    ActivityDAO::getInstance()->update($activityToUpdate);
    echo "Activity updated successfully: " . $activityToUpdate->getDescription() . "</br>";  












    // TEST ACTIVITYENTRYDAO

    /*
    // Example of inserting a new activity after deletion
    echo "<h2>Test: Insert New ActivityEntry</h2>";
    $newActivity = new ActivityEntry();
    $newActivity->init(1, "00:00:10", 100, -58.1, 58, 100.3, 1);

    echo "New activity inserted successfully: " . $newActivity->getMeasureTime() . "</br>";

    ActivityEntryDAO::getInstance()->insert($newActivity);
    */




    echo "<h2>Test: Fetch All Activity Entries</h2>";
    $entries = ActivityEntryDAO::getInstance()->findAll();
    $activityEntry = new ActivityEntry();

    if (!empty($entries)) {
        foreach ($entries as $entry) {
            $activityEntry = $entry;
            //A VOIR ACTIVITY ENTRY pour getHeartRate MARCHE PAS
            echo $activityEntry->getId() . "</br>";
        }
    } else {
        echo "No activity entries found in the database.</br>";
    }

} catch (PDOException $e) {
    echo "Erreur de connexion à la base de données : " . $e->getMessage();
}
?>
