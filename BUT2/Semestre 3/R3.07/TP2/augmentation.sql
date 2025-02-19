SET SERVEROUTPUT ON;

DECLARE
  CURSOR c_agents IS
    SELECT numAgent, nomAgent, salaire, estDirecteur
    FROM Agent;

  v_numAgent Agent.numAgent%TYPE;
  v_nomAgent Agent.nomAgent%TYPE;
  v_salaire Agent.salaire%TYPE;
  v_estDirecteur Agent.estDirecteur%TYPE;

  v_nouveauSalaire NUMBER;

BEGIN
  DBMS_OUTPUT.PUT_LINE('Mise à jour des salaires des agents :');

  OPEN c_agents;
  LOOP
    FETCH c_agents INTO v_numAgent, v_nomAgent, v_salaire, v_estDirecteur;
    EXIT WHEN c_agents%NOTFOUND;

    IF v_estDirecteur = 1 THEN
      v_nouveauSalaire := v_salaire * 1.05; 
    ELSE
      v_nouveauSalaire := v_salaire * 1.01;
    END IF;

    UPDATE Agent
    SET salaire = v_nouveauSalaire
    WHERE numAgent = v_numAgent;

    DBMS_OUTPUT.PUT_LINE('Agent ' || v_nomAgent || ' : nouveau salaire = ' || ROUND(v_nouveauSalaire, 2));
  END LOOP;

  -- Fermer le curseur
  CLOSE c_agents;

  DBMS_OUTPUT.PUT_LINE('Mise à jour terminée. ROLLBACK en cours pour annuler les modifications...');

  ROLLBACK;

  DBMS_OUTPUT.PUT_LINE('Modifications annulées.');
END;
/
