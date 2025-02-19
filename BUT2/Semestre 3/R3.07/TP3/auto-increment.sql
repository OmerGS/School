-- Suppression de la séquence existante si elle existe déjà
BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE seq_Agence';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -2289 THEN  -- Ignore l'erreur si la séquence n'existe pas
            RAISE;
        END IF;
END;
/

-- Création de la séquence seq_Agence
CREATE SEQUENCE seq_Agence
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

-- Création du trigger pour l'auto-incrémentation
CREATE OR REPLACE TRIGGER trig_seq_Agence
BEFORE INSERT ON Agence
FOR EACH ROW
WHEN (NEW.numAgence IS NULL)
BEGIN
    -- Utilisation de la séquence pour générer la valeur
    SELECT seq_Agence.NEXTVAL INTO :NEW.numAgence FROM DUAL;
END;
/
