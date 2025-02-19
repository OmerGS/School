SET SERVEROUTPUT ON;

-- Trigger pour vérifier qu'une agence a exactement un seul directeur
CREATE OR REPLACE TRIGGER trg_verif_directeur_agence
AFTER INSERT OR UPDATE OR DELETE ON Agent
DECLARE
    v_nbDirecteurs NUMBER;
BEGIN
    -- Boucle sur toutes les agences pour vérifier la contrainte
    FOR rec IN (SELECT numAgence FROM Agence) LOOP
        SELECT COUNT(*) INTO v_nbDirecteurs
        FROM Agent
        WHERE sonAgence = rec.numAgence
        AND estDirecteur = 1;

        -- Vérification
        IF v_nbDirecteurs = 0 THEN
            DBMS_OUTPUT.PUT_LINE('Erreur: L''agence ' || rec.numAgence || ' n''a pas de directeur.');
        ELSIF v_nbDirecteurs > 1 THEN
            DBMS_OUTPUT.PUT_LINE('Erreur: L''agence ' || rec.numAgence || ' a plus d''un directeur.');
        END IF;
    END LOOP;
END;
/

-- Trigger pour vérifier qu'aucun employé ne gagne plus que le directeur de son agence
CREATE OR REPLACE TRIGGER trg_verif_salaire_employe
AFTER INSERT OR UPDATE OR DELETE ON Agent
DECLARE
    v_salaireDirecteur NUMBER;
    v_count_errors NUMBER := 0;
BEGIN
    -- Parcours de toutes les agences
    FOR rec IN (SELECT DISTINCT sonAgence FROM Agent WHERE sonAgence IS NOT NULL) LOOP
        -- Récupération du salaire du directeur de l'agence
        SELECT NVL(MAX(salaire), 0) INTO v_salaireDirecteur
        FROM Agent
        WHERE sonAgence = rec.sonAgence
        AND estDirecteur = 1;

        -- Vérification pour chaque employé
        FOR emp IN (
            SELECT numAgent, salaire 
            FROM Agent
            WHERE sonAgence = rec.sonAgence 
            AND estDirecteur = 0
        ) LOOP
            IF emp.salaire > v_salaireDirecteur THEN
                v_count_errors := v_count_errors + 1;
                DBMS_OUTPUT.PUT_LINE('Erreur: L''employé ' || emp.numAgent || 
                                     ' gagne plus que le directeur de l''agence ' || rec.sonAgence || '.');
            END IF;
        END LOOP;
    END LOOP;

    -- Résumé des erreurs
    IF v_count_errors > 0 THEN
        DBMS_OUTPUT.PUT_LINE('Nombre total d''erreurs de salaire détectées: ' || v_count_errors);
    END IF;
END;
/
