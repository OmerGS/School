-- Recherche de l'agent Fontaine pour obtenir son numAgent
SELECT numAgent, nomAgent, sonAgence
FROM Agent
WHERE nomAgent = 'Fontaine';

-- Mise à jour de l'agence de l'agent Fontaine
UPDATE Agent
SET sonAgence = 4
WHERE nomAgent = 'Fontaine';

-- Vérification après la mise à jour
SELECT * FROM Agent WHERE nomAgent = 'Fontaine';
