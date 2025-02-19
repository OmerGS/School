CREATE OR REPLACE VIEW Compte_Client AS
SELECT 
    Client.numClient,
    Client.nomClient,
    Client.prenomClient,
    Compte.numCompte,
    Compte.solde,
    Compte.typeCompte
FROM 
    Client
JOIN 
    Appartient ON Client.numClient = Appartient.unClient
JOIN 
    Compte ON Appartient.unCompte = Compte.numCompte;