SET SERVEROUTPUT ON;

/* No Cursor */
DECLARE
    v_compteur NUMBER;

BEGIN
    SELECT COUNT(*)
    INTO v_compteur
    FROM Compte c


    WHERE NOT EXISTS (
        SELECT 1
        FROM Compte cl
        WHERE c.numCompte = cl.numCompte
    );

    IF v_compteur > 0 THEN
        DBMS_OUTPUT.PUT_LINE('Attention, ' || v_compteur || ' comptes n ont pas de client');
    ELSE 
        DBMS_OUTPUT.PUT_LINE('Tous les comptes ont des clients associé');
    END IF;
END;
/

/* Directeur mieux payé */
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


/* Curseur */
DECLARE
    CURSOR c_comptes_sans_client IS
        SELECT numCompte
        FROM Compte c
        WHERE NOT EXISTS (
            SELECT 1
            FROM Client cl
            WHERE cl.numClient = c.numCompte
        );

        v_compte_id Compte.NUMCOMPTE%TYPE;
        v_count NUMBER := 0;

BEGIN
    open c_comptes_sans_client;
    LOOP 
        FETCH c_comptes_sans_client INTO v_compte_id;
        EXIT WHEN c_comptes_sans_client%NOTFOUND;

    DBMS_OUTPUT.PUT_LINE('Le compte ' || v_compte_id || ' n a pas de client!');
    v_count := v_count + 1;
  END LOOP;

  CLOSE c_comptes_sans_client;

    IF v_count > 0 THEN
    DBMS_OUTPUT.PUT_LINE('Attention, ' || v_count || ' compte(s) n ont pas de client!');
  ELSE
    DBMS_OUTPUT.PUT_LINE('Tous les comptes ont un client associé.');
  END IF;
END;
/

/* Unique directeur agence */
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

/* Même Nom que Agent */
DECLARE
  CURSOR c_clients_agents IS
    SELECT cl.nomClient AS nomClient, cl.numClient, ag.nomAgent AS nomAgent, ag.numAgent
    FROM Client cl
    JOIN Agent ag ON cl.nomClient = ag.nomAgent;

  v_nomClient Client.nomClient%TYPE;
  v_numClient Client.numClient%TYPE;
  v_nomAgent Agent.nomAgent%TYPE;
  v_numAgent Agent.numAgent%TYPE;

  v_count NUMBER := 0;

BEGIN
  DBMS_OUTPUT.PUT_LINE('Clients partageant le même nom que leurs agents :');

  OPEN c_clients_agents;
  LOOP
    FETCH c_clients_agents INTO v_nomClient, v_numClient, v_nomAgent, v_numAgent;
    EXIT WHEN c_clients_agents%NOTFOUND;

    DBMS_OUTPUT.PUT_LINE('Client [' || v_nomClient || '] (ID: ' || v_numClient || 
                         ') partage le même nom que l agent [' || v_nomAgent || '] (ID: ' || v_numAgent || ').');
    v_count := v_count + 1;
  END LOOP;

  CLOSE c_clients_agents;

  IF v_count = 0 THEN
    DBMS_OUTPUT.PUT_LINE('Tout est bon! Aucun client ne partage le même nom que ses agents.');
  END IF;
END;
/


/* Retrait dont on n'a pas le meme compte */
DECLARE
  -- Définition du curseur pour les retraits non autorisés
  CURSOR c_retraits_non_autorises IS
    SELECT o.leclient, o.leCompte, o.numOperation, o.dateOperation
    FROM Operation o
    WHERE o.typeOperation = 'RETRAIT'
      AND NOT EXISTS (
        SELECT 1
        FROM Appartient a
        WHERE a.unClient = o.leclient
          AND a.unCompte = o.leCompte
      );

  -- Variables pour stocker les données du curseur
  v_client Operation.leclient%TYPE;
  v_compte Operation.leCompte%TYPE;
  v_numOperation Operation.numOperation%TYPE;
  v_dateOperation Operation.dateOperation%TYPE;

  v_count NUMBER := 0;

BEGIN
  DBMS_OUTPUT.PUT_LINE('Visualisation des retraits non autorisés :');

  -- Ouverture du curseur
  OPEN c_retraits_non_autorises;
  LOOP
    FETCH c_retraits_non_autorises INTO v_client, v_compte, v_numOperation, v_dateOperation;
    EXIT WHEN c_retraits_non_autorises%NOTFOUND;

    -- Affichage des retraits non autorisés
    DBMS_OUTPUT.PUT_LINE('Le client ' || v_client || ' a effectué un retrait sur le compte ' || v_compte ||
                         ' (Numéro d''opération : ' || v_numOperation || ', Date : ' || v_dateOperation || ').');
    v_count := v_count + 1;
  END LOOP;

  -- Fermeture du curseur
  CLOSE c_retraits_non_autorises;

  -- Message si aucun retrait non autorisé n'a été trouvé
  IF v_count = 0 THEN
    DBMS_OUTPUT.PUT_LINE('Tout est bon ! Aucun retrait non autorisé n''a été détecté.');
  END IF;
END;
/
