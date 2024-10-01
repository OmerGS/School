/*
TP2 R206
*/


/* === EXERCICE 1 === */
-- QUESTION 1

-- Script de création et remplissage des tables
-- Syntaxe MySQL

/*
Compagnie (idComp (1), nomComp, pays, estLowCost)
Pilote (idPilote(1), nomPilote, nbHVol, compPil=@Compagnie(idComp))
TypeAvion(idTypeAvion(1),nbPassagers)
Qualification(unPilote=@Pilote(idPilote)(1),unTypeAvion=@TypeAvion(idTypeAvion)(1))
Avion(idAvion(1), leTypeAvion=@TypeAvion(idTypeAvion)(NN),compAv=@Compagnie(idComp)(NN))
*/

USE bd_r206;

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




-- Question 2
/*
SELECT nomPilote, nomComp
FROM Pilote
JOIN Compagnie ON compPil = idComp;
*/

/*
##nomPilote, nomComp
6 Tuples
'Ridard', 'Air France'
'Fleurquin', 'Air France'
'Naert', 'EasyJet'
'Pham', 'American Airlines'
'Kamp', 'American Airlines'
'Godin', 'Ryanair'
*/

--

/*
SELECT nomPilote, nomComp, unTypeAvion
FROM Pilote
    JOIN Compagnie ON compPil = idComp
        JOIN Qualification ON idPilote = unPilote;
*/

/*
# nomPilote, nomComp, unTypeAvion
12 Tuples
'Ridard', 'Air France', 'A320'
'Ridard', 'Air France', 'A350'
'Fleurquin', 'Air France', 'A320'
'Fleurquin', 'Air France', 'A350'
'Fleurquin', 'Air France', 'B747'
'Naert', 'EasyJet', 'A320'
*/

--
/*
SELECT nomPilote, nomComp
FROM Pilote
    LEFT JOIN Compagnie ON compPil = idComp;
*/
/*
# nomPilote, nomComp
7 Tuples
'Ridard', 'Air France'
'Naert', 'EasyJet'
'Godin', 'Ryanair'
'Fleurquin', 'Air France'
'Pham', 'American Airlines'
*/

--

-- QUESTION 3

-- a)
/*
SELECT nomComp
FROM Compagnie
WHERE estLowCost = 1;
*/

/*
# nomComp
2 Tuples
'EasyJet'
'Ryanair'

*/

-- b)
/*
SELECT nomPilote
FROM Pilote
WHERE idPilote NOT IN ( SELECT unPilote
						FROM Qualification
);
*/

/*
1 Tuples
# nomPilote
'Kerbellec'
*/

-- c)
/*
SELECT nomPilote
FROM ( SELECT *
	FROM Pilote
	ORDER BY nomPilote DESC
	) AS subQuerry
LIMIT 5;
*/

/*
5 Tuples
# nomPilote
'Ridard'
'Pham'
'Naert'
'Kerbellec'
'Kamp'
*/



























































/* === EXERCICE 2  (BDD Apprenti) === */

DROP TABLE IF EXISTS Responsabilite;
DROP TABLE IF EXISTS Apprenti;
DROP TABLE IF EXISTS Stagiaire;
DROP TABLE IF EXISTS Entreprise;
DROP TABLE IF EXISTS Etudiant;
DROP TABLE IF EXISTS GroupeInfo1;
DROP TABLE IF EXISTS Enseignant;


CREATE TABLE Enseignant
	(
	idEns VARCHAR(3),
	nomEns VARCHAR(20),
    prenomEns VARCHAR(20),
    CONSTRAINT pk_Enseignant PRIMARY KEY(idEns)
	)
;
        
CREATE TABLE GroupeInfo1
	(
	idGroupe CHAR,
	tuteurGroupe VARCHAR(3) NOT NULL,
	CONSTRAINT pk_GroupeInfo1 PRIMARY KEY(idGroupe),
    CONSTRAINT fk_GroupeInfo1_Enseignant FOREIGN KEY(tuteurGroupe) REFERENCES Enseignant(idEns)
	)
;

CREATE TABLE Etudiant
	(
	idEtud INTEGER,
	nomEtud VARCHAR(20),
	prenomEtud VARCHAR(20),
	sexe CHAR,
	bac VARCHAR(5),
    nomLycee VARCHAR(50),
    depLycee INTEGER,
    leGroupeInfo1 CHAR NOT NULL,
	parcoursInfo2 VARCHAR(2),
    formationInfo2 VARCHAR(3),
    poursuiteEtudes VARCHAR(30),
    CONSTRAINT pk_Etudiant PRIMARY KEY(idEtud),
    CONSTRAINT fk_Etudiant_GroupeInfo1 FOREIGN KEY(leGroupeInfo1) REFERENCES GroupeInfo1(idGroupe)
	)
;

CREATE TABLE Entreprise
	(
	idEntreprise INT,
	nomEntreprise VARCHAR(40),
    depEntreprise INT,
    CONSTRAINT pk_Entreprise PRIMARY KEY(idEntreprise)
	)
;

