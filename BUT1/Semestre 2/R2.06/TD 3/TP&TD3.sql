/*
TD3
*/

-- Question 1 : 

--------------------------------------
SELECT COUNT(*)
FROM Pilote;

/*
# COUNT(*)
'7'
*/

-- On obtient le même resultat
--------------------------------------

SELECT COUNT(compPil)
FROM Pilote;

/*
# COUNT(compPil)
'6'
*/

-- On Obtient le même resultat
--------------------------------------
SELECT compAv, COUNT(*)
FROM Avion
GROUP BY compAv;

/*
# compAv, COUNT(*)
'1', '3'
'2', '1'
'3', '1'
'4', '2'
'5', '2'
*/

-- On Obtient le même resultat mais dans un ordre differents
--------------------------------------

SELECT COUNT(DISTINCT compPil)
FROM Pilote;

/*
# COUNT(DISTINCT compPil)
'4'
*/

-- On Obtient le même resultat
--------------------------------------

SELECT MAX(nbHVol)
FROM Pilote;

/*
# MAX(nbHVol)
'3000'
*/

-- On obtient le meme resultat
--------------------------------------

SELECT AVG(nbHVol)
FROM Pilote;

/*
# AVG(nbHVol)
'1457.1429'
*/

-- On obtient le meme resultat
--------------------------------------

SELECT STDDEV(nbHVol)
FROM Pilote;

/*
# STDDEV(nbHVol)
'1029.0177603036832'

-- On Obtient le meme ordre de grandeur que le cours
*/
--------------------------------------v

SELECT nomComp, COUNT(DISTINCT leTypeAvion)
FROM Compagnie, Avion
WHERE idComp = compAv
GROUP BY nomComp
ORDER BY nomComp;

/*
# nomComp, COUNT(DISTINCT leTypeAvion)
'Air France', '3'
'American Airlines', '2'
'Corsair Internat', '1'
'EasyJet', '1'
'Ryanair', '1'
*/

-- On Obtient la même chose que le cours
--------------------------------------

SELECT compPil, COUNT(idPilote) nbPilote
FROM Pilote
GROUP BY compPil
HAVING COUNT(compPil) = 2;

/*
# compPil, nbPilote
'1', '2'
'4', '2'
*/

-- On Obtient la meme chose que dans le cours.
--------------------------------------









--------------------------------------
-- Question 2
SELECT COUNT(idEtud)
FROM Etudiant
WHERE depLycee IN (29,56,35,22);

/*
# COUNT(idEtud)
'81'
*/

--------------------------------------

-- Question 3
SELECT COUNT(poursuiteEtudes)
FROM Etudiant
WHERE poursuiteEtudes IS NOT NULL;

/*
# COUNT(poursuiteEtudes)
'74'
*/
--------------------------------------

-- Question 4
SELECT COUNT(tuteurApp)
FROM Apprenti
GROUP BY tuteurApp;

/*
7 Tuples
# COUNT(tuteurApp)
'3'
'2'
'7'
'6'
'2'
'2'
'2'
*/

--------------------------------------

-- Question 5
SELECT COUNT(tuteurApp)
FROM Apprenti, Entreprise
WHERE tuteurApp = "PB"
AND entrepriseApp = idEntreprise
AND depEntreprise = 56;
GROUP BY tuteurApp;

/*
# COUNT(tuteurApp)
'4'
*/
--------------------------------------

-- Question 6
SELECT COUNT(formationInfo2)/Count(*)
FROM Etudiant;
/*
# COUNT(formationInfo2)/Count(*)
'0.7664'
*/
--------------------------------------

-- Question 7
SELECT COUNT(DISTINCT tuteurApp)/COUNT(DISTINCT idEns)
FROM Enseignant, Apprenti;

/*
# COUNT(DISTINCT tuteurApp)/COUNT(DISTINCT idEns)
'0.3333'
*/

--------------------------------------

-- Question 8

SELECT tuteurApp, COUNT(etudApp)
FROM Apprenti
GROUP BY tuteurApp
ORDER BY tuteurApp ASC;

/*
7 Tuples
# nomEns, COUNT(etudApp)
'Baudont', '6'
'Fleurquin', '2'
'Kamp', '2'
'Lefevre', '2'
'Mannevy', '7'
'Tonin', '2'
'Tuffigo', '3'
*/





-- Question 9

SELECT nomEns, COUNT(etudApp)
FROM Apprenti, Enseignant
GROUP BY nomEns
ORDER BY nomEns ASC;

