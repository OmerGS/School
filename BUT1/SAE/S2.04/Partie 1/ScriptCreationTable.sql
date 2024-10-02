
USE bd_communeBretonneTest;
/*
-- création d'une nouvelle base de données
CREATE DATABASE bd_CommuneBretonne ;
USE bd_CommuneBretonne;


Schéma relationnel :
--------------------
Departement(codeDepartement (1), nom NN)
CommunesBretonnes(codeInsee (1), departement=@Departement.codeDepartement NN, nomCommune NN)
Aeroport(nomAeroport (), numDepartement=@Departement.codeDepartement NN, adresse NN)
Gare(codeGare (1), nomGare NN, fret NN, voyageurs NN, nomCommune=@CommunesBretonnes.nomCommune NN, departement=@Departement.nom NN, codeCommune=@CommunesBretonnes.codeInsee NN)
voisinnageCommune([insee=@CommunesBretonnes.codeInsee] (1), nom=@CommunesBretonnes.nomCommune NN, nbVoisin NN, inseeVoisin) 
InvestissementCulturelleDepartement([codeInsee=@Departement.codeDepartement, annee] (1), total)
DepenseCulturelleCommunes([codeInsee=@CommunesBretonnes.codeInsee, annee] (1), libelleGeographique=@CommunesBretonnes.nomCommune, populationMunicipale, depenseCulturelle, budgetCommune)
PrixParCommunes([codeInsee=@CommunesBretonnes.codeInsee, annee] (1), nbMaisonVendu, nbAppartVendu, prixMoyen, prixMettreCarreMoyen, surfaceMoyenne )
TauxInflationParAn(annee (1), tauxInflation NN)
*/
DROP TABLE IF EXISTS TauxInflationParAn;
DROP TABLE IF EXISTS PrixParCommunes;
DROP TABLE IF EXISTS DepenseCulturelleCommunes;
DROP TABLE IF EXISTS InvestissementCulturelleDepartement;
DROP TABLE IF EXISTS voisinnageCommune;
DROP TABLE IF EXISTS Gare;
DROP TABLE IF EXISTS Aeroport;
DROP TABLE IF EXISTS CommunesBretonnes;
DROP TABLE IF EXISTS Departement;

CREATE TABLE Departement
    (
    codeDepartement INTEGER,
    nom VARCHAR(30) NOT NULL,

    CONSTRAINT pk_leDepartement PRIMARY KEY(codeDepartement),
    INDEX (nom) -- Ajout de l'index sur la colonne nom
    );

CREATE TABLE CommunesBretonnes
    (
    codeInsee INTEGER,
    departement INTEGER NOT NULL,
    nomCommune VARCHAR(30) NOT NULL,


    CONSTRAINT pk_laCommune PRIMARY KEY(codeInsee),
    CONSTRAINT fk_unDepartementCommune FOREIGN KEY(departement) REFERENCES Departement(codeDepartement),
    INDEX (nomCommune) -- Ajout de l'index sur la colonne nomCommune
    );

CREATE TABLE Aeroport
    (
    nomAeroport VARCHAR(30),
    numDepartement INTEGER,
    adresse VARCHAR(30),

    CONSTRAINT pk_lAeroport PRIMARY KEY(nomAeroport),
    CONSTRAINT fk_unDepartementAeroport FOREIGN KEY(numDepartement) REFERENCES Departement(codeDepartement)
    );

CREATE TABLE Gare
    (
    codeGare INTEGER,
    nomGare VARCHAR(30) NOT NULL,
    fret CHAR NOT NULL,
    voyageurs CHAR NOT NULL,
    nomCommune VARCHAR(30) NOT NULL,
    departement VARCHAR(30) NOT NULL,
    codeCommune INTEGER NOT NULL,

    CONSTRAINT pk_laGare PRIMARY KEY(codeGare),
    CONSTRAINT fk_unDepartementGare FOREIGN KEY(departement) REFERENCES Departement(nom),
    CONSTRAINT fk_laCommuneGare FOREIGN KEY(codeCommune) REFERENCES CommunesBretonnes(codeInsee),
	CONSTRAINT fk_nomCommuneGare FOREIGN KEY(nomCommune) REFERENCES CommunesBretonnes(nomCommune)
    );

CREATE TABLE VoisinnageCommune
    (
    insee INTEGER,
    nom VARCHAR(30) NOT NULL,
    nbVoisin INTEGER NOT NULL,
    inseeVoisin VARCHAR(50),

    CONSTRAINT fk_laCommuneEtVoisin FOREIGN KEY(insee) REFERENCES CommunesBretonnes(codeInsee),
    CONSTRAINT pk_laCommuneEtVoisin PRIMARY KEY(insee),
    CONSTRAINT fk_nomCommuneEtVoisin FOREIGN KEY(nom) REFERENCES CommunesBretonnes(nomCommune),
    CONSTRAINT ck_nbVoisin CHECK(nbVoisin>=0)
    );

CREATE TABLE InvestissementCulturelleDepartement
    (
    codeInsee INTEGER,
    annee INTEGER,
    total FLOAT,
    CONSTRAINT fk_laCommuneInvestissement FOREIGN KEY(codeInsee) REFERENCES Departement(codeDepartement),
    CONSTRAINT pk_AppartientInvestissement PRIMARY KEY (codeInsee,annee),
    CONSTRAINT ck_total CHECK(total>=0)
    );


CREATE TABLE DepenseCulturelleCommunes
    (
    annee INTEGER,
    libelleGeographique VARCHAR(30) NOT NULL,
    codeInsee INTEGER,
    populationMunicipale FLOAT,
    depenseCulturelle FLOAT,
    budgetCommune FLOAT,

    CONSTRAINT fk_laCommuneDepense FOREIGN KEY(codeInsee) REFERENCES CommunesBretonnes(codeInsee),
    CONSTRAINT pk_AppartientDepense PRIMARY KEY (codeInsee,annee),
    CONSTRAINT fk_nomCommuneDepense FOREIGN KEY(libelleGeographique) REFERENCES CommunesBretonnes(nomCommune),
    CONSTRAINT ck_populationMunicipale CHECK(populationMunicipale>=0),
    CONSTRAINT ck_depenseCulturelle CHECK(depenseCulturelle>=0),
    CONSTRAINT ck_budgetCommune CHECK(budgetCommune>=0)
    );

CREATE TABLE PrixParCommunes
    (
    codeInsee INTEGER,
    annee INTEGER,
    nbMaisonVendu INTEGER,
    nbAppartVendu INTEGER,
    prixMoyen FLOAT,
    prixMettreCarreMoyen FLOAT,
    surfaceMoyenne FLOAT,

    CONSTRAINT fk_laCommunePrix FOREIGN KEY(codeInsee) REFERENCES CommunesBretonnes(codeInsee),
    CONSTRAINT pk_AppartientPrix PRIMARY KEY (codeInsee,annee),
    CONSTRAINT ck_nbMaisonVendu CHECK(nbMaisonVendu>=0),
    CONSTRAINT ck_nbAppartVendu CHECK(nbAppartVendu>=0),
    CONSTRAINT ck_prixMoyen CHECK(prixMoyen>=0),
    CONSTRAINT ck_prixMettreCarreMoyen CHECK(prixMettreCarreMoyen>=0),
    CONSTRAINT ck_surfaceMoyenne CHECK(surfaceMoyenne>=0)
    );

CREATE TABLE TauxInflationParAn
    (
    annee INTEGER,
    tauxInflation INTEGER NOT NULL,

    CONSTRAINT pk_AppartientTaux PRIMARY KEY (annee)
    );
