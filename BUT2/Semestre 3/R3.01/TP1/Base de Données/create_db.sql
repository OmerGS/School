-- Création de la table Utilisateur
CREATE TABLE IF NOT EXISTS Utilisateur (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    Nom TEXT NOT NULL,
    Prenom TEXT NOT NULL,
    DateNaissance DATE,
    Sexe TEXT,
    Taille REAL,
    Poids REAL,
    AdresseEmail TEXT UNIQUE NOT NULL,
    MotDePasse TEXT NOT NULL
);

-- Création de la table Activité
CREATE TABLE IF NOT EXISTS Activite (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    Date DATE NOT NULL,
    Description TEXT,
    Temps INTEGER,
    UtilisateurID TEXT NOT NULL,
    Distance REAL,
    FOREIGN KEY (UtilisateurID) REFERENCES Utilisateur(ID)
);

-- Création de la table Données
CREATE TABLE IF NOT EXISTS Donnees (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    Temps INTEGER,  -- Temps en secondes
    FreqCardiaque INTEGER,
    Latitude REAL,
    Longitude REAL,
    Altitude REAL,
    ActiviteID INTEGER,
    FOREIGN KEY (ActiviteID) REFERENCES Activite(ID)
);

-- Insertion de données valides dans la table Utilisateur
INSERT INTO Utilisateur (Nom, Prenom, DateNaissance, Sexe, Taille, Poids, AdresseEmail, MotDePasse)
VALUES ('Dupont', 'Jean', '1980-05-12', 'Homme', 1.75, 75, 'jean.dupont@example.com', 'password123');

INSERT INTO Utilisateur (Nom, Prenom, DateNaissance, Sexe, Taille, Poids, AdresseEmail, MotDePasse)
VALUES ('Martin', 'Sophie', '1992-03-24', 'Femme', 1.65, 60, 'sophie.martin@example.com', 'sophiepass');

-- Insertion de données valides dans la table Activite
INSERT INTO Activite (Date, Description, Temps, UtilisateurID, Distance, ID)
VALUES ('2024-09-15', 'Course matinale', 3600, 'jean.dupont@example.com', 10.5, 1);

INSERT INTO Activite (Date, Description, Temps, UtilisateurID, Distance, ID)
VALUES ('2024-09-16', 'Marche rapide', 1800, 'sophie.martin@example.com', 3.2, 2);

-- Insertion de données valides dans la table Donnees
INSERT INTO Donnees (Temps, FreqCardiaque, Latitude, Longitude, Altitude, ActiviteID)
VALUES (600, 120, 48.8566, 2.3522, 35, 1);

INSERT INTO Donnees (Temps, FreqCardiaque, Latitude, Longitude, Altitude, ActiviteID)
VALUES (1200, 110, 48.8566, 2.3522, 35, 1);