CREATE TABLE Stagiaire
	(
	etudStagiaire INTEGER,
	entrepriseStage INTEGER NOT NULL,
	CONSTRAINT fk_Stagiaire_Etudiant FOREIGN KEY(etudStagiaire) REFERENCES Etudiant(idEtud),
    CONSTRAINT fk_Stagiaire_Entreprise FOREIGN KEY(entrepriseStage) REFERENCES Entreprise(idEntreprise),
    CONSTRAINT pk_Stagiaire PRIMARY KEY(etudStagiaire)
	)
;

CREATE TABLE Apprenti
	(
	etudApp INTEGER,
	entrepriseApp INTEGER NOT NULL,
    tuteurApp VARCHAR(3) NOT NULL,
    CONSTRAINT fk_Apprenti_Etudiant FOREIGN KEY(etudApp) REFERENCES Etudiant(idEtud),
	CONSTRAINT pk_Apprenti PRIMARY KEY(etudApp),
    CONSTRAINT fk_Apprenti_Entreprise FOREIGN KEY(entrepriseApp) REFERENCES Entreprise(idEntreprise),
    CONSTRAINT fk_Apprenti_Enseignant FOREIGN KEY(tuteurApp) REFERENCES Enseignant(idEns)
	)
;

-- Ajout de la table Responsabilite
CREATE TABLE Responsabilite
	(
		intituleResp VARCHAR(20),
		leResp VARCHAR(3),
		CONSTRAINT pk_Responsabilite PRIMARY KEY(intituleResp)
	)
;


-- SET SQL_SAFE_UPDATES = 0;

DELETE FROM Responsabilite;
DELETE FROM Apprenti;
DELETE FROM Stagiaire;
DELETE FROM Entreprise;
DELETE FROM Etudiant;
DELETE FROM GroupeInfo1;
DELETE FROM Enseignant;

-- Table Enseignant

INSERT INTO Enseignant VALUES 
('PB','Baudont','Pascal'),
('IB','Borne','Isabelle'),
('RF','Fleurquin','Regis'),
 ('TG','Godin','Thibault'),
 ('PJ','Joucla','Philippe'),
 ('JFK','Kamp','Jean-Francois'),
 ('GK','Kerbellec','Goulven'),
 ('MLL','Le Lain','Matthieu'),
 ('NLS','Le Sommer','Nicolas'),
 ('SL','Lefevre','Sebastien'),
 ('EL','Lemaitre','Elodie'),
 ('FL','Lesueur','Francois'),
 ('MM','Mannevy','Muriel'),
 ('FME','Merciol','Francois'),
 ('LN','Naert','Lucie'),
 ('MTP','Pham','Minh-Tan'),
 ('SR','Raut','Sophie'),
 ('XR','Roirand','Xavier'),
 ('PT','Tonin','Philippe'),
 ('HT','Tuffigo','Helene'),
 ('MV','Volin','Manon');

--  Table GroupeInfo1

INSERT INTO GroupeInfo1 VALUES 
 ('A','TG'),
 ('B','HT'),
 ('C','LN'),
 ('D','PT');

--  Table ETUDIANT

