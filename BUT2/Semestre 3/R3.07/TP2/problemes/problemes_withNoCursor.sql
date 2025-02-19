SET SERVEROUTPUT ON

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