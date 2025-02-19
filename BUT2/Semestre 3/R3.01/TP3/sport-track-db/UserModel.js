import { Sequelize, DataTypes } from 'sequelize';

import Activity from './ActivityModel.js'; 

const sequelize = new Sequelize({
  dialect: 'sqlite',
  storage: './sport_track.db',
});

sequelize.sync({ force: false })  // force: true recrée les tables, mais supprime aussi les données existantes.
  .then(() => {
    console.log('Tables synchronisées');
  })
  .catch((error) => {
    console.error('Erreur lors de la synchronisation des tables :', error);
  });

const User = sequelize.define('User', {
  id: {
    type: DataTypes.INTEGER,
    autoIncrement: true,
    primaryKey: true,
  },
  lastname: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  firstname: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  birthdate: {
    type: DataTypes.DATEONLY,
    allowNull: false,
    validate: {
      isDate: true,
      isWithinRange(value) {
        const minDate = new Date('1920-01-01');
        const maxDate = new Date();
        if (new Date(value) < minDate || new Date(value) > maxDate) {
          throw new Error("La date de naissance doit être entre le 01-01-1920 et aujourd'hui.");
        }
      },
    },
  },
  gender: {
    type: DataTypes.STRING,
    allowNull: false,
    validate: {
      isIn: [['MAN', 'WOMAN', 'TRANSGENDER']],
    },
  },
  height: {
    type: DataTypes.INTEGER,
    validate: {
      min: 50,
      max: 230,
    },
  },
  weight: {
    type: DataTypes.INTEGER,
    validate: {
      min: 10,
      max: 200,
    },
  },
  email: {
    type: DataTypes.STRING,
    allowNull: false,
    unique: true,
    validate: {
      isEmail: true,
    },
  },
  password: {
    type: DataTypes.STRING,
    allowNull: false,
    validate: {
      len: [8],
    },
  },
}, {
  tableName: 'User',
  timestamps: false,
});

User.hasMany(Activity, { foreignKey: 'id_user' });
Activity.belongsTo(User, { foreignKey: 'id_user' });


export default User;