INSERT INTO ETUDIANT VALUES 
 (21900754,'ADAM','ANTOINE','M','S','LYCEE GENERAL ET TECHNOLOGIQUE FELIX LE',22,'B','IA','App',null),
 (21900678,'AJELLO','PAUL','M','S','LYCEE GENERAL PRIVE ST FRANCOIS-XAVIER',56,'B',null,null,null),
 (21905324,'ARTAUD','PAUL','M','STI2D','LYCEE POLYVALENT PRIVE ST JOSEPH LA JOLI',49,'D',null,null,null),
 (21900265,'AUVRAY','ALEXANDRE','M','S','LYCEE GEN.ET TECHNOL.PRIVE INSTITUT SAIN',50,'A','DA','FI','IFSI Normandie'),
 (21901052,'BEAUCLAIR','ADRIEN','M','STMG','LYC POLYVAL PRIVE des METIERS JEAN-BAPTI',35,'A','IA','FI','ESNA'),
 (21906543,'BENABBOU','ANAS','M',null,null,null,'D',null,null,null),
 (21902500,'BERNIER','ALLAN','M','STI2D','LYCEE GENERAL ET TECHNOLOGIQUE JOLIOT-CU',35,'C','DA','FI','EPITECH Rennes'),
 (21900646,'BIERI','KERYANN','M','S','LPO LYCEE DES METIERS COLBERT',56,'B',null,null,null),
 (21903032,'BLIVET','LAURELINE','F','S','LYCEE GENERAL ET TECHNOLOGIQUE EMILE ZOL',35,'A','IA','FI','Licence Pro Nantes'),
 (21901464,'BOIVENT','PIERRE','M','S','LPO PRIVE DES METIERS INSTITUTION ST MAL',35,'A','IA','FI','ENIBrest'),
 (21900169,'BONNET','BENJAMIN','M','S','LYCEE GENERAL GALILEE',44,'B','DA','FI','Licence Histoire Nantes'),
 (21900298,'BOUTINAUD','ALEXANDRE','M','STI2D','LYCEE GENERAL ET TECHNOLOGIQUE BEAUMONT',35,'B',null,null,null),
 (21900347,'BRAUD','ANTOINE','M','S','LY ST JOSEPH VANNES',56,'B','DA','FI','ESAIP'),
 (21905341,'BRAULT','MAXIME','M','S','LYCEE EMILE ZOLA RENNES',35,'C',null,null,null),
 (21901463,'BREIT HOARAU','EMELINE','F','S','LYC POLYVAL PRIVE DES METIERS LA MENNAIS',35,'A',null,null,null),
 (21901620,'BRUN','DONOVAN','M','STI2D','LYCEE GENERAL ET TECHNOLOGIQUE JOLIOT-CU',35,'B','IA','FI','IMT'),
 (21900755,'BUAN','KILIAN','M','S','LYCEE GENERAL ET TECHNOLOGIQUE LESAGE',56,'B','IA','FI','Licence info Vannes'),
 (21901179,'BUCHE','SYLVAIN','M','S','LYCEE GENERAL ET TECHNOLOGIQUE JOLIOT-CU',35,'C','DA','FI','Licence info Vannes'),
 (21902463,'CALVEZ','LOUIS','M','S','LYCEE GEN TECHNO LE LIKES',29,'B','IA','FI','BUT Génie Biologique'),
 (21901650,'CARN','YOHAN','M','S','LYCEE GENERAL DE LHARTELOIRE',29,'D','IA','App',null),
 (21900282,'CASTELLA','MATEO','M','S','LY ST JOSEPH VANNES',56,'D','IA','App','ESIEA Laval'),
 (21900839,'CHASTANIER','TANGUY','M','S','LYCEE GEN ET TECHNOL PRIVE ST PAUL',56,'C','DA','FI',null),
 (21903410,'CHIFFOT','CALVIN','M','S','LYCEE GENERAL PRIVE LA PERVERIE SACRE CO',44,'D','DA','FI',null),
 (21900496,'CHOLLET','QUENTIN','M','S','LYCEE GENERAL ET TECHNOLOGIQUE CITE TECH',86,'C','IA','FI','ENSI Caen'),
 (21900427,'CHRISTOPH','NIELS','M','STI2D','LYCEE POLYVALENT VAUBAN',29,'D',null,null,null),
 (21901954,'CLOAREC','THOMAS','M','S','LYCEE GEN TECH PRIVE NOTRE DAME DU VOEU',56,'A','IA','App','CentraleSupelec'),
 (21901457,'COBAT','GUILLAUME','M','S','LPO PRIVE DES METIERS INSTITUTION ST MAL',35,'C','DA','FI','Licence info Vannes'),
 (21900537,'COMBRISSON','EWEN','M','S','LYCEE GENERAL ET TECHNOLOGIQUE JEAN BRIT',35,'C','IA','FI','ESIEA Laval'),
 (21900563,'CORBEAU','REMI','M','S','LYCEE BENJAMIN FRANKLIN (AURAY)',56,'D',null,null,null),
 (21900448,'COSNIER','QUENTIN','M','S','LYCEE GENERAL ET TECHNOLOGIQUE COLBERT D',72,'D','IA','FI','Licence info Vannes'),
 (21902355,'DE LA TULLAYE','ADRIEN','M','S','LYCEE GEN ET TECHNOL PRIVE ST LOUIS LORI',29,'B',null,null,null),
 (21900262,'DELAUNAY','GURWAN','M','S','LPO LYCEE DES METIERS BROCELIANDE',56,'C','IA','FI','ENSSAT Lannion'),
 (21901481,'DESMONTS','LEO','M','S','LYCEE POLYVALENT JEAN PERRIN',95,'B','IA','App','ENSIBS cyberdéfense'),
 (21900272,'DESORMEAUX-DELAUNAY','TOM','M','STI2D','LYCEE GENERAL ET TECHNOLOGIQUE BEAUMONT',35,'D','DA','FI','Travail'),
 (21900976,'DEWILDE','YOANN','M','S','LYCEE GEN TECHNO LE LIKES',29,'D','DA','App','Licence info Vannes'),
 (21900698,'DIBERDER','EVAN','M','S','LYCEE GENERAL ET TECHNOLOGIQUE LESAGE',56,'A','IA','FI','Licence info Vannes'),
 (21902446,'DINEL','ROMAIN','M','S','LYCEE F.R de Chateaubriand COMBOURG - CO',35,'D','IA','FI','EPITECH Rennes'),
 (21900965,'DUFILS','ROMAIN','M','S','LYCEE GENERAL ET TECHNOLOGIQUE PIERRE ME',85,'D','IA','App','EPSI Nantes'),
 (21901046,'ERMINE','LOUIS','M','S','LYCEE GENERAL FRANCOIS RENE DE CHATEAUBR',35,'C','IA','FI','Travail'),
 (21901680,'FAUGERON','LUCAS','M','S','LYCEE POLYVALENT JEAN MACE',56,'A','DA','App','Licence Pro Vannes Delice'),
 (21902089,'FLOHIC','JORIS','M','S','LYCEE GENERAL GALILEE',44,'D','IA','App','EPSI Nantes'),
 (21900815,'FRANCIS','TEIVA','M','S','LYCEE BENJAMIN FRANKLIN (AURAY)',56,'D','DA','App',null),
 (21901869,'GARAUD','ORIANNE','F','S','LYCEE POLYVALENT PRIVE STE ANNE-ST LOUIS',56,'A','IA','FI',null),
 (21901634,'GARCIA','CHRISTOPHE','M','S','LPO LYCEE DES METIERS YVES THEPOT',29,'A','DA','FI','Licence Info Brest'),
 (21903624,'GARIN-HAMELINE','GILDAS','M','S','LYCEE GEN.ET TECHNOL.PRIVE ASSOMPTION BE',69,'D','IA','App','ENSIBS cyberdéfense'),
 (21902979,'GARREC','MEVEN','M','STI2D','LPO LYCEE DES METIERS YVES THEPOT',29,'A','DA','FI','Licence Pro Vannes Delice'),
 (21900989,'GASTEBOIS','LUCY','F','ES','LYCEE GENERAL ET TECHNOLOGIQUE SEVIGNE',35,'B','DA','FI','ESNA'),
 (21902450,'GLORENNEC','OSCAR','M','S','LYCEE GENERAL ET TECHNOLOGIQUE JOLIOT-CU',35,'A',null,null,null),
 (21900316,'GODET','LOUIS-XAVIER','M','STI2D','LYCEE TECHNOLOGIQUE PRIVE LES RIMAINS',35,'A','IA','FI','Licence Info Poitiers'),
 (21902833,'GONTARD','ALICE','F','S','LYCEE GENERAL ET TECHNO DE CORNOUAILLE',29,'B','IA','FI','ESNA'),
 (21903180,'GOYER','ENZO','M','S','LYCEE GEN.ET TECHNOL.PRIVE DAVESNIERES',53,'C',null,null,null),
 (21901478,'GREGOIRE','EWAN','M','S','LYCEE METIERS MARCELIN BERTHELOT (QUEST',56,'B','IA','App','Licence Pro Vannes Cyber'),
 (21900144,'GUENNEC','PAUL','M','S','LYCEE GEN. TECHNO VICTOR HUGO (HENNEBONT',56,'D','IA','FI','ENSIBS cyberdéfense'),
 (21903367,'GUENNOU','NICOLAS','M','STI2D','LYCEE GEN ET TECHNOL PRIVE ST SEBASTIEN',90,'D',null,null,null),
 (21902502,'GUILLOU','DORYAN','M','S','LYCEE GENERAL AUGUSTE BRIZEUX',29,'B',null,null,null),
 (21900788,'GUILLOUZO','BENJAMIN','M','S','LYCEE GENERAL PRIVE ND DE TOUTES AIDES',44,'B','IA','App','ENSIBS cyberdéfense'),
 (21903081,'HAMON','CLEMENT','M','S','LYCEE GEN ET TECHNOL PRIVE ST MARTIN',35,'C','IA','FI','EPITECH Rennes'),
 (21902069,'HERARD','THIBAULT','M','S','LYCEE GENERAL ET TECHNOLOGIQUE RENE DESC',35,'B','IA','App','ENSIBS cyberdéfense'),
 (21802862,'JOSSE','ELOUAN','M','S','LPO LYCEE DES METIERS COLBERT',56,'D','DA','FI',null),
 (21900318,'JOSSE','SAMUEL','M','S','LYCEE GENERAL ET TECHNOLOGIQUE MAUPERTUI',35,'C','DA','FI','Licence info Vannes'),
 (21903258,'KERSULLEC','ANTOINE','M','S','LYCEE GEN ET TECHNO DE KERNEUZEC',29,'A','IA','FI','Licence 2 info Rennes'),
 (21901901,'KOENIGS','THEO','M','S','LYCEE GENERAL ET TECHNOLOGIQUE JACQUES D',37,'A','IA','App','UTT'),
 (21903040,'LAGUE','PIERRE','M','S','LYCEE GENERAL FRANCOIS RENE DE CHATEAUBR',35,'C','IA','FI','Licence info Vannes'),
 (21906101,'LALIMAN','SIMON','M','S','LYCEE GENERAL ET TECHNOLOGIQUE MARCEL PA',null,'C',null,null,null),
 (21900878,'LAMBERT','TANGUY','M','S','LYCEE GEN ET TECHNOL PRIVE ST PAUL',56,'C','IA','App','Licence info Rennes'),
 (21903405,'LAROQUE','ARTHUR','M','S','LYCEE GEN. ET TECHNOL. PRIVE GERMAIN',50,'D','IA','FI','Licence info Nantes'),
 (21903763,'LE BORGNE','ANTOINE','M','STI2D','LPO LYCEE DES METIERS COLBERT',56,'B','DA','FI','Licence Pro Vannes Delice'),
 (21902012,'LE BOT','KILLIAN','M','STI2D','LY ST JOSEPH VANNES',56,'A',null,null,null),
 (21900924,'LE BRETON','DAN','M','S','LYCEE GEN ET TECHNOL PRIVE ST PAUL',56,'B','DA','App',null),
 (21901106,'LE CHENADEC','SARAH','F','S','LYCEE GEN. TECHNO VICTOR HUGO (HENNEBONT',56,'A','IA','FI','ENSIBS cyberdéfense'),
 (21900900,'LE DOUSSAL','RIWAN','M','S','LYCEE GEN. TECHNO VICTOR HUGO (HENNEBONT',56,'C',null,null,null),
 (21900858,'LE GARREC','VINCENT','M','S','LYCEE GEN. TECHNO VICTOR HUGO (HENNEBONT',56,'A','IA','FI','ENSIBS cyberdéfense'),
 (21900875,'LE GOFF','BENJAMIN','M','S','LYCEE GEN ET TECHNO DE KERNEUZEC',29,'A','DA','FI','Travail'),
 (21900911,'LE LAN','QUENTIN','M','S','LYCEE POLYVALENT PRIVE STE ANNE-ST LOUIS',56,'B','IA','FI','ESIR'),
 (21900219,'LE PORS','YANIS','M','S','LYCEE POLYVALENT PAUL SERUSIER',29,'A','IA','App','Licence info Brest'),
 (21903169,'LEBOEUF','SULLLIVAN','M','STI2D','LYCEE JEANNE DARC ST IVY',56,'A','DA','FI','Licence Pro Vannes Delice'),
 (21807519,'LEBORGNE','AYMERICK','M','S','LYCEE GENERAL ET TECHNOLOGIQUE JOLIOT-CU',56,'A','IA','App','Licence info Rennes'),
 (21901089,'LEDOUX','MATTEO','M','S','LGT LYCEE DES METIERS MARIE CURIE',60,'A','IA','FI','ESNA'),
 (21908182,'LEDREAU','ERIN','M',null,null,null,'B',null,null,null),
 (21900432,'LELEUX','KIERAN','M','S','LYCEE POLYVALENT JEAN MACE',56,'B',null,null,null),
 (21900834,'LENOBLE','ALEXANDRE','M','S','LYCEE GEN ET TECHNOL PRIVE ST PAUL',56,'A','IA','FI','Licence info Vannes'),
 (21902830,'LEVEL','VALENTIN','M','STI2D','LYCEE GEN ET TECHNOL PRIVE ST SEBASTIEN',29,'D',null,null,null),
 (21901136,'LICKINDORF','BORIS','M','S','LYCEE GENERAL ET TECHNOLOGIQUE JOLIOT-CU',35,'C','IA','App','ENSIBS cyberdéfense'),
 (21902778,'LIGER','VICTOR','M','STI2D','LYCEE GEN TECHNO LE LIKES',29,'B',null,null,null),
 (21900162,'MADELAINE','DYLAN','M','S','LYCEE GEN ET TECHNOL PRIVE ST SAUVEUR',35,'C','DA','App','Licence info Vannes'),
 (21900748,'MAHEO','CORENTIN','M','S','LYCEE GENERAL ET TECHNOLOGIQUE JOSEPH LO',56,'D','IA','FI','Travail'),
 (21901709,'MAINDRON','ELOUAN','M','STI2D','LYCEE POLYVALENT LEONARD DE VINCI',85,'C','DA','FI','Bachelor GL  CESI Nantes'),
 (21901009,'MEAUZE','BAPTISTE','M','S','LYCEE GENERAL ET TECHNOLOGIQUE RENE DESC',35,'C','IA','FI','Licence info Rennes'),
 (21900422,'MERIC-PONS','MATHIS','M','S','LPO LYCEE DES METIERS COLBERT',72,'C','IA','FI','Licence info Brest'),
 (21902602,'MESNAGE','MARIUS','M','STI2D','LYCEE TECHNOLOGIQUE PRIVE LES RIMAINS',35,'C',null,null,null),
 (21900482,'MICHENEAU','TIMOTHE','M','S','LYCEE GENERAL GALILEE',44,'A','DA','FI','Licence Pro Vannes Delice'),
 (21901766,'MIONET','PIERRE','M','STI2D','LYCEE GENERAL ET TECHNOLOGIQUE FELIX LE',22,'D','IA','FI','ENSIBS cyberdéfense'),
 (21901272,'MULTAN','GABRIELLE','F','S','LYCEE GEN ET TECHNOL PRIVE ST PAUL',56,'B','IA','FI','ESIEA Laval'),
 (21901069,'PEDRON','MATISSE','M','S','LYCEE GEN ET TECHNOL PRIVE ST LOUIS LORI',56,'D','IA','FI','ENSIBS cyberlogiciel'),
 (21900234,'PLOQUIN','NATHAN','M','S','LY ST JOSEPH VANNES',56,'C','IA','App','ENSIBS cyberdéfense'),
 (21902387,'PLOUZENNEC','MORGAN','M','STI2D','LYCEE GEN TECHNO LE LIKES',29,'C',null,null,null),
 (21900466,'QUACH','VALENTIN','M','STI2D','LYCEE POLYVALENT DE LA FONTAINE DES EAUX',22,'C','DA','FI','Travail'),
 (21900244,'QUINIOU','CHRISTOPHE','M','STI2D','LPO LYCEE DES METIERS YVES THEPOT',29,'C','DA','FI','Licence MIAGE Rennes'),
 (21900078,'RECOLIN','ANGELE','F','S','LYCEE POLYVALENT LES BOURDONNIERES',44,'B','DA','FI','Licence Pro Nantes'),
 (21901881,'RIAT','PAUL','M','STI2D','LYCEE GEN TECHNO LE LIKES',29,'B',null,null,null),
 (21902206,'ROUILLIER','JULIEN','M','S','LYCEE GENERAL ET TECHNOLOGIQUE MIREILLE ',60,'B','IA','App','ENSIBS cyberdéfense'),
 (21900626,'SAMSON','BENOIT','M','STI2D','LYCEE JEANNE DARC ST IVY',56,'A',null,null,null),
 (21900343,'SIMAR','CEDRIC','M','S','LYCEE GENERAL PRIVE ST FRANCOIS-XAVIER',56,'D','IA','FI','Bachelor Info De vinci'),
 (21900864,'SUARD','MAEL','M','S','LYCEE GEN TECHNO LE LIKES',29,'D','IA','App','IMT'),
 (21900238,'TIRLEMONT','KIERIAN','M','S','LYCEE GENERAL ET TECHNOLOGIQUE JULLIOT D',50,'D','IA','App','ESIEA Laval'),
 (21901836,'TRETON','PIERRE','M','S','LY.GEN CHARLES PEG GORGES',44,'A','DA','FI','Licence 2 Info Rennes'),
 (21702527,'VIAUD','BENJAMIN','M','S','LYCEE GEN TECH PRIVE NOTRE DAME DU VOEU',29,'C','IA','FI','Licence Info Rennes');

