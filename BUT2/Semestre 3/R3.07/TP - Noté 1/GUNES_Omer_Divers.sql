SET SERVEROUTPUT ON;

DECLARE
    CURSOR c_compteEpargne IS
     SELECT c.numCompte, COUNT(cl.compte) AS nbClients
     FROM Compte c
     INNER JOIN Client cl ON cl.compte = c.numCompte
     AND c.typeCompte <> 'COURANT'
     GROUP BY cl.nomClient;

    v_numClient Client.numClient%TYPE;
    v_nbClient NUMBER;
    v_count NUMBER := 0;
BEGIN
  DBMS_OUTPUT.PUT_LINE('Clients avec comptes sans épargne :');

  OPEN c_compteEpargne;
  LOOP
    FETCH c_compteEpargne INTO v_numClient, v_nbClient;
    EXIT WHEN c_compteEpargne%NOTFOUND;

    IF v_count > 0 THEN
        DBMS_OUTPUT.PUT_LINE('Le client ' || v_nomClient || ' a ' || v_count || ' compte épargne');
        
        -- Deuxième curseur pour transformer les comptes épargne en compte courant.
    ELSIF v_count <= 0 THEN
        DBMS_OUTPUT.PUT_LINE('Le client a aucun compte épargne');
    END IF;

    v_count := v_count + 1;
  END LOOP;

  CLOSE c_compteEpargne;

  IF v_count = 0 THEN
    DBMS_OUTPUT.PUT_LINE('Aucun client trouvé !');
  END IF;
END;