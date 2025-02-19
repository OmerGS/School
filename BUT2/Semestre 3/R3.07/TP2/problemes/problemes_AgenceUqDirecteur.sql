SET SERVEROUTPUT ON;

DECLARE
  CURSOR c_compteDirecteur IS
    SELECT agence.numAgence, COUNT(ag.numAgent) AS nbAgent
    FROM Agence agence
    LEFT JOIN Agent ag ON agence.numAgence = ag.sonAgence
    AND ag.ESTDIRECTEUR = 1
    GROUP BY agence.numAgence;

  v_numAgence Agence.numAgence%TYPE;
  v_nbAgents NUMBER;

BEGIN
  DBMS_OUTPUT.PUT_LINE('Agence avec leurs nombre d agents : ');

  OPEN c_compteDirecteur;
  LOOP
    FETCH c_compteDirecteur INTO v_numAgence, v_nbAgents;
    EXIT WHEN c_compteDirecteur%NOTFOUND;

    IF v_nbAgents > 1 THEN
      DBMS_OUTPUT.PUT_LINE('Agence ' || v_numAgence || ' a ' || v_nbAgents || ' directeur.');
    ELSIF v_nbAgents <= 0 THEN
      DBMS_OUTPUT.PUT_LINE('Agence ' || v_numAgence || ' n"a pas de directeur.');
    END IF;

    --DBMS_OUTPUT.PUT_LINE('L agence ' || v_numAgence || ' compte ' || v_nbAgents || ' agent(s)');
  END LOOP;

  CLOSE c_compteDirecteur;
END;
/
