let express = require('express');
let router = express.Router();

/**
 * Charge dynamiquement le module de base de données.
 * @async
 * @function loadDb
 * @returns {Promise<Object>} Une promesse résolue avec le module de base de données chargé.
 */
async function loadDb() {
  const dbModule = await import('sport-track-db');
  return dbModule;
}

let db;
let User;

/**
 * Charger la base de données au démarrage et assigner le modèle User.
 */
loadDb().then(dbModule => {
  db = dbModule;
  User = db.User;
  console.log('Base de données chargée');
}).catch(err => {
  console.error('Erreur lors du chargement de la base de données :', err);
});

/**
 * Gère la requête GET pour afficher le formulaire de création d'utilisateur.
 * @name GET /user/create
 * @function
 * @memberof module:router
 * @param {Object} req - Objet requête d'Express.
 * @param {Object} res - Objet réponse d'Express.
 * @returns {void} Rend la vue 'user_form_create' si l'utilisateur n'est pas connecté, sinon affiche la page de téléchargement.
 */
router.get('/', function(req, res) {
  if (!req.session.userId) {
    res.render('user_form_create');
  } else {
    res.render('upload', {
      userLastname: req.session.userLastname,
      userFirstname: req.session.userFirstname
    });
  }
});

/**
 * Gère la requête POST pour traiter le formulaire de création d'utilisateur.
 * @name POST /user/create
 * @function
 * @memberof module:router
 * @param {Object} req - Objet requête d'Express.
 * @param {Object} req.body - Données envoyées via le formulaire de création d'utilisateur.
 * @param {Object} res - Objet réponse d'Express.
 * @returns {void} Crée un nouvel utilisateur et redirige vers la page de téléchargement, ou renvoie une erreur en cas d'échec.
 */
router.post('/', async (req, res) => {
  const { lastname, firstname, birthdate, gender, height, weight, email, password } = req.body;

  try {
    // Vérifier si l'utilisateur existe déjà dans la base de données
    const existingUser = await User.findOne({ where: { email } });
    
    if (existingUser) {
      return res.status(400).send('Cet email est déjà utilisé. Veuillez en choisir un autre.');
    }

    // Ajouter un nouvel utilisateur dans la base de données
    const newUser = {
      lastname,
      firstname,
      birthdate,
      gender,
      height,
      weight,
      email,
      password,
    };

    await User.create(newUser);

    // Créer une session pour l'utilisateur
    req.session.userId = newUser.id;
    req.session.firstname = newUser.firstname;

    res.redirect('/upload');
  } catch (err) {
    console.error('Erreur lors de la création de l\'utilisateur :', err);
    return res.status(500).send('Erreur lors de la création de l\'utilisateur.');
  }
});

module.exports = router;