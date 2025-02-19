import { Sequelize, DataTypes } from 'sequelize';

const sequelize = new Sequelize({
  dialect: 'sqlite',
  storage: './sport_track.db',
});

const Activity = sequelize.define('Activity', {
  id: {
    type: DataTypes.INTEGER,
    autoIncrement: true,
    primaryKey: true,
  },
  date: {
    type: DataTypes.DATEONLY,
    allowNull: false,
    validate: {
      isDate: true,
      isAfter: "1920-01-01",
      isBefore: new Date().toISOString().split("T")[0],
    },
  },
  description: {
    type: DataTypes.TEXT,
    allowNull: false,
    validate: {
      len: [1, 250],
    },
  },
  heart_rate_min: {
    type: DataTypes.INTEGER,
    allowNull: false,
    validate: {
      min: 20,
    },
  },
  heart_rate_max: {
    type: DataTypes.INTEGER,
    allowNull: false,
    validate: {
      max: 220,
    },
  },
  heart_rate_avg: {
    type: DataTypes.INTEGER,
    allowNull: false,
  },
  distance: {
    type: DataTypes.INTEGER,
    allowNull: false,
    validate: {
      min: 1,
    },
  },
  duration: {
    type: Sequelize.TIME,
    allowNull: false,
  },
  id_user: {
    type: DataTypes.INTEGER,
    allowNull: false,
    references: {
      model: 'User',
      key: 'id',
    },
  },
}, {
  tableName: 'Activity',
  timestamps: false,
});

export default Activity;
