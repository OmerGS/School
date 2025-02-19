const express = require('express');
const router = express.Router();

/**
 * GET route pour gérer la déconnexion de l'utilisateur.
 * @name GET /logout
 * @function
 * @memberof module:router
 * @param {Object} req - Objet requête d'Express.
 * @param {Object} req.session - Session utilisateur à détruire.
 * @param {Object} res - Objet réponse d'Express.
 * @returns {void} Redirige vers la page d'accueil après la déconnexion, ou renvoie une erreur en cas d'échec de la destruction de la session.
 */
router.get('/', (req, res) => {
  // Détruire la session
  req.session.destroy(err => {
    if (err) {
      console.error('Erreur lors de la destruction de la session :', err);
      return res.status(500).send('Erreur lors de la déconnexion.');
    }

    res.redirect('/');
  });
});

module.exports = router;
