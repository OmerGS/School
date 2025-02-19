SET SERVEROUTPUT ON;

DECLARE
  CURSOR c_retraits IS
    SELECT op.numOperation, op.dateOperation
    FROM Operation op
    WHERE op.typeOperation = 'RETRAIT'
    ORDER BY op.dateOperation DESC;

  v_idOperation Operation.numOperation%TYPE;
  v_dateOperation Operation.dateOperation%TYPE;

  v_count NUMBER := 0;
BEGIN
  DBMS_OUTPUT.PUT_LINE('Les 5 derniers retraits :');
  OPEN c_retraits;
  LOOP
    FETCH c_retraits INTO v_idOperation, v_dateOperation;
    EXIT WHEN c_retraits%NOTFOUND OR v_count = 5;

    DBMS_OUTPUT.PUT_LINE('L operation ' || v_idOperation || ' a été effectuée le ' || TO_CHAR(v_dateOperation, 'DD-MON-YY'));
    v_count := v_count + 1;
  END LOOP;
  CLOSE c_retraits;

  IF v_count = 0 THEN
    DBMS_OUTPUT.PUT_LINE('Aucun retrait trouvé.');
  END IF;
END;
/
