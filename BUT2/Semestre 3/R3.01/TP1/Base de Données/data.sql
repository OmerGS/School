-- Insertion de données valides dans la table Utilisateur
INSERT INTO Utilisateur (Nom, Prenom, DateNaissance, Sexe, Taille, Poids, AdresseEmail, MotDePasse)
VALUES ('Dupont', 'Jean', '1980-05-12', 'Homme', 1.75, 75, 'jean.dupont@example.com', 'password123');

INSERT INTO Utilisateur (Nom, Prenom, DateNaissance, Sexe, Taille, Poids, AdresseEmail, MotDePasse)
VALUES ('Martin', 'Sophie', '1992-03-24', 'Femme', 1.65, 60, 'sophie.martin@example.com', 'sophiepass');

-- Insertion d'une donnée non valide (adresse e-mail en double)
-- Cette insertion devrait échouer à cause de la contrainte UNIQUE sur AdresseEmail
INSERT INTO Utilisateur (Nom, Prenom, DateNaissance, Sexe, Taille, Poids, AdresseEmail, MotDePasse)
VALUES ('Durand', 'Paul', '1985-07-09', 'Homme', 1.80, 80, 'jean.dupont@example.com', 'paulpass');

-- Insertion de données valides dans la table Activite
INSERT INTO Activite (Date, Description, Temps, UtilisateurID, Distance, ID)
VALUES ('2024-09-15', 'Course matinale', 3600, 'jean.dupont@example.com', 10.5, 1);

INSERT INTO Activite (Date, Description, Temps, UtilisateurID, Distance, ID, 1)
VALUES ('2024-09-16', 'Marche rapide', 1800, 'sophie.martin@example.com', 3.2);

-- Insertion d'une donnée non valide (UtilisateurID qui n'existe pas)
-- Cette insertion devrait échouer à cause de la contrainte FOREIGN KEY sur UtilisateurID
INSERT INTO Activite (Date, Description, Temps, UtilisateurID, Distance, ID)
VALUES ('2024-09-17', 'Cyclisme', 5400, 'roger.caillou@example.com', 25.0, 2);

-- Insertion de données valides dans la table Donnees
INSERT INTO Donnees (Temps, FreqCardiaque, Latitude, Longitude, Altitude, ActiviteID)
VALUES (600, 120, 48.8566, 2.3522, 35, 1);

INSERT INTO Donnees (Temps, FreqCardiaque, Latitude, Longitude, Altitude, ActiviteID)
VALUES (1200, 110, 48.8566, 2.3522, 35, 1);

-- Insertion d'une donnée non valide (ActiviteID qui n'existe pas)
-- Cette insertion devrait échouer à cause de la contrainte FOREIGN KEY sur ActiviteID
INSERT INTO Donnees (Temps, FreqCardiaque, Latitude, Longitude, Altitude, ActiviteID)
VALUES (300, 100, 48.8566, 2.3522, 35, 32);
