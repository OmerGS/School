SET SERVEROUTPUT ON;

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