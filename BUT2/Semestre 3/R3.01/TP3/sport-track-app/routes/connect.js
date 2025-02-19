let express = require('express');
let router = express.Router();

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
let User;

// Charger la base de données au démarrage
loadDb()
  .then(dbModule => {
    db = dbModule;
    User = db.User; // Assurez-vous que c'est le bon modèle utilisateur
    console.log('Base de données chargée');
  })
  .catch(err => {
    console.error('Erreur lors du chargement de la base de données :', err);
  });

/**
 * GET route pour afficher le formulaire de connexion ou la page de téléchargement.
 * @name GET /
 * @function
 * @memberof module:router
 * @param {Object} req - Objet requête d'Express.
 * @param {Object} req.session - Session utilisateur contenant l'ID utilisateur et d'autres informations.
 * @param {Object} res - Objet réponse d'Express.
 * @returns {void} Rend la vue du formulaire de connexion si l'utilisateur n'est pas connecté, sinon rend la vue de téléchargement.
 */
router.get('/', function(req, res) {
  if (!req.session.userId) {
    res.render('connect_form'); 
  } else {
    res.render('upload', {
      userLastname: req.session.userLastname,
      userFirstname: req.session.userFirstname
    });
  }
});

/**
 * POST route pour gérer la soumission du formulaire de connexion.
 * @name POST /
 * @function
 * @memberof module:router
 * @param {Object} req - Objet requête d'Express.
 * @param {string} req.body.email - L'adresse e-mail de l'utilisateur.
 * @param {string} req.body.password - Le mot de passe de l'utilisateur.
 * @param {Object} res - Objet réponse d'Express.
 * @returns {void} Redirige vers la page de profil si la connexion est réussie, sinon renvoie une erreur.
 */
router.post('/', async (req, res) => {
  const { email, password } = req.body;

  if (!email || !password) {
    return res.status(400).send("Email et mot de passe sont requis.");
  }

  try {
    const existingUser = await User.findOne({ where: { email } });

    if (!existingUser) {
      return res.status(400).send("Cet email n'est pas utilisé. Veuillez en choisir un autre.");
    }

    if (existingUser.password !== password) {
      return res.status(400).send("Mot de passe incorrect.");
    }

    req.session.userId = existingUser.id;
    req.session.firstname = existingUser.firstname;

    res.redirect('/upload');

  } catch (err) {
    console.error('Erreur lors de la connexion :', err);
    return res.status(500).render('connect_form', { error: 'Erreur lors de la connexion.' });
  }
});

module.exports = router;