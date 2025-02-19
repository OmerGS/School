SELECT * FROM Compte WHERE numCompte=19;
DELETE FROM Compte WHERE numCompte = 19;
-- Suppression impossible => Violation de contrainte d'integrité. Il existe des associations dans Appartient
SELECT * FROM Appartient WHERE UnCompte=19;
SELECT * FROM Compte WHERE numCompte=19;
ROLLBACK;

INSERT INTO Compte_Client VALUES (21, 50, 'EPARGNE', 1); -- Pas possible car la vue est basée sur 2 tables
DELETE FROM Compte_Client WHERE numClient IS NULL;  -- Pas possible car la vue est basée sur 2 tables 
UPDATE Compte_Client SET numClient = 1 WHERE numClient IS NULL;  -- Pas possible car la vue est basée sur 2 tables 