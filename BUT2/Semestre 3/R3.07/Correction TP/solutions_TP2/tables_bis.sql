-- Avoir la liste des contraintes
SELECT * FROM user_constraints WHERE table_name = 'APPARTIENT';

-- Supprimer la contrainte de clé étrangère faisant référence à Compte
ALTER TABLE Appartient DROP CONSTRAINT fk_Appartient_Compte;

-- Ajouter la nouvelle contrainte de clé étrangère faisant référence à Compte avec une suppression en cascade
ALTER TABLE Appartient ADD CONSTRAINT fk_Appartient_Compte FOREIGN KEY(unCompte) REFERENCES Compte(numCompte) ON DELETE CASCADE;

-- Supprimer la contrainte de clé étrangère faisant référence à Compte
ALTER TABLE Operation DROP CONSTRAINT fk_Operation_Compte;

-- Ajouter la nouvelle contrainte de clé étrangère faisant référence à Compte avec une suppression en cascade
ALTER TABLE Operation ADD CONSTRAINT fk_Operation_Compte FOREIGN KEY(leCompte) REFERENCES Compte(numCompte) ON DELETE CASCADE;