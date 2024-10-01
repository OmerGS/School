-- Script de création et remplissage des tables
-- Syntaxe MySQL

/*
Compagnie (idComp (1), nomComp, pays, estLowCost)
Pilote (idPilote(1), nomPilote, nbHVol, compPil=@Compagnie(idComp))
TypeAvion(idTypeAvion(1),nbPassagers)
Qualification(unPilote=@Pilote(idPilote)(1),unTypeAvion=@TypeAvion(idTypeAvion)(1))
Avion(idAvion(1), leTypeAvion=@TypeAvion(idTypeAvion)(NN),compAv=@Compagnie(idComp)(NN))
*/

-- USE bd_r206;

DROP TABLE IF EXISTS Avion;
DROP TABLE IF EXISTS Qualification;
DROP TABLE IF EXISTS TypeAvion;
DROP TABLE IF EXISTS Pilote;
DROP TABLE IF EXISTS Compagnie;

CREATE TABLE Compagnie
	(
	idComp INTEGER,
	nomComp VARCHAR(30),
	pays VARCHAR(20),
	estLowCost INTEGER,

    CONSTRAINT pk_Compagnie PRIMARY KEY (idComp)
	)
;

CREATE TABLE Pilote
	(
	idPilote INTEGER,
	nomPilote VARCHAR(20),
	nbHVol INTEGER,
	compPil INTEGER,

    CONSTRAINT pk_Pilote PRIMARY KEY (idPilote),
	CONSTRAINT fk_Pilote_Compagnie FOREIGN KEY (compPil) REFERENCES Compagnie (idComp)
	)
;

CREATE TABLE TypeAvion
	(
	idTypeAvion VARCHAR(20),
	nbPassagers INTEGER,

    CONSTRAINT pk_TypeAvion PRIMARY KEY (idTypeAvion)
	)
;

CREATE TABLE Qualification
	(
	unPilote INTEGER,
	unTypeAvion VARCHAR(20),

	CONSTRAINT pk_Qualification	PRIMARY KEY (unPilote, unTypeAvion),
    CONSTRAINT fk_Qualification_Pilote FOREIGN KEY (unPilote) REFERENCES Pilote(idPilote),
    CONSTRAINT fk_Qualification_TypeAvion FOREIGN KEY (unTypeAvion) REFERENCES TypeAvion(idTypeAvion)
	)
;

CREATE TABLE Avion 
	(
	idAvion INTEGER,
	leTypeAvion VARCHAR(20),
	compAv INTEGER,
		
    CONSTRAINT pk_Avion	PRIMARY KEY (idAvion),
    CONSTRAINT fk_Avion_TypeAvion FOREIGN KEY (leTypeAvion) REFERENCES TypeAvion(idTypeAvion),
    CONSTRAINT fk_Avion_Compagnie FOREIGN KEY (compAv) REFERENCES Compagnie(idComp)
	)
;
	

DELETE FROM Avion;
DELETE FROM Qualification;
DELETE FROM TypeAvion;
DELETE FROM Pilote;
DELETE FROM Compagnie;

INSERT INTO Compagnie VALUES(1, 'Air France', 'France', 0);
INSERT INTO Compagnie VALUES(2, 'Corsair Internat', 'France', 0);
INSERT INTO Compagnie VALUES(3, 'EasyJet', 'Angleterre', 1);
INSERT INTO Compagnie VALUES(4, 'American Airlines', 'Etat-Unis', 0);
INSERT INTO Compagnie VALUES(5, 'Ryanair', 'Irelande', 1);

INSERT INTO Pilote VALUES(1, 'Ridard', 1500, 1);
INSERT INTO Pilote VALUES(2, 'Naert', 450, 3);
INSERT INTO Pilote VALUES(3, 'Godin', 450, 5);
INSERT INTO Pilote VALUES(4, 'Fleurquin', 3000, 1);
INSERT INTO Pilote VALUES(5, 'Pham', 900, 4);
INSERT INTO Pilote VALUES(6, 'Kerbellec', 900, NULL);
INSERT INTO Pilote VALUES(7, 'Kamp', 3000, 4);

INSERT INTO TypeAvion VALUES('A320', 174);
INSERT INTO TypeAvion VALUES('A350', 324);
INSERT INTO TypeAvion VALUES('B747', 279);

INSERT INTO Qualification VALUES(1, 'A320');
INSERT INTO Qualification VALUES(1, 'A350');
INSERT INTO Qualification VALUES(2, 'A320');
INSERT INTO Qualification VALUES(2, 'B747');
INSERT INTO Qualification VALUES(3, 'A320');
INSERT INTO Qualification VALUES(4, 'A320');
INSERT INTO Qualification VALUES(4, 'A350');
INSERT INTO Qualification VALUES(4, 'B747');
INSERT INTO Qualification VALUES(5, 'A350');
INSERT INTO Qualification VALUES(5, 'A320');
INSERT INTO Qualification VALUES(7, 'A350');
INSERT INTO Qualification VALUES(7, 'B747');

INSERT INTO Avion VALUES(1, 'A320', 1);
INSERT INTO Avion VALUES(2, 'A320', 3);
INSERT INTO Avion VALUES(3, 'A350', 1);
INSERT INTO Avion VALUES(4, 'A320', 2);
INSERT INTO Avion VALUES(5, 'B747', 1);
INSERT INTO Avion VALUES(6, 'A350', 4);
INSERT INTO Avion VALUES(7, 'B747', 4);
INSERT INTO Avion VALUES(8, 'A320', 5);
INSERT INTO Avion VALUES(9, 'A320', 5);




