import { Sequelize } from 'sequelize';

// Initialiser une instance de sequelize avec SQLite en mémoire
const sequelize = new Sequelize('sqlite::memory:', { logging: false });

export default sequelize;
