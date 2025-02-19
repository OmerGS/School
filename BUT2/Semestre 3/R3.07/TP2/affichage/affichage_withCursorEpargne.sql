SET SERVEROUTPUT ON;

DECLARE
  CURSOR c_compteEpargne IS
    SELECT a.numAgence, COUNT(cl.compte) AS nbClients
    FROM Compte c
    INNER JOIN Client cl ON cl.compte = c.numCompte
    INNER JOIN Agent ag ON ag.sonAgence = cl.sonagent
    INNER JOIN Agence a ON a.numAgence = ag.sonAgence
    WHERE c.typeCompte <> 'EPARGNE'
    GROUP BY a.numAgence;

  v_numAgence Agence.numAgence%TYPE;
  v_nbClient NUMBER;

  v_count NUMBER := 0;
BEGIN
  DBMS_OUTPUT.PUT_LINE('Clients avec comptes sans épargne :');

  OPEN c_compteEpargne;
  LOOP
    FETCH c_compteEpargne INTO v_numAgence, v_nbClient;
    EXIT WHEN c_compteEpargne%NOTFOUND OR v_count = 5;

    DBMS_OUTPUT.PUT_LINE('L agence ' || v_numAgence || ' compte ' || v_nbClient || ' client(s) sans épargne.');
    v_count := v_count + 1;
  END LOOP;

  CLOSE c_compteEpargne;

  IF v_count = 0 THEN
    DBMS_OUTPUT.PUT_LINE('Aucun client trouvé !');
  END IF;
END;
/
