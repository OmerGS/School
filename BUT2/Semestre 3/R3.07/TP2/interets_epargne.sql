SET SERVEROUTPUT ON;

DECLARE
  CURSOR c_comptes IS
    SELECT numCompte, solde
    FROM Compte
    WHERE typeCompte = 'EPARGNE';

  v_numCompte Compte.numCompte%TYPE;
  v_solde Compte.solde%TYPE;

  v_soldeTotal NUMBER := 0;

BEGIN
  FOR compte IN c_comptes LOOP
    v_soldeTotal := v_soldeTotal + compte.solde;
  END LOOP;

  DBMS_OUTPUT.PUT_LINE('Solde global des comptes d épargne : ' || v_soldeTotal);

  FOR compte IN c_comptes LOOP
    IF v_soldeTotal >= 100000 THEN
      v_solde := compte.solde + (compte.solde * 5 / 100);
    ELSIF v_soldeTotal BETWEEN 10000 AND 99999.99 THEN
      v_solde := compte.solde + (compte.solde * 3 / 100);
    ELSE
      v_solde := compte.solde + (compte.solde * 1 / 100);
    END IF;

    UPDATE Compte
    SET solde = v_solde
    WHERE numCompte = compte.numCompte;

    DBMS_OUTPUT.PUT_LINE('Compte ' || compte.numCompte || ' : nouveau solde = ' || ROUND(v_solde, 2));
  END LOOP;

  COMMIT;
  DBMS_OUTPUT.PUT_LINE('Mises à jour des soldes terminées.');
END;
/
