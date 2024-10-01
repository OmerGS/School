use bd_r206;

CREATE OR REPLACE VIEW vue_Client_Complet
AS
SELECT nomClient, prenomClient, sonAgent, (DATE_FORMAT(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(dateNaissanceClient)), '%Y')+0) AS age
FROM Client, Agent
JOIN Agent ON numAgent = sonAgent
    JOIN Agence ON sonAgence = numAgence
        JOIN Agence ON numAgence = adAgence
;

SELECT *
FROM vue_Client_Complet
;