--  ENTREPRISE

INSERT INTO ENTREPRISE VALUES
 (1,'ACCENTURE',75),
 (2,'ADAPEI56',56),
 (3,'ADM',56),
 (4,'ALADOM',35),
 (5,'ANVERGUR',56),
 (6,'APRAS',35),
 (7,'AWAHOTO',35),
 (8,'BIOTHOT',56),
 (9,'BYSTAMP',56),
 (10,'CADIOU INDUSTRIE',29),
 (11,'CAISSE DEPARGNE LOIRE DROME ARDECHE',42),
 (12,'CGI',56),
 (13,'CGI',35),
 (14,'CMB',29),
 (15,'CNRS',35),
 (16,'COLLEGE SACRE COEUR',56),
 (17,'CONSEIL DEPARTEMENTAL DU MORBIHAN',56),
 (18,'CREDIT AGRICOLE',56),
 (19,'DAVID SYSTEMS GMBH',null),
 (20,'DAWIZZ',56),
 (21,'DCODE-IT',27),
 (22,'DEPARTEMENT DE LA VENDEE',85),
 (23,'DEPARTEMENT DILLE-ET-VILAINE',35),
 (24,'DIOTAL SAS',56),
 (25,'DGFIP',35),
 (26,'DIRISI',29),
 (27,'ÉCOLES DE SAINT-CYR COËTQUIDAN',56),
 (28,'ENSIBS',56),
 (29,'ERCII',56),
 (30,'EXOTIC ANIMALS',56),
 (31,'GROUPE ROCHER',35),
 (32,'HIPPOCAD',77),
 (33,'HORIZON SYSTEMES DINFORMATION',60),
 (34,'IDEMIA',95),
 (35,'IFREMER',29),
 (36,'INPIXAL',35),
 (37,'INRAE ET FN3PT',75),
 (38,'IOLE SOLUTIONS',56),
 (39,'IRISA, EQUIPE OBELIX',56),
 (40,'JEXLPROD',56),
 (41,'JULIANA MULTIMEDIA',56),
 (42,'KARROAD',65),
 (43,'LA BIDOUILLERIE',35),
 (44,'LACTALIS',53),
 (45,'LEAD OFF',56),
 (46,'LETSEAT',87),
 (47,'LUNION DES FORGERONS',91),
 (48,'MAIRIE DE SÉNÉ',56),
 (49,'MENBAT',56),
 (50,'MONSTOCK',51),
 (51,'NAVAL GROUP',83),
 (52,'NIJI',44),
 (53,'NIRYO',59),
 (54,'NOVOSYS INGENIERIE',88),
 (55,'OPTIC PERFORMANCE',56),
 (56,'ORANGE CYBERDÉFENSE',92),
 (57,'OUEST BOISSON',56),
 (58,'PLASTEOL',56),
 (59,'PORT DE PÊCHE KEROMAN',56),
 (60,'SCM VIAUD-FORMAGNE',56),
 (61,'SEM LORIENT KEROMAN',56),
 (62,'SIB',35),
 (63,'SITIA',44),
 (64,'SMARTMOOV',35),
 (65,'SMURFIT KAPPA FRANCE',56),
 (66,'SOLIDR',56),
 (67,'UBS',56),
 (68,'VIDEOR INFORMATIQUE',56),
 (69,'WINNOVE MEDICAL',35),
 (70,'WISEBAND',85),
 (71,'YOGOKO',35);


