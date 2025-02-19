CREATE OR REPLACE VIEW Compte_Client AS ( SELECT Compte.*, unClient as numClient from Compte LEFT JOIN Appartient ON numCompte = unCompte);
SELECT * FROM Compte_Client;