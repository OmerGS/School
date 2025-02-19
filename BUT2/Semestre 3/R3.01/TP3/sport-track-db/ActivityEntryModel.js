import { Sequelize, DataTypes } from 'sequelize';
import { Activity } from './index.js';


const sequelize = new Sequelize({
  dialect: 'sqlite',
  storage: './sport_track.db',
});

const ActivityEntry = sequelize.define("ActivityEntry", {
  id: {
    type: DataTypes.INTEGER,
    autoIncrement: true,
    primaryKey: true,
  },
  measure_time: {
    type: DataTypes.TIME,
    allowNull: false,
    validate: {
      is: /^([0-1]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$/,
    },
  },
  heart_rate: {
    type: DataTypes.INTEGER,
    allowNull: false,
    validate: {
      min: 20,
      max: 220,
    },
  },
  longitude: {
    type: DataTypes.FLOAT,
    allowNull: false,
    validate: {
      min: -180,
      max: 180,
    },
  },
  latitude: {
    type: DataTypes.FLOAT,
    allowNull: false,
    validate: {
      min: -90,
      max: 90,
    },
  },
  altitude: {
    type: DataTypes.FLOAT,
    allowNull: false,
    validate: {
      min: -1000, 
      max: 9000,
    },
  },
  id_activity: {
    type: DataTypes.INTEGER,
    allowNull: false,
    references: {
      model: Activity,
      key: 'id',
    },
  },
}, {
  tableName: 'ActivityEntry',
  timestamps: false,
});

export default ActivityEntry;