--  Table STAGIAIRE

INSERT INTO STAGIAIRE  VALUES 
 (21900265,37),
 (21901052,53),
 (21902500,2),
 (21903032,15),
 (21901464,23),
 (21900169,30),
 (21900347,47),
 (21901620,15),
 (21900755,18),
 (21901179,66),
 (21902463,18),
 (21900839,28),
 (21903410,14),
 (21900496,36),
 (21901457,42),
 (21900537,31),
 (21900448,29),
 (21900262,46),
 (21900834,3),
 (21900272,66),
 (21900698,17),
 (21902446,6),
 (21901046,32),
 (21901869,28),
 (21901634,50),
 (21902979,19),
 (21900989,34),
 (21900316,10),
 (21902833,11),
 (21900144,39),
 (21903081,24),
 (21802862,12),
 (21900318,38),
 (21903258,15),
 (21903040,1),
 (21903405,21),
 (21903763,61),
 (21901106,38),
 (21908182,55),
 (21900858,9),
 (21900875,19),
 (21900911,41),
 (21903169,65),
 (21901089,33),
 (21900748,16),
 (21901709,22),
 (21901009,44),
 (21900422,12),
 (21900482,29),
 (21901766,51),
 (21901272,20),
 (21901069,12),
 (21900466,58),
 (21900244,30),
 (21900078,1),
 (21900343,12),
 (21901836,63),
 (21702527,60);

