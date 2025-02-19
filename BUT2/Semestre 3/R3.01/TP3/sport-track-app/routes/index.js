var express = require('express');
var router = express.Router();

/**
 * GET route pour afficher la page d'accueil.
 * @name GET /
 * @function
 * @memberof module:router
 * @param {Object} req - Objet requête d'Express.
 * @param {Object} res - Objet réponse d'Express.
 * @param {Function} next - Fonction middleware pour passer au middleware suivant.
 * @returns {void} Rend la vue 'index' avec un titre.
 */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

module.exports = router;