/*
21 Tuples
# nomEns, COUNT(etudApp)
'Baudont', '24'
'Borne', '24'
'Fleurquin', '24'
'Godin', '24'
'Joucla', '24'
...
*/



-- Question 10
SELECT MAX(nbApp)
FROM (SELECT tuteurApp, COUNT(etudApp) AS nbApp
    FROM Apprenti
    GROUP BY tuteurApp
    ) AS Subquerry;

/*
7 etudiant
1 Tuples

# MAX(nbApp)
'7'
*/


-- Question 11
    
SELECT nomEns
FROM Apprenti, Enseignant
WHERE idEns = tuteurApp
GROUP BY tuteurApp
HAVING COUNT(etudApp) =

    (
    SELECT MAX(nbApp)
    FROM (SELECT tuteurApp, COUNT(etudApp) AS nbApp
        FROM Apprenti
        GROUP BY tuteurApp
        ) AS Subquerry
    );

/*
1 Tuples : "Manevy"
# nomEns
'Mannevy'
*/

-- Question 12

SELECT AVG(nbApp)
FROM (SELECT tuteurApp, COUNT(etudApp) AS nbApp
    FROM Apprenti
    GROUP BY tuteurApp
    ) AS Subquerry;


/*
# AVG(nbApp)
'3.4286'
*/


-- Question 13 (2 tuples)
SELECT nomEns
FROM Apprenti, Enseignant
WHERE idEns = tuteurApp
GROUP BY tuteurApp
HAVING COUNT(etudApp) >
    (
    SELECT AVG(nbApp)
    FROM (SELECT tuteurApp, COUNT(etudApp) AS nbApp
        FROM Apprenti
        GROUP BY tuteurApp
        ) AS Subquerry
    );

/*
# nomEns
'Mannevy'
'Baudont'
*/

-- Question 14 (17 tuples)

SELECT depLycee, COUNT(idEtud)
FROM Etudiant
GROUP BY depLycee
ORDER BY depLycee DESC;

/*
17 Tuples

# depLycee, COUNT(idEtud)
'95', '1'
'90', '1'
'86', '1'
'85', '2'
'72', '2'
'69', '1'
'60', '2'
'56', '34'
'53', '1'
'50', '3'
'49', '1'
'44', '7'
'37', '1'
'35', '25'
'29', '19'
'22', '3'
NULL, '3'

*/

-- Question 15 (22 tuples)

SELECT depLycee
FROM Etudiant
WHERE depLycee IN (29,56,35,22)
GROUP BY depLycee
HAVING COUNT(idEtud) =
    (
    SELECT MIN(nbEtud)
    FROM (SELECT depLycee, COUNT(idEtud) AS nbEtud
        FROM Etudiant
        WHERE depLycee IN (29,56,35,22)
        GROUP BY depLycee
        ) AS Subquerry
    );

/*
1 Tuples

# depLycee
'22'
*/

-- Question 16 (4 tuples)

SELECT poursuiteEtudes, COUNT(*) AS nombre_etudiants
FROM Etudiant
WHERE poursuiteEtudes IS NOT NULL
GROUP BY poursuiteEtudes
HAVING COUNT(*) >= 5;

/*
4 Tuples
# poursuiteEtudes, nombre_etudiants
'ENSIBS cyberdéfense', '11'
'Licence info Vannes', '10'
'Travail', '5'
'Licence Pro Vannes Delice', '5'
*/

-- Question 17 (1 ENSIBS)

SELECT poursuiteEtudes
FROM Etudiant
WHERE UPPER(formationInfo2) = 'APP'
GROUP BY poursuiteEtudes
HAVING COUNT(*) = 
    (
        SELECT MAX(nbApp)
        FROM (
            SELECT COUNT(*) as nbApp
            FROM Etudiant
            WHERE UPPER(formationInfo2) = 'APP'
            GROUP BY poursuiteEtudes
        ) AS subquery
    );

/*
# poursuiteEtudes
'ENSIBS cyberdéfense'
*/

-- Question 18 (3 Tuples)
SELECT poursuiteEtudes, COUNT(*) AS nombre_etudiants
FROM Etudiant
WHERE parcoursInfo2 = 'DA'
GROUP BY poursuiteEtudes
ORDER BY COUNT(*) DESC
LIMIT 3;

/*
3 Tuples

# poursuiteEtudes, nombre_etudiants
NULL, '5'
'Licence Pro Vannes Delice', '5'
'Licence info Vannes', '5'
*/