--  Table APPRENTI

INSERT INTO APPRENTI  VALUES
 (21807519,5,'RF'),
 (21901901,7,'MM'),
 (21901680,12,'PT'),
 (21901136,25,'PB'),
 (21900282,26,'SL'),
 (21901463,27,'PB'),
 (21901650,35,'SL'),
 (21900878,38,'JFK'),
 (21901954,40,'PB'),
 (21903624,40,'PB'),
 (21900924,48,'MM'),
 (21900219,49,'MM'),
 (21900162,49,'MM'),
 (21902206,56,'MM'),
 (21901478,57,'JFK'),
 (21900754,59,'HT'),
 (21902069,62,'PT'),
 (21900238,64,'MM'),
 (21902089,67,'RF'),
 (21900864,68,'PB'),
 (21901481,69,'HT'),
 (21900788,69,'HT'),
 (21900965,70,'PB'),
 (21900234,71,'MM');

 -- Table Responsabilite
 INSERT INTO Responsabilite VALUES 
 ('admin','LN'),
 ('stages','JFK'),
 ('apprentis','PB'),
 ('poursuite_etudes','RF'),
 ('chef_dept','FME'),
 ('direction_edudes','RF');

 

-- QUESTION 4

SELECT nomEns, prenomEns
FROM Enseignant, groupeInfo1
WHERE idEns = tuteurGroupe
ORDER BY nomEns, prenomEns;


