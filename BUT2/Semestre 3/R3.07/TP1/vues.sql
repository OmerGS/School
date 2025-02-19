-- Question 2 :
-- Vue fournissant tous les élements dérivables (attributs et association)

CREATE OR REPLACE VIEW VueElementsDerivables AS
SELECT 
    c.numClient,
    c.nomClient,
    c.prenomClient,
    c.adClient,
    c.dateNaissClient,
    a.numAgent,
    a.nomAgent,
    a.prenomAgent,
    a.salaire,
    ag.numAgence,
    ag.telAgence,
    ag.adAgence,
    comp.numCompte,
    comp.typeCompte,
    comp.solde
FROM 
    Client c
JOIN 
    Agent a ON c.sonAgent = a.numAgent
JOIN 
    Agence ag ON a.sonAgence = ag.numAgence
JOIN 
    Compte comp ON c.compte = comp.numCompte;




-- Question 3 :
-- La vue détectant des éventuelles défauts de surjectivité (8, 20)


CREATE OR REPLACE VIEW VueDefautsSurjectivite AS
SELECT 
    'Agent non attribué à un client' AS Defaut,
    a.numAgent AS ElementOrphelin,
    a.nomAgent AS Nom,
    a.prenomAgent AS Prenom
FROM 
    Agent a
WHERE 
    NOT EXISTS (
        SELECT 1 
        FROM Client c
        WHERE c.sonAgent = a.numAgent
    )
UNION ALL
SELECT 
    'Compte non associé à un client' AS Defaut,
    comp.numCompte AS ElementOrphelin,
    NULL AS Nom,
    NULL AS Prenom
FROM 
    Compte comp
WHERE 
    NOT EXISTS (
        SELECT 1 
        FROM Client c
        WHERE c.compte = comp.numCompte
    )
UNION ALL
SELECT 
    'Agence sans directeur' AS Defaut,
    ag.numAgence AS ElementOrphelin,
    NULL AS Nom,
    NULL AS Prenom
FROM 
    Agence ag
WHERE 
    NOT EXISTS (
        SELECT 1 
        FROM Agent a
        WHERE a.sonAgence = ag.numAgence AND a.estDirecteur = 1
    );
