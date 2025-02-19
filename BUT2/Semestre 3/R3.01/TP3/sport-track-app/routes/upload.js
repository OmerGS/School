const express = require('express');
const fileUpload = require('express-fileupload');
const moment = require('moment');
const router = express.Router();

// Middleware for handling file uploads
router.use(fileUpload());

/**
 * Charge dynamiquement le module de base de données.
 * @async
 * @function loadDb
 * @returns {Promise<Object>} Une promesse qui se résout avec le module de base de données chargé.
 */
async function loadDb() {
  const dbModule = await import('sport-track-db');
  return dbModule;
}

let db;
let Activity;
let ActivityEntry;

/**
 * Charger la base de données au démarrage et assigner les modèles Activity et ActivityEntry.
 */
loadDb()
  .then(dbModule => {
    db = dbModule;
    Activity = db.Activity;
    ActivityEntry = db.ActivityEntry;
    console.log('Database loaded successfully');
  })
  .catch(err => {
    console.error('Error loading the database:', err);
  });

/**
 * GET route pour afficher la page de téléchargement de fichier.
 * @name GET /upload
 * @function
 * @memberof module:router
 * @param {Object} req - Objet requête d'Express.
 * @param {Object} res - Objet réponse d'Express.
 * @returns {void} Rend la vue 'upload' avec les informations de l'utilisateur si connecté, sinon redirige vers la page de connexion.
 */
router.get('/', (req, res) => {
  if (!req.session.userId) {
    return res.redirect('/connect');
  }

  res.render('upload', {
    userLastname: req.session.userLastname,
    userFirstname: req.session.userFirstname,
  });
});

/**
 * POST route pour gérer le téléchargement de fichiers JSON et créer une nouvelle activité.
 * @name POST /upload
 * @function
 * @memberof module:router
 * @param {Object} req - Objet requête d'Express.
 * @param {Object} req.files - Fichier téléchargé par l'utilisateur.
 * @param {Object} res - Objet réponse d'Express.
 * @returns {void} Crée une nouvelle activité et les entrées associées dans la base de données ou renvoie une erreur si la création échoue.
 */
router.post('/', async (req, res) => {
  if (!req.files || Object.keys(req.files).length === 0) {
    return res.status(400).send('No file was uploaded.');
  }

  const uploadedFile = req.files.fichier;

  let jsonData;
  try {
    jsonData = JSON.parse(uploadedFile.data.toString());
  } catch (err) {
    console.error('Error reading JSON file:', err);
    return res.status(400).send('Uploaded file is not valid JSON.');
  }

  const activityDate = jsonData.activity.date;
  const activityDescription = jsonData.activity.description;

  const heartRates = jsonData.data.map(entry => entry.cardio_frequency);
  const heartRateMin = Math.min(...heartRates);
  const heartRateMax = Math.max(...heartRates);
  const heartRateAvg = Math.round(heartRates.reduce((a, b) => a + b, 0) / heartRates.length);

  if (heartRateAvg < heartRateMin || heartRateAvg > heartRateMax) {
    return res.status(400).send('Average heart rate must be between minimum and maximum heart rates.');
  }

  const times = jsonData.data.map(entry => entry.time);
  const start = new Date(`1970-01-01T${times[0]}Z`);
  const end = new Date(`1970-01-01T${times[times.length - 1]}Z`);
  const durationInMs = end - start;
  const durationSeconds = Math.floor(durationInMs / 1000);

  if (durationSeconds <= 0) {
    return res.status(400).send('Duration must be greater than zero.');
  }

  const durationHours = Math.floor(durationSeconds / 3600);
  const durationMinutes = Math.floor((durationSeconds % 3600) / 60);
  const durationSecondsFinal = durationSeconds % 60;
  const durationFormatted = 
    `${String(durationHours).padStart(2, '0')}:${String(durationMinutes).padStart(2, '0')}:${String(durationSecondsFinal).padStart(2, '0')}`;

  if (!req.session.userId) {
    return res.status(400).send("User not logged in.");
  }

  const distanceInMeters = Math.round(calculateTotalDistance(jsonData.data));

  const newActivity = {
    date: moment(activityDate, 'DD/MM/YYYY').format('YYYY-MM-DD'),
    description: activityDescription,
    heart_rate_min: Math.round(heartRateMin),
    heart_rate_max: Math.round(heartRateMax),
    heart_rate_avg: Math.round(heartRateAvg),
    distance: distanceInMeters,
    duration: durationFormatted,
    id_user: req.session.userId,
  };

  console.log('New activity:', newActivity);

  try {
    const activity = await Activity.create(newActivity);

    for (const entry of jsonData.data) {
      const entryData = {
        measure_time: entry.time,
        heart_rate: Math.round(entry.cardio_frequency),
        longitude: entry.longitude,
        latitude: entry.latitude,
        altitude: entry.altitude,
        id_activity: activity.id,
      };

      try {
        await ActivityEntry.create(entryData);
      } catch (entryError) {
        console.error('Error creating activity entry:', entryError);
      }
    }

    res.send('Activity and entries created successfully.');
  } catch (err) {
    console.error('Error creating activity:', err);
    return res.status(500).send('Error creating activity.');
  }
});

/**
 * Calcule la distance totale à partir des coordonnées de latitude et de longitude.
 * @function calculateTotalDistance
 * @param {Array<Object>} data - Tableau d'objets contenant les coordonnées de latitude et de longitude.
 * @returns {number} La distance totale en mètres.
 */
function calculateTotalDistance(data) {
  const haversineDistance = (coords1, coords2) => {
    const toRad = (value) => (value * Math.PI) / 180;
    const R = 6371;

    const lat1 = toRad(coords1.latitude);
    const lon1 = toRad(coords1.longitude);
    const lat2 = toRad(coords2.latitude);
    const lon2 = toRad(coords2.longitude);

    const dLat = lat2 - lat1;
    const dLon = lon2 - lon1;

    const a =
      Math.sin(dLat / 2) * Math.sin(dLat / 2) +
      Math.cos(lat1) * Math.cos(lat2) *
      Math.sin(dLon / 2) * Math.sin(dLon / 2);
    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    return R * c * 1000;
  };

  let totalDistance = 0;

  for (let i = 0; i < data.length - 1; i++) {
    totalDistance += haversineDistance(data[i], data[i + 1]);
  }

  return totalDistance;
}

module.exports = router;