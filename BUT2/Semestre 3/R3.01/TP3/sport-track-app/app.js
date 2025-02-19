var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');

var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');
var connect = require('./routes/connect');
var activities = require('./routes/upload');
var disconnect = require('./routes/disconnect');
var upload = require('./routes/upload');
var activities = require('./routes/activities');

var app = express();

const session = require('express-session');

/**
 * Configure et initialise la session utilisateur.
 * @function session
 * @memberof module:app
 * @param {Object} session - Configuration de la session.
 * @param {string} session.secret - La clé secrète pour signer la session.
 * @param {boolean} session.resave - Ne sauvegarde pas automatiquement la session si elle n'a pas été modifiée.
 * @param {boolean} session.saveUninitialized - Sauvegarde la session même si elle n'a pas été initialisée.
 * @param {Object} session.cookie - Configuration des cookies de la session.
 * @param {boolean} session.cookie.secure - Définit si le cookie doit être transmis uniquement via HTTPS.
 */
app.use(session({
  secret: 'clesupersecrete', 
  resave: false,
  saveUninitialized: true,
  cookie: { secure: false }
}));

/**
 * Configure le moteur de vue de l'application.
 * @function app.set
 * @memberof module:app
 * @param {string} key - Clé pour l'option de vue.
 * @param {string} value - Chemin vers les vues de l'application.
 */
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'pug');

/**
 * Middleware de l'application.
 * Utilise morgan pour logger les requêtes HTTP, et express pour traiter les requêtes JSON et URL-encoded.
 */
app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(cookieParser());

/**
 * Sert les fichiers statiques.
 * Utilise express pour servir les fichiers du répertoire 'public' et un répertoire statique supplémentaire 'static'.
 */
app.use(express.static(path.join(__dirname, 'public')));
app.use('/static', express.static('static'));

/**
 * Monte les routeurs sur les chemins spécifiés.
 * @function app.use
 * @memberof module:app
 */
app.use('/', indexRouter);      // Route pour la page d'accueil
app.use('/users', usersRouter); // Route pour les utilisateurs
app.use('/connect', connect);   // Route pour la connexion
app.use('/activities', activities); // Route pour les activités
app.use('/disconnect', disconnect); // Route pour la déconnexion
app.use('/upload', upload);     // Route pour le téléchargement de fichiers

/**
 * Capture les erreurs 404 et les transfère à l'handler d'erreur.
 * @function
 * @param {Object} req - Objet requête d'Express.
 * @param {Object} res - Objet réponse d'Express.
 * @param {Function} next - Fonction pour passer au middleware suivant.
 */
app.use(function(req, res, next) {
  next(createError(404));
});

/**
 * Handler d'erreurs pour l'application.
 * @function
 * @param {Object} err - L'erreur rencontrée.
 * @param {Object} req - Objet requête d'Express.
 * @param {Object} res - Objet réponse d'Express.
 * @param {Function} next - Fonction pour passer au middleware suivant.
 */
app.use(function(err, req, res, next) {
  // Définit les variables locales, en fournissant uniquement les erreurs en développement
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // Rend la page d'erreur
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;