const express = require('express');
const router = express.Router();

/**
 * Dynamically load the database module.
 * @async
 * @function loadDb
 * @returns {Promise<Object>} A promise that resolves to the loaded database module.
 */
async function loadDb() {
  const dbModule = await import('sport-track-db');
  return dbModule;
}

let db;

// Load the database at startup
loadDb()
  .then(dbModule => {
    db = dbModule;
    console.log('Database loaded successfully');
  })
  .catch(err => {
    console.error('Error loading the database:', err);
  });

/**
 * GET route to display user activities.
 * @name GET /
 * @function
 * @memberof module:router
 * @param {Object} req - Express request object.
 * @param {Object} req.session - Session object that contains user data.
 * @param {number} req.session.userId - The ID of the logged-in user.
 * @param {string} req.session.userLastname - The last name of the logged-in user.
 * @param {string} req.session.userFirstname - The first name of the logged-in user.
 * @param {Object} res - Express response object.
 * @returns {void} Renders the 'activities' view if successful, otherwise renders an error view.
 */
router.get('/', async (req, res) => {
  if (!req.session.userId) {
    return res.redirect('/connect');
  }

  try {
    // Fetch activities for the logged-in user
    const activities = await db.Activity.findAll({
      where: {
        id_user: req.session.userId,
      },
      order: [['date', 'ASC']], // Order by date ascending
    });

    // Convert the date string to a Date object
    activities.forEach(activity => {
      activity.date = new Date(activity.date); // Ensure it's a Date object
    });

    res.render('activities', {
      userLastname: req.session.userLastname,
      userFirstname: req.session.userFirstname,
      activities, // Pass activities to the view
    });
  } catch (err) {
    console.error('Error retrieving activities:', err);
    return res.render('error', { message: 'Could not retrieve activities. Please try again later.' });
  }
});

/**
 * POST route to handle form submission.
 * @name POST /
 * @function
 * @memberof module:router
 * @param {Object} req - Express request object.
 * @param {Object} res - Express response object.
 * @returns {void} Redirects the user to the activities page.
 */
router.post('/', (req, res) => {
  res.redirect('/activities');
});

module.exports = router;