/*
4 Tuples
# nomEns, prenomEns
'Godin', 'Thibault'
'Naert', 'Lucie'
'Tonin', 'Philippe'
'Tuffigo', 'Helene'
*/

-- Avec sous requetes

SELECT nomEns, prenomEns
FROM Enseignant
WHERE idEns IN (Select tuteurGroupe
				FROM groupeInfo1)
ORDER BY nomEns, prenomEns;


/*
4 Tuples
# nomEns, prenomEns
'Godin', 'Thibault'
'Naert', 'Lucie'
'Tonin', 'Philippe'
'Tuffigo', 'Helene'
*/

-- QUESTION 5 (On doit obtenir 2 Tuples)

SELECT DISTINCT nomEns
FROM Enseignant, groupeInfo1, Apprenti
WHERE idEns = tuteurGroupe
AND idEns = tuteurApp
ORDER BY nomEns;


SELECT DISTINCT nomEns
FROM Enseignant, groupeInfo1, Apprenti
WHERE idEns IN (SELECT tuteurGroupe
				FROM groupeInfo1
				WHERE idEns IN(
				SELECT tuteurApp
				FROM Apprenti));

/*
2 Tuples
# nomEns
'Tonin'
'Tuffigo'
*/

-- QUESTION 6 (On doit obtenir 6 Tuples)
SELECT E.nomEntreprise
FROM Entreprise E
JOIN Apprenti A ON E.idEntreprise = A.entrepriseApp
WHERE A.tuteurApp = 'MM';


SELECT DISTINCT E.nomEntreprise
FROM Apprenti A
JOIN Entreprise E ON A.entrepriseApp = E.idEntreprise
JOIN Enseignant EN ON A.tuteurApp = EN.idEns
WHERE EN.idEns = 'MM';

/*
6 Tuples
# nomEntreprise
'MENBAT'
'YOGOKO'
'SMARTMOOV'
'MAIRIE DE SÉNÉ'
'AWAHOTO'
*/


-- QUESTION 7 (On doit obtenir 6 Tuples)
SELECT nomApprenti, prenomApprenti
FROM Apprenti A
JOIN Suit S ON A.idApprenti = S.idApprenti
JOIN Enseignant E ON S.idEns = E.idEns
WHERE E.nomEns = 'BAUDONT' AND E.prenomEns = 'Pascal';


SELECT nomApprenti, prenomApprenti
FROM Apprenti
WHERE idApprenti IN (
    SELECT idApprenti
    FROM Suit
    WHERE idEns IN (
        SELECT idEns
        FROM Enseignant
        WHERE nomEns = 'BAUDONT' AND prenomEns = 'Pascal'
    )
);



-- QUESTION 8 (On doit obtenir 2 Tuples)
SELECT DISTINCT T.nomTuteur
FROM Tuteur T
JOIN Suit S ON T.idTuteur = S.idTuteur
JOIN Apprenti A ON S.idApprenti = A.idApprenti
WHERE A.regionApprenti <> 'Bretagne';


SELECT DISTINCT nomTuteur
FROM Tuteur
WHERE idTuteur IN (
    SELECT idTuteur
    FROM Suit
    WHERE idApprenti IN (
        SELECT idApprenti
        FROM Apprenti
        WHERE regionApprenti <> 'Bretagne'
    )
);


