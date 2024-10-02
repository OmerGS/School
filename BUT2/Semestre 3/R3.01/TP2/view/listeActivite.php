<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="view/listeActivity.css"> <!-- Link to external CSS -->
    <title>Activities - SportTrack</title>

</head>
<body>

    <!-- Header / Navbar -->
    <div class="navbar">
        <div class="logo"></div>
        <div class="titre">
            <h1>SportTrack</h1>
        </div>
        <div class="bouton">
            <button class="boutonFormulaire" type="button"><a href="/">Page d'Acceuil</a></button>
            <button class="boutonFormulaire" type="button"><a href="/upload">Profil</a></button>
            <button class="boutonFormulaireResponsive" type="button"><a href="index.html"><img src="/Image/7133312.png" height="30px" width="30px"></a></button>
        </div>
    </div>

    <?php  
        $totalDistance = 0;
    ?>

    <!-- LA LISTE DES ACTIVITES -->
    <table>
        <thead>
            <tr>
                <th>Date</th>
                <th>Description</th>
                <th>Distance</th>
                <th>BPM Moyen</th>
                <th>Durée</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <?php foreach ($activities as $activity): ?>
                <tr>
                    <td><?php echo htmlspecialchars($activity->getDate()); ?></td>
                    <td><?php echo htmlspecialchars($activity->getDescription()); ?></td>
                    <td><?php echo htmlspecialchars($activity->getDistance()); ?> m</td>
                    <?php $totalDistance += $activity->getDistance() ?>
                    <td><?php echo htmlspecialchars($activity->getHeartRateAvg()); ?> bpm</td>
                    <td><?php echo htmlspecialchars($activity->getDuration()); ?></td>
                    <td>
                        <button class="toggle-entries">Details</button>
                    </td>
                </tr>
                <tr class="activity-entries">
                    <td colspan="6">
                        <?php foreach ($activitiesEntries as $entry): ?>
                            <?php if ($entry->getIdActivity() === $activity->getId()): ?>
                                <div><?php echo "Heure : " . htmlspecialchars($entry->getMeasureTime()); ?></div>
                                <div><?php echo "Fréquence Cardiaque : " . htmlspecialchars($entry->getHeartRate()); ?></div>
                                <div><?php echo "Altitude : " . ($entry->getAltitude()); ?></div>
                                <div><?php echo "Latitude : " . ($entry->getLatitude()); ?></div>
                                <div><?php echo "Longitude : " . htmlspecialchars($entry->getLongitude()); ?></div>
                                <br>
                            <?php endif; ?>
                        <?php endforeach; ?>

                    </td>
                </tr>
            <?php endforeach; ?>
        </tbody>
    </table>

    <!-- Total Distance and Duration -->
    <div class="total-summary">
        <p>
            Nous avons parcouru <?php echo htmlspecialchars($totalDistance); ?> mètres avec vous !
        </p>
    </div>


    <script>
        document.querySelectorAll('.toggle-entries').forEach(button => {
            button.addEventListener('click', function() {
                const entriesRow = this.closest('tr').nextElementSibling;
                entriesRow.style.display = entriesRow.style.display === 'none' ? 'table-row' : 'none';
            });
        });
    </script>

    <!-- Footer -->
    <footer>
        <p><a class="tos" href="view/tos.html">Terms of Service</a></p>
    </footer>

    
</body>
</html>