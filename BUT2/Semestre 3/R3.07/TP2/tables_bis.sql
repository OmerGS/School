-- Suppression des tables dans l'ordre inverse des dépendances
DROP TABLE Operation CASCADE CONSTRAINTS;
DROP TABLE Appartient CASCADE CONSTRAINTS;
DROP TABLE Client CASCADE CONSTRAINTS;
DROP TABLE Compte CASCADE CONSTRAINTS;
DROP TABLE Agent CASCADE CONSTRAINTS;
DROP TABLE Agence CASCADE CONSTRAINTS;

-- Création de la table Agence
CREATE TABLE Agence (
    numAgence INT PRIMARY KEY,
    telAgence VARCHAR2(50),
    adAgence VARCHAR2(50)
);

-- Création de la table Agent
CREATE TABLE Agent (
    numAgent INT PRIMARY KEY,
    nomAgent VARCHAR2(50),
    prenomAgent VARCHAR2(50),
    salaire NUMBER(8, 2) CHECK (salaire >= 1709.28),
    estDirecteur NUMBER(1) CHECK (estDirecteur IN (0, 1)),
    sonAgence INT,
    CONSTRAINT fk_Agent_Agence FOREIGN KEY (sonAgence) REFERENCES Agence(numAgence) ON DELETE CASCADE
);

-- Création de la table Compte
CREATE TABLE Compte (
    numCompte INT PRIMARY KEY,
    solde NUMBER(15, 2),
    typeCompte VARCHAR2(50) CHECK (typeCompte IN ('COURANT', 'EPARGNE'))
);

-- Création de la table Client
CREATE TABLE Client (
    numClient INT PRIMARY KEY,
    nomClient VARCHAR2(50),
    prenomClient VARCHAR2(50),
    adClient VARCHAR2(50),
    dateNaissClient DATE,
    sonAgent INT,
    compte INT,
    CONSTRAINT fk_Client_Agent FOREIGN KEY (sonAgent) REFERENCES Agent(numAgent) ON DELETE CASCADE,
    CONSTRAINT fk_Client_Compte FOREIGN KEY (compte) REFERENCES Compte(numCompte) ON DELETE CASCADE
);

CREATE TABLE Appartient (
    unCompte INT,
    unClient INT,
    CONSTRAINT fk_compte FOREIGN KEY (unCompte) REFERENCES Compte(numCompte) ON DELETE CASCADE,
    CONSTRAINT fk_client FOREIGN KEY (unClient) REFERENCES Client(numClient) ON DELETE CASCADE,
    CONSTRAINT pk_appartient PRIMARY KEY (unClient, unCompte)
);

-- Création de la table Operation
CREATE TABLE Operation (
    numOperation INT PRIMARY KEY,
    dateOperation DATE DEFAULT SYSDATE,
    typeOperation VARCHAR2(50) CHECK (typeOperation IN ('RETRAIT', 'DEPOT')),
    leclient INT,
    leCompte INT,
    montant NUMBER(15, 2) CHECK (montant > 0),
    CONSTRAINT fk_Operation_Client FOREIGN KEY (leclient) REFERENCES Client(numClient) ON DELETE CASCADE,
    CONSTRAINT fk_Operation_Compte FOREIGN KEY (leCompte) REFERENCES Compte(numCompte) ON DELETE CASCADE
);