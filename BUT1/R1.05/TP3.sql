-- ======================== Script de création de tables pour le TP3 (cf. TP2, exercice 1)
/* 
Enseignant( nomEns (1), prenomEns, adresse, statut )  
Cycle( num (1), enseignantResponsable = @Enseignant.nomEns (UQ)(NN) )
Cours( nomCours (1), volumeH, lEnseignant = @Enseignant.nomEns (NN), leCycle = @Cycle.num (NN) ) 
Requiert( [ cours = @Cours.nomCours, coursRequis = @Cours.nomCours ](1) )
*/

/* QUESTION 1 : Voici le script de cr�ation de table */
DROP TABLE Requiert ;
DROP TABLE Cours ;
DROP TABLE Cycle ; 
DROP TABLE Enseignant ;

CREATE TABLE Enseignant
	(
	nomEns VARCHAR2(20)
		CONSTRAINT pk_Enseignant PRIMARY KEY,	
	prenomEns VARCHAR2(20),
	adresse VARCHAR2(40),
	statut VARCHAR2(20)
	)
;

CREATE TABLE Cycle
	(
	num NUMBER
		CONSTRAINT pk_Cycle PRIMARY KEY,	
	enseignantResponsable VARCHAR2(20) 
		CONSTRAINT fk_Cycle_Enseignant REFERENCES Enseignant(nomEns)
		CONSTRAINT uq_enseignantResponsable UNIQUE
		CONSTRAINT nn_enseignantResponsable NOT NULL
	)
;

CREATE TABLE Cours
	(
	nomCours VARCHAR2(20)
		CONSTRAINT pk_Cours PRIMARY KEY,	
	volumeH NUMBER,
	lEnseignant VARCHAR2(20)
		CONSTRAINT fk_Cours_Enseignant REFERENCES Enseignant(nomEns)
		CONSTRAINT nn_lEnseignant NOT NULL,	
	leCycle NUMBER
		CONSTRAINT fk_Cours_Cycle REFERENCES Cycle(num)	
		CONSTRAINT nn_leCycle NOT NULL
	)
;

CREATE TABLE Requiert
	(
	cours VARCHAR2(20)
		CONSTRAINT fk_Requiert_Cours REFERENCES Cours(nomCours), 
	coursRequis VARCHAR2(20)
		CONSTRAINT fk_Requiert_CoursRequis REFERENCES Cours(nomCours), 
	CONSTRAINT pk_Requiert PRIMARY KEY (cours, coursRequis)
	)
;

/*QUESTION 3 : */
DELETE FROM Requiert;
DELETE FROM Cours;
DELETE FROM Cycle;
DELETE FROM Enseignant;

/*QUESTION 2 : Voici le script pour inserer des lignes*/

INSERT INTO Enseignant VALUES
('PHAM', 'MT', NULL, 'Enseignant-Chercheur');

INSERT INTO Enseignant VALUES
('NAERT', 'Lucie', NULL, 'Enseignant');

INSERT INTO Enseignant VALUES
('KAMP', 'Jean-Fran�ois', NULL, 'Enseignant');

INSERT INTO Enseignant VALUES
('TUFFIGO', 'H�l�ne', NULL, 'Enseignant');

INSERT INTO Enseignant VALUES
('LEMAITRE', 'Elodie', NULL, 'Enseignant');

INSERT INTO Cycle VALUES
(2, 'PHAM');

INSERT INTO Cycle VALUES
(1, 'NAERT');

INSERT INTO Cycle VALUES
(3, 'KAMP');

INSERT INTO Cours VALUES
('Developpement', 5, 'KAMP', 2);

INSERT INTO Cours VALUES
('Economie', 1, 'LEMAITRE', 1);

INSERT INTO Cours VALUES
('Communication', 1, 'TUFFIGO', 3);

INSERT INTO Requiert VALUES
('Developpement', 'Economie');

INSERT INTO Requiert VALUES 
('Economie', 'Communication');

INSERT INTO Requiert VALUES
('Communication', 'Developpement');

/*QUESTION 4 : */
/*A*/
INSERT INTO Enseignant VALUES
(NULL, 'Lucie', NULL, 'Enseignant');

/*La contrainte fonctionne bien on obtient cette erreur
Rapport d'erreur -
ORA-01400: cannot insert NULL into ("OMER"."ENSEIGNANT"."NOMENS")
*/

/*B*/
INSERT INTO Enseignant VALUES
('NAERT', 'Lucie', NULL, 'Enseignant');

INSERT INTO Enseignant VALUES
('NAERT', 'Lucie', NULL, 'Enseignant');

/* La contrainte fonctionne bien on obtient cette erreur
Rapport d'erreur -
ORA-00001: unique constraint (OMER.PK_ENSEIGNANT) violated
*/

/*C*/
INSERT INTO  Cycle VALUES 
(1, NULL);
/*
Contrainte d'existence fonctionnelle
Rapport d'erreur -
ORA-01400: cannot insert NULL into ("OMER"."CYCLE"."ENSEIGNANTRESPONSABLE")
*/


INSERT INTO Cycle VALUES
(1, 'PHAM');
/*
Contrainte d'unicit� fonctionnelle
Rapport d'erreur -
ORA-00001: unique constraint (OMER.PK_CYCLE) violated
*/

/*QUESTION 5 : */
/*A*/
ALTER TABLE Cours
ADD CONSTRAINT ck_valeurPositif CHECK (volumeH > 0);
/*L'avantage de cette m�thode permet d'ajouter des contraintes sans avoir
a supprimer puis recr�er une table. */

/*B*/
INSERT INTO Cours VALUES
('Developpement', -1, 'KAMP');
/*On obtient bien une erreur pour la contrainte qu'on vient d'ajouter :
Erreur SQL : ORA-00947: not enough values
00947. 00000 -  "not enough values" */

/*C*/
ALTER TABLE Cours
DROP CONSTRAINT ck_valeurPositif;
/*Sortie de Script : " Table COURS modifi�(e)." */

/*QUESTION 6 : */
/*AJOUT*/
ALTER TABLE Cours
ADD jour VARCHAR2(20);
/*Sortie de Script : " Table COURS modifi�(e)." */

/*SUPPRESSION*/
ALTER TABLE Cours
DROP COLUMN jour;
/*Sortie de Script : " Table COURS modifi�(e)." */

/*QUESTION 7 : */
UPDATE Enseignant
SET prenomEns = 'MT'
where nomEns = 'PHAM';
/*Sortie de Script : "1 ligne mis a jour." */

DELETE Requiert
where cours = 'Communication';
/*Sortie de Script : "1 ligne supprim�." */












