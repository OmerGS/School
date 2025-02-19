SET SERVEROUTPUT ON
DECLARE
    CURSOR cur_clients IS SELECT DISTINCT numClient, nomClient, COUNT(*) as nbComptes FROM CLIENT JOIN APPARTIENT ON numClient=unClient JOIN COMPTE ON numCompte=unCompte where LOWER(typecompte) = 'epargne' GROUP BY numClient, nomClient;
    CURSOR cur_comptes(p_numClient CLIENT.numClient%TYPE) IS SELECT * FROM COMPTE,APPARTIENT WHERE numCompte = unCompte AND unclient = p_numClient AND LOWER(typecompte) = 'epargne' FOR UPDATE OF typeCompte ;
    v_trouve BOOLEAN := FALSE;
BEGIN
    FOR v_client IN cur_clients LOOP
        IF v_client.nbComptes > 1 THEN
            v_trouve := TRUE;
            DBMS_OUTPUT.PUT_LINE(v_client.nomClient || ' a ' || v_client.nbComptes  || ' comptes d''épargne:');
            FOR v_compte IN cur_comptes(v_client.numClient) LOOP
                DBMS_OUTPUT.PUT_LINE('   => Transformation du compte numéro ' || v_compte.numCompte || ' en compte courant');
                UPDATE  COMPTE SET typeCompte= 'COURANT' WHERE CURRENT OF cur_comptes;
            END LOOP;
        END IF;
    END LOOP;
    IF NOT v_trouve THEN
        DBMS_OUTPUT.PUT_LINE('Tout est bon!');
    END IF;
END;
/
ROLLBACK;