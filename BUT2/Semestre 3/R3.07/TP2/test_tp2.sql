-- QUESTION 1 :

DELETE COMPTE WHERE NUMCOMPTE = 19;

/*
On a l'erreur ci-dessous : 
ERROR at line 1:
ORA-02292: integrity constraint (SYSTEM.FK_COMPTE) violated - child record found
https://docs.oracle.com/error-help/db/ora-02292/

Cela veut dire qu'il y une autre table qui a une référence vers la table Compte. Donc il faut d'abord
supprimer la reference avant de supprimer la valeur.
*/



-- QUESTION 3 :
DELETE COMPTE WHERE NUMCOMPTE = 19;

/*
SQL>   DELETE COMPTE WHERE NUMCOMPTE = 19;

1 row deleted.

Commit complete.
*/


ROLLBACK;

/*
SQL>   ROLLBACK;

Rollback complete.
*/


/*
La suppression fonctionne normalement mais le RollBack est mis comme complet mais il ne s'effectue pas.
Je pense que cela vient du fait qu'il va à la dernière fois où il y a un un COMMIT. Vu que lorsqu'on fait un DELETE, SELECt, INSERT
le GDBC effectue des COMMIT implicite, le rollback nous emmène au dernier point où il y a eu un COMMIT, càd juste après le DELETE.
*/










-------------------------------------------------------------------------------

/* QUESTION 7a */

INSERT INTO Compte (numCompte, typeCompte, solde)
VALUES (21, 'EPARGNE', 50);

INSERT INTO Client (numClient, numCompte)
VALUES (1, 21);
/* ORA-01779: cannot modify a column which maps to a non key-preserved table */


/* QUESTION 7b */

DELETE FROM Compte_Client
WHERE client_id IS NULL;

/*ORA-01752: cannot delete from view without exactly one key-preserved table*/


/* QUESTION 7c */

UPDATE Compte_Client
SET client_id = 1
WHERE client_id IS NULL;


/* ORA-01779: cannot modify a column which maps to a non key-preserved table */



