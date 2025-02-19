SET SERVEROUTPUT ON;

CREATE OR REPLACE TRIGGER DIVERS_C_TRIGGER
AFTER INSERT OR UPDATE ON Operation
FOR EACH ROW
DECLARE
    --? Nb total de retraits
    v_nb_retraits NUMBER := 0;

    --? Montant total des retraits
    v_total_retraits NUMBER := 0;
BEGIN
    IF :new.typeOperation = 'RETRAIT' THEN
        SELECT NVL(SUM(montant), 0)
        INTO v_total_retraits
        FROM Operation
        WHERE leCompte = :new.leCompte
          AND TRUNC(dateOperation) = TRUNC(:new.dateOperation)
          AND typeOperation = 'RETRAIT';

        SELECT COUNT(*)
        INTO v_nb_retraits
        FROM Operation
        WHERE leCompte = :new.leCompte
          AND typeOperation = 'RETRAIT'
          AND TRUNC(dateOperation) = TRUNC(:new.dateOperation);


        --? Exception si on a plus de 3 reatrait en un jour
        IF v_nb_retraits > 3 THEN
            RAISE_APPLICATION_ERROR(
                -20600,
                'ORA-20600 : Tentative de plus de trois retraits sur le compte numéro ' || 
                :new.leCompte || ' le ' || TRUNC(:new.dateOperation)
            );
        END IF;

        --? Exception si on a on essaye de retirer + de 50 000 euros en un jour
        IF v_total_retraits + :new.montant > 50000 THEN
            RAISE_APPLICATION_ERROR(
                -20500,
                'ORA-20500 : Tentative de retrait de ' || :new.montant || 
                ' euros sur le compte numéro ' || :new.leCompte || 
                ' le ' || TRUNC(:new.dateOperation)
            );
        END IF;

        
        /*
        UPDATE OPERATION
        SET v_nb_retraits = v_nb_retraits + 1,
            v_total_retraits = v_total_retraits + :new.MONTANT
        WHERE LECOMPTE;
  
            --! Le montant total des retraits ne se met pas à jour, 
            --! uniquement le nombre de retrait change dans le fichier 
            --! tests_divers_C.sql
        */
    END IF;
END;
/
