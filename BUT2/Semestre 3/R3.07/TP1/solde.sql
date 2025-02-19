SET SERVEROUTPUT ON;

DECLARE
    v_numClient   CLIENT.numClient%TYPE;
    v_nomClient   VARCHAR2(100);
    v_totalSolde  NUMBER := 0;
BEGIN
    v_numClient := &client_number;

    SELECT nomClient || ' ' || prenomClient
    INTO v_nomClient
    FROM Client
    WHERE numClient = v_numClient;

    SELECT NVL(SUM(solde), 0)
    INTO v_totalSolde
    FROM Compte
    WHERE numCompte IN (SELECT compte FROM Client WHERE numClient = v_numClient);

    DBMS_OUTPUT.PUT_LINE('Nom du client : ' || v_nomClient);
    DBMS_OUTPUT.PUT_LINE('Solde total des comptes : ' || v_totalSolde);
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Client ou compte non trouvé.');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erreur : ' || SQLERRM);
END;
/