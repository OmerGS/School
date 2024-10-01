/*
SAE Communes Bretonne
Partie 2 BDD
PERON Romain
GUNES Omer
CAMPION Brayan
Groupe TP 1B1
*/

use communeBretonne;

/* 
Question 1: Jointure interne
Affiche les villes avec leur département
 */
SELECT nomCommune, nomDep
FROM Commune
	JOIN Departement ON leDepartement = idDep;


/*
1000 tuples
# nomCommune, nomDep
'ALLINEUC', 'COTES-D\'ARMOR'
'ANDEL', 'COTES-D\'ARMOR'
'AUCALEUC', 'COTES-D\'ARMOR'
'BEGARD', 'COTES-D\'ARMOR'
'BELLE-ISLE-EN-TERRE', 'COTES-D\'ARMOR'
*/

/* 
Question 2: Auto-Jointure
Quel sont les voisins de Vannes
 */
SELECT c2.nomCommune
FROM Voisinage
	JOIN Commune c1 ON commune = c1.idCommune
		JOIN Commune c2 ON communeVoisine = c2.idCommune
WHERE UPPER(c1.nomCommune) = 'VANNES';

/*
7 tuples
# nomCommune
'ARRADON'
'PLESCOP'
'PLOEREN'
'SAINT-AVE'
'SAINT-NOLFF'
'SENE'
*/

/*
Question 3: Jointure externe droite
Pour chaque ville afficher sa gare si elle en possèdent une
*/

SELECT DISTINCT nomCommune, nomGare
FROM Gare
	RIGHT JOIN Commune ON laCommune = idCommune;
    
/*
1000 tuples
# nomCommune, nomGare
'ALLINEUC', NULL
'ANDEL', NULL
'AUCALEUC', NULL
'BEGARD', NULL
'BELLE-ISLE-EN-TERRE', NULL

*/


/*
Question 4: Jointure externe gauche
Affiche paur chaque aeroport si il a ou non un departement
*/

SELECT nom, nomDep
FROM Aeroport
	LEFT JOIN Departement ON leDepartement = idDep;
/*
8 tuples
# nom, nomDep
'LANNION', 'COTES-D\'ARMOR'
'SAINT-BRIEUC-ARMOR', 'COTES-D\'ARMOR'
'BREST-BRETAGNE', 'FINISTERE'
'MORLAIX-PLOUJEAN', 'FINISTERE'
'QUIMPER-PLUGUFFAN', 'FINISTERE'


*/
/*
Question 5: Sous-requete aven IN
Affiche les ville qui ont pour département Finistere et qui ont une gare
*/

SELECT nomCommune
FROM Commune
WHERE idCommune IN (SELECT laCommune 
					FROM Gare
					WHERE leDepartement = 29
                    );
                    
/*
23 tuples 
# nomCommune
'BANNALEC'
'BREST'
'CARHAIX-PLOUGUER'
'CHATEAULIN'
'CONCARNEAU'
'LA FOREST-LANDERNEAU'

*/

/*
Question 6: Sous-requete aven NOT IN
Affiche les aéroports qui ne sont pas dans le Morbihan
*/

SELECT nom
FROM Aeroport
WHERE leDepartement NOT IN (SELECT idDep 
					FROM Departement
					WHERE leDepartement = 56
                    );
                    
/*
7 tuples
# nom
'LANNION'
'SAINT-BRIEUC-ARMOR'
'BREST-BRETAGNE'
'MORLAIX-PLOUJEAN'
'QUIMPER-PLUGUFFAN'

*/

/*
Question 7: Sous-requete avec EXISTS
Quelles sont les villes qui ont plus de 25 000 habitants ?
*/
SELECT nomCommune
FROM Commune c
WHERE EXISTS (
    SELECT nomCommune
    FROM DonneesAnnuelles 
    WHERE laCommune = idCommune
    AND population > 25000
);

/*
7 tuples 
# nomCommune
'SAINT-BRIEUC'
'BREST'
'QUIMPER'
'RENNES'
'SAINT-MALO'
*/

/*
Question 8: Sous-requete avec NOT EXISTS
Quelles sont les villes qui n'ont pas plus de 20 maisons vendu?
*/
SELECT nomCommune
FROM Commune 
WHERE NOT EXISTS (
    SELECT nomCommune
    FROM DonneesAnnuelles 
    WHERE laCommune = idCommune AND nbMaison > 20
);

