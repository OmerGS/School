import { Sequelize } from 'sequelize';
import UserModel from './UserModel.js'; // Assurez-vous que le chemin est correct
import pkg from '../appConstants.js';

const { db } = pkg;

// Créer une instance de Sequelize avec SQLite
const sequelize = new Sequelize("sqlite:" + db);

// Initialiser le modèle User avec l'instance de Sequelize
UserModel.init(sequelize);

async function runTests() {
  try {
    // Test 1: Créer un utilisateur
    console.log("Test 1: Création d'un utilisateur...");
    const newUser = await sequelize.models.User.create({
      lastname: 'Doe',
      firstname: 'John',
      birthdate: '1990-01-01',
      gender: 'MAN',
      height: 180,
      weight: 75,
      email: 'omer@example.com',
      password: 'password123',
    });

    console.log(`Utilisateur créé avec succès: ${newUser.firstname} ${newUser.lastname}`);




    // Test 2: Récupérer tous les utilisateurs
    console.log("Test 2: Affichage de tous les utilisateurs...");
    const users = await UserModel.getModel().findAll();

    if (users.length > 0) {
      console.log("Utilisateurs dans la base de données :");
      users.forEach(user => {
        console.log(`ID: ${user.id}, Nom: ${user.firstname} ${user.lastname}, Email: ${user.email}`);
      });
    } else {
      console.log("Aucun utilisateur trouvé.");
    }

  } catch (error) {
    console.error("Erreur pendant les tests :", error);
  } finally {
    // Fermer la connexion
    await sequelize.close();
  }
}

runTests();