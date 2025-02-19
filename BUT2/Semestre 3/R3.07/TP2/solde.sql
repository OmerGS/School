SET SERVEROUTPUT ON;

DECLARE
    v_numClient   Client.numClient%TYPE;
    v_nomClient   VARCHAR2(100);
    v_typeCompte  Compte.typeCompte%TYPE := 'EPARGNE';
    v_solde       NUMBER;
    v_totalSolde  NUMBER := 0;

    e_numClientNegatif EXCEPTION;

    CURSOR c_soldes(p_numClient Client.numClient%TYPE, p_typeCompte Compte.typeCompte%TYPE) IS
        SELECT solde
        FROM Compte
        WHERE numCompte IN (
            SELECT compte
            FROM Client
            WHERE numClient = p_numClient
        ) AND typeCompte = p_typeCompte;

BEGIN
    v_numClient := &client_number;

    IF v_numClient < 0 THEN
        RAISE e_numClientNegatif;
    END IF;

    BEGIN
        SELECT nomClient || ' ' || prenomClient
        INTO v_nomClient
        FROM Client
        WHERE numClient = v_numClient;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Aucun client ne porte ce numéro!');
            RETURN;
    END;

    DBMS_OUTPUT.PUT_LINE('Nom du client : ' || v_nomClient);

    FOR typeRec IN (SELECT DISTINCT typeCompte FROM Compte WHERE numCompte IN (
                      SELECT compte FROM Client WHERE numClient = v_numClient)) LOOP

        DBMS_OUTPUT.PUT_LINE('----------------' || typeRec.typeCompte || '-------');

        FOR soldeRec IN c_soldes(v_numClient, typeRec.typeCompte) LOOP
            DBMS_OUTPUT.PUT_LINE(soldeRec.solde);
            v_totalSolde := v_totalSolde + soldeRec.solde;
        END LOOP;
    END LOOP;

    DBMS_OUTPUT.PUT_LINE('Solde total des comptes : ' || v_totalSolde);

EXCEPTION
    WHEN e_numClientNegatif THEN
        DBMS_OUTPUT.PUT_LINE('Le numéro client ne doit pas être négatif!');
    WHEN VALUE_ERROR THEN
        DBMS_OUTPUT.PUT_LINE('Le numéro doit être un nombre!');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erreur : ' || SQLERRM);
END;
/