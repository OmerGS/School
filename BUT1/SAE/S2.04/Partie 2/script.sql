/*
Script de Test des privilèges des utilisateurs
*/

use communeBretonne;

-- Test des privilèges pour l'utilisateur 'omer'

-- Connexion en tant qu'utilisateur 'omer'
-- (Utiliser l'interface de votre choix pour établir cette connexion)

-- Test de lecture sur la table Commune
SELECT * FROM Commune LIMIT 10;
/*
Résultat attendu : Affichage des premières 10 lignes de la table Commune
'22001', 'ALLINEUC', '22'
'22002', 'ANDEL', '22'
'22003', 'AUCALEUC', '22'
'22004', 'BEGARD', '22'
'22005', 'BELLE-ISLE-EN-TERRE', '22'
'22006', 'BERHET', '22'
'22008', 'BOBITAL', '22'
'22009', 'LE BODEO', '22'
'22011', 'BOQUEHO', '22'
'22012', 'LA BOUILLIE', '22'
*/

-- Test de création de table
CREATE TABLE TestTableOmer (id INT, name VARCHAR(50));
/*
Résultat attendu : 0 row(s) affected
*/

-- Test de suppression de table
DROP TABLE TestTableOmer;
/*
Résultat attendu : 0 row(s) affected
*/

-- Test d'insertion de données dans Departement
INSERT INTO Departement (idDep, nomDep, investissementCulturel2019) VALUES (18905, 'TestDep', 10000);
/*
Résultat attendu : 1 row(s) affected
*/

-- Test d'insertion de données dans Commune
INSERT INTO Commune (idCommune, nomCommune, leDepartement) VALUES (15000, 'TestCommune', 9999);
/*
Résultat attendu : 1 row(s) affected
*/

-- Test d'insertion de données dans Gare
INSERT INTO Gare (codeGare, nomGare, estFret, estVoyageur, laCommune) VALUES (9999, 'TestGare', true, false, 15000);
/*
Résultat attendu : 1 row(s) affected
*/

-- Test de mise à jour de données
UPDATE Gare SET nomGare = 'UpdatedGare' WHERE codeGare = 9999;
/*
Résultat attendu : 1 row(s) affected
*/

-- Test de suppression de données dans Gare
DELETE FROM Gare WHERE codeGare = 9999;
/*
Résultat attendu : 1 row(s) affected
*/

-- Suppression de l'entrée de test dans Commune
DELETE FROM Commune WHERE idCommune = 15000;
/*
Résultat attendu : 1 row(s) affected
*/

-- Suppression de l'entrée de test dans Departement
DELETE FROM Departement WHERE idDep = 9999;
/*
Résultat attendu : 1 row(s) affected
*/


-- Test des privilèges pour l'utilisateur 'romain'

-- Connexion en tant qu'utilisateur 'romain'

-- Test de lecture sur la table Gare
SELECT * FROM Gare LIMIT 10;
/*
Résultat attendu : Affichage des premières 10 lignes de la table Gare
'87159947', 'KER-LANN', '0', '1', '35047'
'87322347', 'TRAOU-NEZ', '0', '1', '22233'
'87334490', 'CESSON-SEVIGNE', '0', '1', '35051'
'87334508', 'LA POTERIE', '0', '1', '35238'
'87471003', 'RENNES', '1', '1', '35238'
'87471029', 'VERN', '0', '1', '35352'
'87471037', 'BRUZ', '0', '1', '35047'
'87471045', 'BETTON', '0', '1', '35024'
'87471052', 'ST-JACQUES-DE-LA-LANDE', '1', '1', '35281'
'87471060', 'L\'HERMITAGE-MORDELLES', '1', '1', '35131'
*/

-- Test de création de table
CREATE TABLE TestTableRomain (id INT, name VARCHAR(50));
/*
Résultat attendu : 0 row(s) affected
*/

-- Test de suppression de table
DROP TABLE TestTableRomain;
/*
Résultat attendu : 0 row(s) affected
*/

-- Test d'insertion de données dans Departement
INSERT INTO Departement (idDep, nomDep, investissementCulturel2019) VALUES (9998, 'TestDepRomain', 20000);
/*
Résultat attendu : 1 row(s) affected
*/

-- Test d'insertion de données dans Commune
INSERT INTO Commune (idCommune, nomCommune, leDepartement) VALUES (15001, 'TestCommuneRomain', 9998);
/*
Résultat attendu : 1 row(s) affected
*/

-- Test d'insertion de données dans Gare
INSERT INTO Gare (codeGare, nomGare, estFret, estVoyageur, laCommune) VALUES (9998, 'TestGareRomain', true, false, 15001);
/*
Résultat attendu : 1 row(s) affected
*/

-- Test de mise à jour de données
UPDATE Gare SET nomGare = 'UpdatedGareRomain' WHERE codeGare = 9998;
/*
Résultat attendu : 1 row(s) affected
*/

-- Test de suppression de données dans Gare
DELETE FROM Gare WHERE codeGare = 9998;
/*
Résultat attendu : 1 row(s) affected
*/

-- Suppression de l'entrée de test dans Commune
DELETE FROM Commune WHERE idCommune = 15001;
/*
Résultat attendu : 1 row(s) affected
*/

-- Suppression de l'entrée de test dans Departement
DELETE FROM Departement WHERE idDep = 9998;
/*
Résultat attendu : 1 row(s) affected
*/


-- Test des privilèges pour l'utilisateur 'brayan'

-- Connexion en tant qu'utilisateur 'brayan'

use communeBretonne;

-- Test de lecture sur la table Departement
SELECT * FROM Departement LIMIT 10;
/*
Résultat attendu : Affichage des premières 10 lignes de la table Departement
'22', 'COTES-D\'ARMOR', '6196596'
'29', 'FINISTERE', '13672777'
'35', 'ILLE-ET-VILAINE', '26901579'
'56', 'MORBIHAN', '7107993'
'18905', 'TestDep', '10000'
*/

-- Test de création de table (devrait échouer)
CREATE TABLE TestTableBrayan (id INT, name VARCHAR(50));
/*
Résultat attendu : Error Code: 1142. CREATE command denied to user 'brayan'@'localhost' for table 'TestTableBrayan'
*/

-- Test de suppression de table (devrait échouer)
DROP TABLE TestTableBrayan;
/*
Résultat attendu : Error Code: 1142. DROP command denied to user 'brayan'@'localhost' for table 'TestTableBrayan'
*/

-- Test d'insertion de données (devrait échouer)
INSERT INTO Departement (idDep, nomDep, investissementCulturel2019) VALUES (9997, 'TestDepBrayan', 30000);
/*
Résultat attendu : Error Code: 1142. INSERT command denied to user 'brayan'@'localhost' for table 'Departement'
*/

-- Test de mise à jour de données (devrait échouer)
UPDATE Departement SET nomDep = 'UpdatedDepBrayan' WHERE idDep = 9997;
/*
Résultat attendu : Error Code: 1142. UPDATE command denied to user 'brayan'@'localhost' for table 'Departement'
*/

-- Test de suppression de données (devrait échouer)
DELETE FROM Departement WHERE idDep = 9997;
/*
Résultat attendu : Error Code: 1142. DELETE command denied to user 'brayan'@'localhost' for table 'Departement'
*/


-- Test des privilèges pour l'utilisateur 'leandre'

-- Connexion en tant qu'utilisateur 'leandre'

-- Test de lecture sur la table Annee (devrait échouer)
SELECT * FROM Annee LIMIT 10;
/*
Résultat attendu : Error Code: 1142. SELECT command denied to user 'leandre'@'localhost' for table 'Annee'
'1991', '3.2'
'1992', '2.4'
'1993', '2.1'
'1994', '1.6'
'1995', '1.8'
'1996', '2'
'1997', '1.2'
'1998', '0.6'
'1999', '0.5'
'2000', '1.7'
*/

-- Test de création de table (devrait échouer)
CREATE TABLE TestTableLeandre (id INT, name VARCHAR(50));
/*
Résultat attendu : Error Code: 1142. CREATE command denied to user 'leandre'@'localhost' for table 'TestTableLeandre'
*/

-- Test de suppression de table (devrait échouer)
DROP TABLE TestTableLeandre;
/*
Résultat attendu : Error Code: 1142. DROP command denied to user 'leandre'@'localhost' for table 'TestTableLeandre'
*/

-- Test d'insertion de données (devrait échouer)
INSERT INTO Annee (annee, tauxInflation) VALUES (9999, 2.5);
/*
Résultat attendu : Error Code: 1142. INSERT command denied to user 'leandre'@'localhost' for table 'Annee'
*/

-- Test de mise à jour de données (devrait échouer)
UPDATE Annee SET tauxInflation = 3.0 WHERE annee = 9999;
/*
Résultat attendu : Error Code: 1142. UPDATE command denied to user 'leandre'@'localhost' for table 'Annee'
*/

-- Test de suppression de données (devrait échouer)
DELETE FROM Annee WHERE annee = 9999;
/*
Résultat attendu : Error Code: 1142. DELETE command denied to user 'leandre'@'localhost' for table 'Annee'
*/