/*
496 tuples
# nomCommune
'ALLINEUC'
'ANDEL'
'AUCALEUC'
'BERHET'
'LE BODEO'

*/


/*
Question 9: fonction de groupe sans regroupement
Quel sont les dépenses culturelles totale du Morbihan
*/
SELECT SUM(depensesCulturellesTotales) AS TotalDepensesCulturelles
FROM DonneesAnnuelles
	JOIN Commune ON idCommune = laCommune
WHERE leDepartement = 56;

/*
1 tuple
# TotalDepensesCulturelles
'112701'

*/


/*
Question 10: Fonction de groupe sans regroupement
Quel est la moyenne des prix moyen des habitats du Morbihan
*/
SELECT AVG(prixMoyen) AS PrixMoyenMoy
FROM DonneesAnnuelles
	JOIN Commune ON idCommune = laCommune
WHERE leDepartement = 56;

/*
1 tuple
# PrixMoyenMoy
'170887.75302773595'

*/

/*
Question 11 : regroupement avec fonction de groupe 
Quel est la population de chaque département
*/
SELECT leDepartement, SUM(population) AS PopulationTotale
FROM Commune 
	JOIN DonneesAnnuelles ON idCommune = laCommune
GROUP BY leDepartement;

/*
4 tuples
# leDepartement, PopulationTotale
'22', '569245'
'29', '1254647'
'35', '1481230'
'56', '957087'

*/

/*
Question 12 : regroupement avec fonction de groupe
Quel est la moyenne des prix moyen des maisons en focntion des année
*/
SELECT lAnnee, AVG(prixMoyen) AS MoyennePrixMoyen
FROM DonneesAnnuelles
GROUP BY lAnnee;

/*
4 tuples
# lAnnee, MoyennePrixMoyen
'2018', '141925.45741504474'
'2019', '145122.5320768229'
'2020', '158303.30966796874'
'2021', '177211.27047693948'


*/

/*
Question 13 : Regroupement et Restriction (HAVING)
Quel sont les départment qui investit plus de 3 milliard
*/
SELECT leDepartement, SUM(investissementCulturel2019) AS TotalInvestissement
FROM Commune
	JOIN Departement ON Commune.leDepartement = Departement.idDep
GROUP BY leDepartement
HAVING SUM(investissementCulturel2019) > 3000000000;

/*
2 tuples
# leDepartement, TotalInvestissement
'29', '3787359229'
'35', '8958225807'
*/

/*
Question 14 : Regroupement et Restriction (HAVING)
Quelle année on une moyenne de prix supérieur à 170000 euros
*/
SELECT lAnnee, AVG(prixMoyen) AS MoyennePrixMoyen
FROM DonneesAnnuelles
GROUP BY lAnnee
HAVING AVG(prixMoyen) > 170000;
/*
1 tuple
# lAnnee, MoyennePrixMoyen
'2021', '177211.27047693948'

*/


/*
Question 15 :division (division normale)
Quel sont les ville ayant une gare qui prend du fret
*/
SELECT DISTINCT nomCommune
FROM Commune C1
WHERE NOT EXISTS (
    SELECT idCommune
    FROM Commune C2
    WHERE C1.idCommune = C2.idCommune
    EXCEPT
    SELECT laCommune
    FROM Gare
    WHERE estFret = 1
);

/*
# nomCommune
'GUINGAMP'
'LAMBALLE-ARMOR'
'LOCARN'
'LOUDEAC'
'PLAINTEL'

*/

/*
Question 16 : division (division exacte) 
Quel sont les ville ayant uniquemnt une gare qui prend du fret
*/
SELECT DISTINCT nomCommune
FROM Commune C1
WHERE NOT EXISTS (
    SELECT idCommune
    FROM Commune C2
    WHERE C1.idCommune = C2.idCommune
    EXCEPT
    SELECT laCommune
    FROM Gare
)
AND NOT EXISTS (
SELECT laCommune
    FROM Gare
    EXCEPT
    SELECT idCommune
    FROM Commune C2
    WHERE C1.idCommune = C2.idCommune
) ;


/*
0 tuple
N'affiche rien car une gare à forc"ment une ville d'agrégation
*/

/*
-- Question 17:  vue (pour g´erer des contraintes `a proposer) 
Affiche les donnée des ville en fonction des années
*/
-- DROP VIEW Vue_Donnees_Ville;


