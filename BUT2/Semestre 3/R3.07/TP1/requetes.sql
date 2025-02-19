-- Question 4 (Roux, Leclerc) : 

SELECT DISTINCT c.numClient, c.nomClient, c.prenomClient
FROM Client c
JOIN Operation o ON c.numClient = o.leclient
WHERE o.typeOperation = 'RETRAIT'
  AND o.montant > 1000;


-- Question 5 : 

-- MARCHE PAS AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

SELECT c.nomClient, c.prenomClient
FROM Client c
LEFT JOIN Agent a ON c.sonAgent = a.numAgent AND a.nomAgent = 'Dupont'
LEFT JOIN Compte co ON c.compte = co.numCompte
WHERE a.numAgent IS NULL OR co.numCompte IS NULL;


-- Question 6 : 

SELECT a.numAgence, a.telAgence, a.adAgence, COUNT(ag.numAgent) AS nombre_agents
FROM Agence a
LEFT JOIN Agent ag ON a.numAgence = ag.sonAgence
GROUP BY a.numAgence, a.telAgence, a.adAgence;


-- Question 7 : 

SELECT nomAgent, prenomAgent, salaire
FROM Agent
WHERE estDirecteur = 1
ORDER BY salaire DESC;

-- Question 8 : 

SELECT 
    sonAgence AS Directeur,
    COUNT(*) AS Nombre_Agents
FROM 
    Agent
GROUP BY 
    sonAgence
ORDER BY 
    Nombre_Agents DESC;

-- Question 9 : 

SELECT 
    DISTINCT a.numAgent,
    a.nomAgent || ' ' || a.prenomAgent AS Nom_Agent
FROM 
    Client c
JOIN 
    Agent a ON c.sonAgent = a.numAgent
JOIN 
    Compte cp ON c.compte = cp.numCompte
WHERE 
    cp.typeCompte = 'EPARGNE';


-- Question 10 :

SELECT *
FROM Operation
WHERE typeOperation = 'RETRAIT'
ORDER BY dateOperation DESC;

-- Question 11 :
SELECT 
    a.numAgence,
    COUNT(c.numClient) AS Nombre_Clients_Sans_Epargne
FROM 
    Client c
JOIN 
    Agent ag ON c.sonAgent = ag.numAgent
JOIN 
    Agence a ON ag.sonAgence = a.numAgence
LEFT JOIN 
    Compte cp ON c.compte = cp.numCompte AND cp.typeCompte = 'EPARGNE'
WHERE 
    cp.numCompte IS NULL
GROUP BY 
    a.numAgence;



-- Question 12 :

SELECT 
    o.leclient,
    c.nomClient || ' ' || c.prenomClient AS Nom_Client
FROM 
    Operation o
JOIN 
    Client c ON o.leclient = c.numClient
JOIN 
    Compte cp ON o.leCompte = cp.numCompte
WHERE 
    cp.typeCompte IN ('COURANT', 'EPARGNE')
GROUP BY 
    o.leclient, c.nomClient, c.prenomClient
HAVING 
    COUNT(DISTINCT cp.typeCompte) = 2;
