CREATE OR REPLACE VIEW Compte_Client AS
SELECT 
    c.numCompte AS compte_id,
    cl.numClient AS client_id
FROM 
    Compte c
LEFT JOIN 
    Client cl
ON 
    c.numCompte = cl.numClient;
