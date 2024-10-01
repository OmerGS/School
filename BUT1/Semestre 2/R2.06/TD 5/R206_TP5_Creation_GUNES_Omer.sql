/*
Schéma relationnel :
--------------------
Agence(numAgence (1), telAgence, adAgence)
Agent(numAgent (1), nomAgent, prenomAgent, salaire, estDirecteur, sonAgence=@Agence.numAgence NN)
Client(numClient (1), nomClient, prenomClient, adClient, dateNaissClient, sonAgent=@Agent.numAgent NN)
Compte(numCompte (1), solde, typeCompte)
Operation(numOperation (1), dateOperation, typeOperation, montant, leClient=@Client.numClient NN,
		leCompte=@Compte.numCompte NN)
Appartient([unCompte=@Compte.numCompte, unClient=@Client.numClient] (1))

*/
use bd_r206;


DROP TABLE IF EXISTS Appartient;
DROP TABLE IF EXISTS Operation;
DROP TABLE IF EXISTS Compte;
DROP TABLE IF EXISTS Client;
DROP TABLE IF EXISTS Agent;
DROP TABLE IF EXISTS Agence;

CREATE TABLE Agence
	(
	numAgence INTEGER,
	telAgence VARCHAR(30),
	adAgence VARCHAR(30),
    
    CONSTRAINT pk_Compagnie PRIMARY KEY (numAgence)
	);
	
CREATE TABLE Agent
	(
	numAgent INTEGER(50),	
	nomAgent VARCHAR(30),
	prenomAgent VARCHAR(30),
	
	salaire int
	CONSTRAINT ck_salaire CHECK(salaire>=1480),
	
	directeur int
	CONSTRAINT ck_directeur CHECK(directeur=0 OR directeur=1),
	
	sonAgence int
    CONSTRAINT nn_sonAgence CHECK (sonAgence IS NOT NULL),
    
    PRIMARY KEY(numAgent),
    CONSTRAINT fk_agent FOREIGN KEY Agent(numAgent) REFERENCES Agence(numAgence)
	);
	
CREATE TABLE Client
	(
	numClient INTEGER,
	
	nomClient VARCHAR(30)
	CONSTRAINT nn_nomClient CHECK (nomClient IS NOT NULL),
	
	prenomClient VARCHAR(30)
	CONSTRAINT nn_prenomClient CHECK (prenomClient IS NOT NULL),
	
	adClient VARCHAR(30),
	
	dateNaissanceClient DATE,
	
	sonAgent INTEGER
	CONSTRAINT nn_sonAgent CHECK (sonAgent IS NOT NULL),
    
    PRIMARY KEY(numClient),
    CONSTRAINT fk_Client_Agent FOREIGN KEY Agence(numClient) REFERENCES Agent(numAgent)
	);
	
CREATE TABLE Compte
	(
	numCompte INTEGER,
	
	solde INTEGER,
	
	typeCompte VARCHAR(30)
	CONSTRAINT nn_typeCompte CHECK (typeCompte IS NOT NULL)
	CONSTRAINT ck_typeCompte CHECK(typeCompte='COURANT' OR typeCompte='EPARGNE'),
    
    PRIMARY KEY(numCompte)
	);
	
CREATE TABLE Operation
	(
	numOperation INTEGER,
	
	dateOperation DATE DEFAULT (CURRENT_DATE),
	
	typeOperation VARCHAR(30),
	CONSTRAINT ck_typeOperation CHECK(typeOperation='RETRAIT' OR typeOperation='DEPOT'),
	
	montant INTEGER
	CONSTRAINT ck_montant CHECK(montant>=0),
	
	leClient INTEGER,
	CONSTRAINT fk_Operation_Client FOREIGN KEY Operation(leClient) REFERENCES Client(numClient),
	CONSTRAINT nn_leClient CHECK (leClient IS NOT NULL),
	
	leCompte INTEGER,
	CONSTRAINT fk_Operation_Compte FOREIGN KEY Operation(leCompte) REFERENCES Compte(numCompte),
	CONSTRAINT nn_leCompte CHECK(leCompte IS NOT NULL),
    
    PRIMARY KEY(numOperation)
	);
	
CREATE TABLE Appartient
	(
	unCompte INTEGER,
	CONSTRAINT fk_Appartient_Compte FOREIGN KEY Appartient(unCompte) REFERENCES Compte(numCompte),
	
	unClient INTEGER,
	CONSTRAINT fk_Appartient_Client FOREIGN KEY Appartient(unClient) REFERENCES Client(numClient),
	
	CONSTRAINT pk_Appartient PRIMARY KEY (unCompte,unClient)
	);