/*
TP6
*/

------------------------------
-- Shell / root@localhost --
------------------------------

------ QUESTION 1 (OK FONCTIONNE)
/*
Pour supprimer les utilisateurs qu'on a créer lors du TP1.
*/
DROP USER 'omer'@'localhost';
DROP USER 'peron'@'localhost';

/*
Pour supprimer la base de données qu'on a utilisées durant les TPs.
*/
DROP DATABASE bd_r206;

------------------------------------------------------------------------

------ QUESTION 2 (OK FONCTIONNE)
CREATE DATABASE bd_iut;


CREATE USER pham@localhost IDENTIFIED BY 'mdp_pham';


GRANT ALL PRIVILEGES ON *.* TO pham@localhost WITH GRANT OPTION;

------------------------------------------------------------------------

------ QUESTION 3 (OK FONCTIONNE)
-- Dans le shell client taper successivement
-- source chemin_d'accès_du_fichier.sql
/*
use bd_iut;

source C:\Users\omerf\Desktop\Ressource\Semestre 2\R2.06 - Exploitation BDD\TD 6\R206_TP1_Creation_MySQL.sql
source C:\Users\omerf\Desktop\Ressource\Semestre 2\R2.06 - Exploitation BDD\TD 6\R206_TP1_Remplissage_MySQL.sql
*/

------------------------------------------------------------------------

------ QUESTION 4 (OK FONCTIONNE)

---Jointure entre les tables Etudiant, Stagiaire et Entreprise

CREATE OR REPLACE VIEW vue_Etud_Stag_Ent
AS 
SELECT NOMETUD
FROM ETUDIANT
	JOIN STAGIAiRE ON IDETUD = ETUDSTAGIAIRE
        JOIN ENTREPRISE ON ENTREPRISESTAGE = IDENTREPRISE
;


SELECT *
FROM vue_Etud_Stag_Ent
;


---Jointure entre les tables Etudiant, Apprentis et Entreprise

CREATE OR REPLACE VIEW vue_Etud_App_Ent
AS 
SELECT NOMETUD
FROM ETUDIANT
	JOIN APPRENTI ON IDETUD = ETUDAPP
        JOIN ENTREPRISE ON ENTREPRISEAPP = IDENTREPRISE
;


SELECT *
FROM vue_Etud_App_Ent
;

------------------------------------------------------------------------

------ QUESTION 5 (OK FONCTIONNE)

-- On va créer les differents utilisateurs
/*
On laisse le drop pour tester la création.
*/

-- Kamp
-- DROP USER 'kamp'@'localhost';
CREATE USER 'kamp'@'localhost' IDENTIFIED BY 'mdp_kamp';

-- Khayata
DROP USER 'khayata'@'localhost';
CREATE USER 'khayata'@'localhost' IDENTIFIED BY 'mdp_khayata';

-- Baudont
DROP USER 'baudont'@'localhost';
CREATE USER 'baudont'@'localhost' IDENTIFIED BY 'mdp_baudont';

-- Fleurquin
DROP USER 'fleurquin'@'localhost';
CREATE USER 'fleurquin'@'localhost' IDENTIFIED BY 'mdp_fleurquin';

------------------------------------------------------------------------

------ QUESTION 6 (LECTURE FONCTIONNE PAS)

DROP ROLE 'bd_iut_lecture', 'bd_iut_ecriture';
CREATE ROLE 'bd_iut_lecture', 'bd_iut_ecriture';

GRANT SELECT 
ON bd_iut.* 
TO bd_iut_lecture;
GRANT CREATE, DROP, INSERT, UPDATE, DELETE 
ON bd_iut.* 
TO bd_iut_ecriture;

------------------------------------------------------------------------

------ QUESTION 7

SET DEFAULT ROLE ALL TO 'kamp'@'localhost';
SET DEFAULT ROLE ALL TO 'khayata'@'localhost';
SET DEFAULT ROLE ALL TO 'baudont'@'localhost';
SET DEFAULT ROLE ALL TO 'fleurquin'@'localhost';

GRANT bd_iut_lecture 
    TO 'kamp'@'localhost', 'khayata'@'localhost', 'baudont'@'localhost', 'fleurquin'@'localhost';
GRANT bd_iut_ecriture 
    TO 'kamp'@'localhost';

GRANT  UPDATE, CREATE, DROP, INSERT, UPDATE, DELETE
    ON bd_iut.Etudiant
    TO 'khayata'@'localhost';

GRANT  UPDATE, CREATE, DROP, INSERT, UPDATE, DELETE
    ON bd_iut.GroupeInfo1
    TO 'khayata'@'localhost';

GRANT  UPDATE, CREATE, DROP, INSERT, UPDATE, DELETE
    ON vue_Etud_App_Ent
    TO 'baudont'@'localhost';


GRANT UPDATE(PoursuiteEtudes), CREATE, DROP, INSERT, UPDATE, DELETE
    ON bd_iut.Etudiant
    TO 'fleurquin'@'localhost';

------------------------------------------------------------------------

------ QUESTION 8

SHOW GRANTS FOR 'kamp'@'localhost';
SHOW GRANTS FOR 'khayata'@'localhost';
SHOW GRANTS FOR 'baudont'@'localhost';
SHOW GRANTS FOR 'fleurquin'@'localhost';


