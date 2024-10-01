DROP TABLE Location;
DROP TABLE Vehicule;
DROP TABLE Client;
DROP TABLE Modele;

/*----- QUESTION 1 -----*/
CREATE TABLE Modele(
    idModele NUMBER
    CONSTRAINT pk_idModele PRIMARY KEY,
    
    marque VARCHAR2(20),
    
    couleur VARCHAR2(20),
    
    puissance NUMBER
);

CREATE TABLE Client(
    idClient NUMBER
    CONSTRAINT pk_idClient PRIMARY KEY,
    
    nom VARCHAR2(30)
    CONSTRAINT nn_nom NOT NULL,
    
    prenom VARCHAR2(30),
    
    adresse VARCHAR2(50)
);

CREATE TABLE Vehicule(
    immat VARCHAR2(30)
    CONSTRAINT pk_immat PRIMARY KEY,
    
    leModele NUMBER
    CONSTRAINT fk_leModele_Modele
    REFERENCES modele(idModele),
    
    dateAchat DATE
);

CREATE TABLE Location(
    unClient NUMBER
        CONSTRAINT fk_leClient
            REFERENCES Client(idClient),
    
    unModele NUMBER
        CONSTRAINT fk_leModele
            REFERENCES Modele(idModele),
    
    dateLocation DATE,
    
    duree NUMBER
        CONSTRAINT ck_laDuree
            CHECK (duree > 0),
            
    CONSTRAINT pk_laLocation
        PRIMARY KEY (unClient,unModele)
);


/*Nous ne pouvons pas traiter la contrainte (dateLocation > dateAchat)*/

/*QUESTION 3 : */
ALTER TABLE Vehicule
MODIFY dateAchat DATE CONSTRAINT nn_dateAchat NOT NULL;

/*QUESTION 4 : */
INSERT INTO Client VALUES
(1, 'GUNES', 'Omer', '56000 Vannes');

INSERT INTO Modele VALUES
(20, 'BMW', 'noir', 120);

INSERT INTO Vehicule VALUES
('20232811AGTHEZG', 20, '25-11-2023');

INSERT INTO Location VALUES
(2,'20232811AGTHEZG', 20 ,'27-11-2023', 30);

/* Erreur Obtenu après l'éxecution de l'insertion : 

Erreur SQL : ORA-00913: too many values
00913. 00000 -  "too many values"
*Cause:    
*Action:   */





/*QUESTION 5 : */
/*
Concession(idConc (1), nomConc, capital);
Voiture(immat(1), modele(nn), couleur, laConcession =@ Concession.idConc, leConstructeur =@ Constructeur.idConst(nn), lClient =@ Client.idClient );
Constructeur(idConst(1), nomConst(1));
Client(idClient(1), nomClient(2), prenomClient(2));
Assurance(unClient =@ Client.idClient, unConstructeur =@ constructeur =@ constructeur.idClient(1), dateContrat);
Travail([uneConc =@ Concession.idConc, unConstructeur =@ Constructeur.idConst](1));

Voiture(leClient) = Client(idClient) Un Client doit acheter au moins 1 voiture
Assurance(unClient) = Client(idClient) Un Client doit être assuré au moins par une assurance
Travail(uneConc) = Concession(idConc) Une concession travail avec au moins un constructeur
Un constructeur travaille avec au moins deux concessions.
*/

/*QUESTION 6 :*/
DROP TABLE Travail;
DROP TABLE Assurance;
DROP TABLE Vehicule2;
DROP TABLE Client2;
DROP TABLE Constructeur;
DROP TABLE Concession;

CREATE TABLE Concession (
    idConc INT PRIMARY KEY,
    nomConc VARCHAR(255),
    capital DECIMAL(10, 2)
);

CREATE TABLE Constructeur(
    idConst INT PRIMARY KEY,
    nomConst VARCHAR(255)
);

CREATE TABLE Client2 (
    idClient INT PRIMARY KEY,
    nomClient VARCHAR(255),
    prenomClient VARCHAR(255)
);

CREATE TABLE Vehicule2 (
    immat VARCHAR(20) PRIMARY KEY,
    modele VARCHAR(255) NOT NULL,
    couleur VARCHAR(255),
    laConcession INT REFERENCES Concession(idConc),
    leConstructeur INT REFERENCES Constructeur(idConst),
    lClient INT REFERENCES Client2(idClient)
);

CREATE TABLE Assurance (
    unClient INT REFERENCES Client2(idClient),
    unConstructeur INT REFERENCES Constructeur(idConst),
    dateContrat DATE,
    PRIMARY KEY (unClient, unConstructeur)
);

CREATE TABLE Travail (
    uneConc INT REFERENCES Concession(idConc),
    unConstructeur INT REFERENCES Constructeur(idConst),
    PRIMARY KEY (uneConc, unConstructeur)
);

/*QUESTION 7 : */
ALTER TABLE Assurance
MODIFY dateContrat DATE CONSTRAINT nn_dateContrat NOT NULL;

/*Question 8 : */
ALTER TABLE Client2
ADD (email VARCHAR2(50) CONSTRAINT ck_email CHECK (email LIKE '%@%'));

/* QUESTION 9 : */
INSERT INTO Concession (idConc, nomConc, capital) VALUES (1, 'Concession1', 100000);

INSERT INTO Constructeur (idConst, nomConst) VALUES (5, 'Constructeur5');

INSERT INTO Client2 (idClient, nomClient, prenomClient) VALUES (1, 'Client1', 'Prenom1');

INSERT INTO Assurance (unClient, unConstructeur, dateContrat) VALUES (1, 1, TO_DATE('2023-11-28', 'YYYY-MM-DD'));

INSERT INTO Vehicule2 (immat, modele, couleur, laConcession, leConstructeur, lClient) 
VALUES ('123ABC', 'Modele1', 'Rouge', 1, 1, 1);

INSERT INTO Vehicule2 (immat, modele, couleur, laConcession, leConstructeur, lClient) 
VALUES ('456XYZ', 'Modele2', 'Bleu', 1, 1, 1);

/* QUESTION 10 : */

/*Erreur : ORA-02291: integrity constraint (OMER.SYS_C008233) violated - parent key not found */
/* génére une erreur car le client avec l'id 2 n'existe pas */
INSERT INTO Assurance (unClient, unConstructeur, dateContrat) VALUES (2, 1, TO_DATE('2023-11-28', 'YYYY-MM-DD'));

/*Erreur : ORA-02291: integrity constraint (OMER.SYS_C008236) violated - parent key not found*/
/* génére une erreur car la concession avec l'id 2 n'existe pas */
INSERT INTO Travail (uneConc, unConstructeur) VALUES (2, 1);

/*Erreur : ORA-02292: integrity constraint (OMER.SYS_C008230) violated - child record found */
/* génére une erreur car le constructeur avec l'id 1 est lié à une assurance */
DELETE FROM Constructeur WHERE idConst = 1;

/*Erreur : ORA-02292: integrity constraint (OMER.SYS_C008229) violated - child record found*/
/* génére une erreur car la concession avec l'id 1 est liée à un travail */
DELETE FROM Concession WHERE idConc = 1;

ALTER TABLE Vehicule2
ADD (commentaire VARCHAR2(255));

UPDATE Vehicule2
SET commentaire = 'Bonne condition'
WHERE immat = '123ABC';

UPDATE Client2
SET nomClient = 'NouveauNom'
WHERE idClient = 1;

