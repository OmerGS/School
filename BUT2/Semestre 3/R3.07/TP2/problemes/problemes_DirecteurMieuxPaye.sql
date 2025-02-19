SET SERVEROUTPUT ON;

DECLARE
  CURSOR c_salaireDirecteur IS
    SELECT 
      ag.numAgent AS Directeur,
      ag.salaire AS SalaireDirecteur,
      ag.sonAgence AS Agence,
      MAX(a.salaire) AS SalaireMaxAgent
    FROM Agent ag
    JOIN Agent a ON ag.sonAgence = a.sonAgence AND ag.numAgent != a.numAgent
    WHERE ag.estDirecteur = 1
    GROUP BY ag.numAgent, ag.salaire, ag.sonAgence;

  v_directeur Agent.numAgent%TYPE;
  v_salaireDirecteur Agent.salaire%TYPE;
  v_agence Agent.sonAgence%TYPE;
  v_salaireMaxAgent Agent.salaire%TYPE;

  v_count NUMBER := 0;

BEGIN
  DBMS_OUTPUT.PUT_LINE('Vérification des directeurs mieux payés que leurs agents :');

  OPEN c_salaireDirecteur;
  LOOP
    FETCH c_salaireDirecteur INTO v_directeur, v_salaireDirecteur, v_agence, v_salaireMaxAgent;
    EXIT WHEN c_salaireDirecteur%NOTFOUND;

    IF v_salaireDirecteur <= v_salaireMaxAgent THEN
      DBMS_OUTPUT.PUT_LINE('Violation : Directeur ' || v_directeur || 
                           ' (Agence ' || v_agence || ') avec un salaire de ' || v_salaireDirecteur || 
                           ' est moins payé que l agent le mieux payé (' || v_salaireMaxAgent || ').');
      v_count := v_count + 1;
    END IF;
  END LOOP;

  CLOSE c_salaireDirecteur;

  IF v_count = 0 THEN
    DBMS_OUTPUT.PUT_LINE('Tout est bon ! Tous les directeurs sont mieux payés que leurs agents.');
  END IF;
END;
/