CREATE OR REPLACE VIEW Vue_Donnees_Ville AS
SELECT c.idCommune, c.nomCommune,d.nomDep,g.codeGare,g.nomGare,g.estFret,g.estVoyageur,da.lAnnee,da.nbMaison,da.nbAppart,da.prixMoyen,da.prixM2Moyen,da.SurfaceMoy,da.depensesCulturellesTotales,da.budgetTotal,da.population 
FROM Commune c
LEFT JOIN Departement d ON c.leDepartement = d.idDep
LEFT JOIN Gare g ON c.idCommune = g.laCommune
LEFT JOIN DonneesAnnuelles da ON c.idCommune = da.laCommune;

SELECT *
FROM Vue_Donnees_Ville;

/*
1000 tuples
# idCommune, nomCommune, nomDep, codeGare, nomGare, estFret, estVoyageur, lAnnee, nbMaison, nbAppart, prixMoyen, prixM2Moyen, SurfaceMoy, depensesCulturellesTotales, budgetTotal, population
'22001', 'ALLINEUC', 'COTES-D\'ARMOR', NULL, NULL, NULL, NULL, '2018', '3', '0', '80966.7', '650.333', '123.333', '-1', '-1', '-1'
'22001', 'ALLINEUC', 'COTES-D\'ARMOR', NULL, NULL, NULL, NULL, '2019', '8', '0', '62237.5', '733.375', '81.875', '-1', '-1', '-1'
'22001', 'ALLINEUC', 'COTES-D\'ARMOR', NULL, NULL, NULL, NULL, '2020', '7', '0', '148857', '1378.14', '113.429', '-1', '-1', '-1'
'22001', 'ALLINEUC', 'COTES-D\'ARMOR', NULL, NULL, NULL, NULL, '2021', '4', '0', '112343', '1082.5', '104', '-1', '-1', '-1'
'22002', 'ANDEL', 'COTES-D\'ARMOR', NULL, NULL, NULL, NULL, '2018', '15', '0', '162488', '1856.6', '96.5333', '-1', '-1', '-1'

*/
/*
Question 18: Vue pour gérer les contraintes à proposer
Affiche les communes qui ont une gare
*/
CREATE OR REPLACE VIEW CommunesAvecGare AS
SELECT nomCommune
FROM Commune c
WHERE EXISTS (SELECT * 
			FROM Gare g 
            WHERE g.laCommune = c.idCommune
            );
            
SELECT *
FROM CommunesAvecGare;

/*
120 tuples
# nomCommune
'BOURBRIAC'
'BROONS'
'CALLAC'
'CAULNES'
'DINAN'

*/

/*
Question 19: vue (pour g´erer des informations d´erivables `a proposer)
Créer une vue pour les investissements culturels par département
*/
CREATE OR REPLACE VIEW InvestissementsCulturels AS
SELECT idDep, nomDep, SUM(investissementCulturel2019) AS TotalInvestissement
FROM Departement
GROUP BY idDep, nomDep;

SELECT *
FROM InvestissementsCulturels;

/*
4 tuples
# idDep, nomDep, TotalInvestissement
'22', 'COTES-D\'ARMOR', '6196596'
'29', 'FINISTERE', '13672777'
'35', 'ILLE-ET-VILAINE', '26901579'
'56', 'MORBIHAN', '7107993'

*/


/*
Question 20: vue (pour g´erer des informations d´erivables `a proposer)
Créer une vue pour les informations des aéroports
*/

CREATE OR REPLACE VIEW Vue_Aeroport_Info AS
SELECT nom, adresse, nomDep
FROM Aeroport 
JOIN Departement ON leDepartement = idDep;


SELECT *
FROM Vue_Aeroport_Info;

/*
8 tuples
# nom, adresse, nomDep
'LANNION', 'Avenue Pierre Marzin LANNION 22300', 'COTES-D\'ARMOR'
'SAINT-BRIEUC-ARMOR', 'TREMON 22440', 'COTES-D\'ARMOR'
'BREST-BRETAGNE', 'GUIPAVAS 29490', 'FINISTERE'
'MORLAIX-PLOUJEAN', 'CCI de Morlaix - B.P. 6 MORLAIX 29201', 'FINISTERE'
'QUIMPER-PLUGUFFAN', 'PLUGUFFAN 29700', 'FINISTERE'
*/