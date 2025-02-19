-- Activation de l'affichage des erreurs
SET SERVEROUTPUT ON;

-- 1. Trigger pour contrôler les augmentations et baisses de salaire
CREATE OR REPLACE TRIGGER trig_modifSalaire
BEFORE UPDATE OF solde ON Client
FOR EACH ROW
DECLARE
  v_maxAugmentation NUMBER;
  v_maxBaisse NUMBER;
BEGIN
  -- Calcul des limites de modification
  v_maxAugmentation := :OLD.salaire * 1.10; -- +10%
  v_maxBaisse := :OLD.salaire * 0.92;       -- -8%

  -- Vérification des limites
  IF :NEW.salaire > v_maxAugmentation THEN
    RAISE_APPLICATION_ERROR(-20001, 'L''augmentation du salaire ne doit pas dépasser 10%.');
  ELSIF :NEW.salaire < v_maxBaisse THEN
    RAISE_APPLICATION_ERROR(-20002, 'La baisse du salaire ne doit pas dépasser 8%.');
  END IF;
END;
/
-- Fin Trigger 1


-- 2. Trigger pour empêcher qu'un client soit conseillé par un agent portant le même nom
CREATE OR REPLACE TRIGGER trig_nomClient_Agent
BEFORE INSERT OR UPDATE ON Client
FOR EACH ROW
DECLARE
  v_nomAgent VARCHAR2(50);
BEGIN
  SELECT nomAgent
  INTO v_nomAgent
  FROM Agent
  WHERE numAgent = :NEW.sonAgent;

  IF :NEW.nomClient = v_nomAgent THEN
    RAISE_APPLICATION_ERROR(-20003, 'Un client ne peut pas être conseillé par un agent portant le même nom.');
  END IF;
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    RAISE_APPLICATION_ERROR(-20004, 'L''agent spécifié n''existe pas.');
END;
/
-- Fin Trigger 2


-- 3. Trigger pour empêcher un retrait dépassant le solde et mettre à jour le solde après le retrait
CREATE OR REPLACE TRIGGER trig_retraitSolde
BEFORE INSERT OR UPDATE ON Operation
FOR EACH ROW
DECLARE
  v_solde_compte Compte.solde%TYPE;
BEGIN
  -- Vérifier si l'opération est un RETRAIT
  IF :NEW.typeOperation = 'RETRAIT' THEN
    -- Récupérer le solde actuel du compte
    SELECT solde
    INTO v_solde_compte
    FROM Compte
    WHERE numCompte = :NEW.leCompte;

    -- Vérifier si le montant retiré dépasse le solde
    IF :NEW.montant > v_solde_compte THEN
      RAISE_APPLICATION_ERROR(-20005, 'Le montant du retrait dépasse le solde disponible.');
    END IF;

    -- Mettre à jour automatiquement le solde du compte
    UPDATE Compte
    SET solde = solde - :NEW.montant
    WHERE numCompte = :NEW.leCompte;
  END IF;
END;
/
-- Fin Trigger 3


-- 4. Trigger pour empêcher un client de retirer sur un compte qui ne lui appartient pas
CREATE OR REPLACE TRIGGER trig_retrait_proprietaire
BEFORE INSERT OR UPDATE ON Operation
FOR EACH ROW
DECLARE
  v_count NUMBER;
BEGIN
  -- Vérifier si l'opération est un RETRAIT
  IF :NEW.typeOperation = 'RETRAIT' THEN
    -- Compter si le client est propriétaire du compte
    SELECT COUNT(*)
    INTO v_count
    FROM Appartient
    WHERE unClient = :NEW.leclient
      AND unCompte = :NEW.leCompte;

    -- Si le compte n'appartient pas au client, erreur
    IF v_count = 0 THEN
      RAISE_APPLICATION_ERROR(-20006, 'Le client ne peut retirer que sur un compte qui lui appartient.');
    END IF;
  END IF;
END;
/
-- Fin Trigger 4


-- 5. Trigger pour mettre à jour automatiquement le solde lors d'un dépôt
CREATE OR REPLACE TRIGGER trig_depotSolde
BEFORE INSERT OR UPDATE ON Operation
FOR EACH ROW
BEGIN
  -- Vérifier si l'opération est un DEPOT
  IF :NEW.typeOperation = 'DEPOT' THEN
    -- Mettre à jour automatiquement le solde du compte
    UPDATE Compte
    SET solde = solde + :NEW.montant
    WHERE numCompte = :NEW.leCompte;
  END IF;
END;
/
-- Fin Trigger 5
