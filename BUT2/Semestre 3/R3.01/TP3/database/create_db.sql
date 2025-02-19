-- =============================================================================
-- Script de création de la base de données sport_track
-- ============================================================================= 

-- ============================================================================= 
-- Propriétés de la base de données
-- ============================================================================= 
PRAGMA foreign_keys = ON; -- To enable foreign key constraints
PRAGMA encoding = 'UTF-8'; 

-- ============================================================================= 
-- Suppression des tables
-- ============================================================================= 
DROP TABLE IF EXISTS ActivityEntry;
DROP TABLE IF EXISTS Activity;
DROP TABLE IF EXISTS User;

-- ============================================================================= 
-- Création des tables
-- ============================================================================= 
CREATE TABLE IF NOT EXISTS User(
  id         INTEGER CONSTRAINT pk_user PRIMARY KEY AUTOINCREMENT,
  lastname   TEXT    CONSTRAINT nn_user_lastname NOT NULL,
  firstname  TEXT    CONSTRAINT nn_user_firstname NOT NULL,
  birthdate  DATE    CONSTRAINT nn_user_birthdate NOT NULL
                     CONSTRAINT ck_user_birthdate CHECK(birthdate BETWEEN '1920-01-01' AND CURRENT_DATE)
                     CONSTRAINT ck_user_birthdate_str CHECK(birthdate IS strftime('%Y-%m-%d', birthdate)),
  gender     TEXT    CONSTRAINT nn_user_gender NOT NULL
                     CONSTRAINT ck_user_gender CHECK (upper(gender) = 'MAN' OR upper(gender) = 'WOMAN'),
  height     INTEGER CONSTRAINT ck_user_height CHECK (height BETWEEN 50 and 230), -- height in centimeter
  weight     INTEGER CONSTRAINT ck_user_weight CHECK (weight BETWEEN 10 and 200), -- weight in kilograms
  email      TEXT    CONSTRAINT nn_user_email NOT NULL
                     CONSTRAINT uq_user_email UNIQUE
                     CONSTRAINT ck_user_email CHECK (email LIKE '%_@_%._%'
                                                     AND LENGTH(email) - LENGTH(REPLACE(email, '@', '')) = 1
                                                     AND SUBSTR(LOWER(email), 1, INSTR(email, '.') - 1) NOT GLOB '*[^@0-9a-z]*' 
                                                     AND SUBSTR(LOWER(email), INSTR(email, '.') + 1) NOT GLOB '*[^a-z]*'                     
                                                    ),
  password   TEXT    CONSTRAINT nn_user_password NOT NULL
                     CONSTRAINT ck_user_password CHECK (length(password) >= 8)
                     -- the format of the password is not checked here because it can be encrypted
);

CREATE TABLE IF NOT EXISTS Activity(
  id             INTEGER CONSTRAINT pk_activty PRIMARY KEY AUTOINCREMENT,
  date           DATE    CONSTRAINT nn_activity_date NOT NULL
                         CONSTRAINT ck_activity_date CHECK(date BETWEEN '1920-01-01' AND CURRENT_DATE)
                         CONSTRAINT ck_activity_date_str CHECK(date IS strftime('%Y-%m-%d', date)),
 description TEXT        CONSTRAINT nn_activity_description NOT NULL
                         CONSTRAINT ck_activity_description CHECK(length(description) < 250),
heart_rate_min  INTEGER CONSTRAINT nn_activity_freq_min NOT NULL
                 CONSTRAINT ck_activity_freq_min CHECK(heart_rate_min > 20),
 heart_rate_max  INTEGER CONSTRAINT nn_activity_freq_max NOT NULL
                         CONSTRAINT ck_acticity_freq_max CHECK(heart_rate_max < 220),
 heart_rate_avg  INTEGER CONSTRAINT nn_activity_freq_avg NOT NULL
                         CONSTRAINT ck_acticity_freq_avg CHECK(heart_rate_avg <= heart_rate_max AND heart_rate_avg >= heart_rate_min),
 distance        INTEGER CONSTRAINT nn_activity_dist NOT NULL
                         CONSTRAINT ck_activity_dist CHECK(distance > 0),
 duration        TIME    CONSTRAINT nn_activity_duration NOT NULL
                         CONSTRAINT ck_activity_duration CHECK(duration IS strftime('%H:%M:%S', duration)
                                                           AND time(duration) > time('00:00:00')),
 id_user         INTEGER CONSTRAINT nn_activity_id_user NOT NULL,
                         CONSTRAINT fk_activity_id_user FOREIGN KEY(id_user) REFERENCES User(id)
);

CREATE TABLE IF NOT EXISTS ActivityEntry(
  id            INTEGER   CONSTRAINT pk_activity_entry PRIMARY KEY AUTOINCREMENT,
  measure_time  TIME      CONSTRAINT nn_activity_entry_time NOT NULL
                          CONSTRAINT ck_activity_time CHECK(measure_time IS strftime('%H:%M:%S', measure_time)
                                                            AND time(measure_time) BETWEEN time('00:00:00') AND time('24:00:00')),
  heart_rate    INTEGER   CONSTRAINT nn_activity_entry_heart_rate NOT NULL
                          CONSTRAINT ck_activity_entry_heart_rate CHECK(heart_rate BETWEEN 20 and 220),                  
  longitude     FLOAT     CONSTRAINT nn_activity_entry_longitude NOT NULL
                          CONSTRAINT ck_activity_entry_longitude CHECK (longitude BETWEEN -180 AND 180),               
  latitude      FLOAT     CONSTRAINT nn_activity_entry_latitude NOT NULL
                          CONSTRAINT ck_activity_entry_latitude CHECK (latitude BETWEEN -90 AND 90),        
  altitude      FLOAT     CONSTRAINT nn_activity_entry_altitude NOT NULL
                          CONSTRAINT ck_activity_entry_altitude CHECK (altitude BETWEEN -1000 AND 9000),        
  id_activity   INTEGER   CONSTRAINT nn_activity_entry_id_act NOT NULL,
                          CONSTRAINT fk_activity_entry_id_act FOREIGN KEY(id_activity) REFERENCES Activity(id)
);

INSERT INTO User (lastname, firstname, birthdate, gender, height, weight, email, password) 
VALUES 
('Doe', 'John', '1990-05-15', 'MAN', 180, 75, 'johndoe@example.com', 'password123'),
('Smith', 'Jane', '1985-09-25', 'WOMAN', 165, 60, 'janesmith@example.com', 'securePass!'),
('Brown', 'Charlie', '2000-01-10', 'MAN', 170, 68, 'charliebrown@example.com', 'charlieP@ss1');


INSERT INTO Activity (date, description, heart_rate_min, heart_rate_max, heart_rate_avg, distance, duration, id_user) 
VALUES 
('2024-01-01', 'Morning run', 60, 150, 120, 5000, '00:30:00', 1),
('2024-02-15', 'Evening cycling', 55, 145, 110, 10000, '00:45:00', 2),
('2024-03-10', 'Swimming session', 70, 160, 130, 2000, '01:00:00', 3);


INSERT INTO ActivityEntry (measure_time, heart_rate, longitude, latitude, altitude, id_activity) 
VALUES 
('00:05:00', 80, 48.8566, 2.3522, 35, 1),
('00:15:00', 95, 48.8566, 2.3522, 35, 1),
('00:30:00', 110, 48.8566, 2.3522, 35, 1),
('00:10:00', 85, 34.0522, -89.2437, 71, 2),
('00:25:00', 105, 34.0522, -89.2437, 71, 2),
('00:40:00', 120, 34.0522, -89.2437, 71, 2);
