SET SERVEROUTPUT ON;

CREATE OR REPLACE TRIGGER trg_Compte_Client
INSTEAD OF INSERT ON Compte_Client
FOR EACH ROW
DECLARE
    v_numCompte INT;
BEGIN
    INSERT INTO Compte (numCompte, solde, typeCompte)
    VALUES (:NEW.numCompte, :NEW.solde, :NEW.typeCompte);
    
    INSERT INTO Client (numClient, nomClient, prenomClient)
    VALUES (:NEW.numClient, :NEW.nomClient, :NEW.prenomClient);

    INSERT INTO Appartient (unCompte, unClient)
    VALUES (:NEW.numCompte, :NEW.numClient);
END;
/