-- QUESTION 9 (On doit obtenir 58 Tuples)
SELECT DISTINCT etudStagiaire, nomEntreprise, depEntreprise
FROM Stagiaire
JOIN Entreprise ON entrepriseStage = idEntreprise;


SELECT DISTINCT etudStagiaire, nomEntreprise, depEntreprise
FROM Stagiaire, Entreprise
WHERE entrepriseStage = idEntreprise;

/*
58 Tuples
# etudStagiaire, nomEntreprise, depEntreprise
'21702527', 'SCM VIAUD-FORMAGNE', '56'
'21802862', 'CGI', '56'
'21900078', 'ACCENTURE', '75'
'21900144', 'IRISA, EQUIPE OBELIX', '56'
'21900169', 'EXOTIC ANIMALS', '56'
*/

-- QUESTION 10 (On doit obtenir 58 Tuples)
SELECT DISTINCT nomEtud, prenomEtud, nomEntreprise, depEntreprise
FROM Etudiant
JOIN Entreprise ON entrepriseStage = idEntreprise
ORDER BY nomEtud, prenomEtud;

/*
58 Tuples
# etudStagiaire, nomEntreprise, depEntreprise
'21900078', 'ACCENTURE', '75'
'21903040', 'ACCENTURE', '75'
'21902500', 'ADAPEI56', '56'
'21900834', 'ADM', '56'
'21902446', 'APRAS', '35'
*/

-- QUESTION 11 (On doit obtenir 31 Tuples)


-- Jointure Relationnel
SELECT DISTINCT nomEtud, prenomEtud
FROM Stagiaire, Etudiant, Entreprise
WHERE idEtud = etudStagiaire
AND entrepriseStage = idEntreprise
AND depEntreprise != depLycee
ORDER BY nomEtud, prenomEtud;

/* 31 Tuples
# nomEtud, prenomEtud
'AUVRAY', 'ALEXANDRE'
'BEAUCLAIR', 'ADRIEN'
'BERNIER', 'ALLAN'
'BONNET', 'BENJAMIN'
'BRAUD', 'ANTOINE'
*/

-- QUESTION 12 (On doit obtenir 38 Tuples) UQ Externe
SELECT nomEns, nomEtud, prenomEtud
FROM Enseignant
LEFT JOIN Apprenti ON tuteurApp = idEns
	LEFT JOIN Etudiant ON idEtud = etudApp
ORDER BY nomEns, nomEtud, prenomEtud;

/*
38 Tuples
# nomEns, nomEtud, prenomEtud
'Baudont', 'BREIT HOARAU', 'EMELINE'
'Baudont', 'CLOAREC', 'THOMAS'
'Baudont', 'DUFILS', 'ROMAIN'
'Baudont', 'GARIN-HAMELINE', 'GILDAS'
'Baudont', 'LICKINDORF', 'BORIS'
*/

-- QUESTION 13 (On doit obtenir 107 Tuples) UQ Externe
SELECT nomEtud, nomEntreprise
FROM Etudiant
	LEFT JOIN Apprenti ON idEtud = etudApp
		LEFT JOIN Stagiaire ON idEtud = etudStagiaire
			LEFT JOIN Entreprise ON (idEntreprise = entrepriseStage OR idEntreprise = entrepriseStage)
ORDER BY nomEtud, nomEntreprise;

/*
107 Tuples


# nomEtud, nomEntreprise
'ADAM', NULL
'AJELLO', NULL
'ARTAUD', NULL
'AUVRAY', 'INRAE ET FN3PT'
'BEAUCLAIR', 'NIRYO'
*/

--Question 14--

SELECT nomEns, nomEtud, prenomEtud, nomEntreprise
FROM Enseignant
LEFT JOIN Apprenti ON tuteurApp = idEns
    LEFT JOIN Etudiant ON etudApp = idEtud
        LEFT JOIN Entreprise ON idEntreprise = entrepriseApp;

/*
38 Tuples
# nomEns, nomEtud, prenomEtud, nomEntreprise
'Lemaitre', NULL, NULL, NULL
'Lesueur', NULL, NULL, NULL
'Merciol', NULL, NULL, NULL
'Kerbellec', NULL, NULL, NULL
'Tuffigo', 'DESMONTS', 'LEO', 'WINNOVE MEDICAL'
*/

-- Question 15--

SELECT nomEns, nomEtud, prenomEtud, depEntreprise
FROM Enseignant
LEFT JOIN Apprenti ON tuteurApp = idEns
    LEFT JOIN Etudiant ON idEtud = etuApp
        LEFT JOIN Entreprise ON idEntreprise = entrepriseApp
WHERE depEntreprise IN (22,29,56,35) OR depEntreprise IS NULL
ORDER BY depEntreprise DESC, nomEns, nomEtud, prenomEtud;

/*
36 Tuples

# nomEns, nomEtud, prenomEtud, depEntreprise
'Baudont', 'BREIT HOARAU', 'EMELINE', '56'
'Baudont', 'CLOAREC', 'THOMAS', '56'
'Baudont', 'GARIN-HAMELINE', 'GILDAS', '56'
'Baudont', 'SUARD', 'MAEL', '56'
'Fleurquin', 'FLOHIC', 'JORIS', '56'
*/