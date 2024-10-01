/*
EXERCICE 1 :
*/

/*
Question 1 : 
- On va créer les tables dans l'ordre suivant : Enseignant, 
Cycles, Cours et Requiert. On crée les tables dans cet ordre car il y a une 
"dependance" entre les tables. Il faut par exemple nomEns contenu dans la table 
Enseigant pour créer la table Cycle.

- On va supprimer les tables dans l'ordre inverse donc : Requiert, Cours,
Cycles, Enseignant. Même chose que pour créer les tables sont dépendant entre 
eux donc il faut commencer par les tables avec aucune dépendance.
*/

/*
QUESTION 2 : Ci-dessous 
*/

CREATE TABLE Enseignant(
    nomEns VARCHAR2(30)
    CONSTRAINT pk_nomEns PRIMARY KEY,
    
    prenomEns VARCHAR2(30),
    adresse VARCHAR2(50),
    statut VARCHAR2(20)
);

CREATE TABLE Cycle(
    num NUMBER
    CONSTRAINT pk_num PRIMARY KEY,
    
    enseignantResponsable VARCHAR2(30)
    CONSTRAINT fk_nomEns_Enseignant REFERENCES Enseignant(nomEns)
    CONSTRAINT uq_enseignantResponsable UNIQUE
    CONSTRAINT nn_enseignantResponsable NOT NULL
);

CREATE TABLE Cours(
    nomCours VARCHAR2(30)
    CONSTRAINT pk_Cours PRIMARY KEY,
    
    volumeH NUMBER(3 , 1),
    Enseignant VARCHAR2(30)
    CONSTRAINT fk_Enseignant_nomEns REFERENCES Enseignant(nomEns)
    CONSTRAINT nn_Enseignant NOT NULL
    CONSTRAINT uq_Enseignant UNIQUE
);

CREATE TABLE Requiert(
    cours VARCHAR2(30)
    CONSTRAINT fk_Cours_nomCours REFERENCES Cours(nomCours),
    
    coursRequis VARCHAR2(30)
    CONSTRAINT fk_CoursRequis_nomCours REFERENCES Cours(nomCours),
    
    CONSTRAINT pk_Requiert PRIMARY KEY (cours, coursRequis)
);

/*
QUESTION 3 : On devrait supprimer les tables avant de créer des nouvelles Tables
en utilisant "DROP TABLE "nom de la Table"".

Le code devrai ressembler à ça (Ci-dessous) :

DROP TABLE Requiert;
DROP TABLE Cours;
DROP TABLE Cycle;
DROP TABLE Enseignant;

CREATE TABLE Requiert(...);
CREATE TABLE Cours(...);
CREATE TABLE Cycle(...);
CREATE TABLE Enseignant(...);
*/

/*
EXERCICE 2 :
*/

CREATE TABLE Proprietaire(
    idProprietaire NUMBER
    CONSTRAINT pk_idProprietaire PRIMARY KEY,
    
    nomProprietaire VARCHAR2(30),
    prenomProprietaire VARCHAR2(30)
    CONSTRAINT uq_prenomProprietaire UNIQUE
    CONSTRAINT nn_emailProprietaire NOT NULL
    CONSTRAINT ck_emailProprietaire CHECK (emailProprietaire LIKE '%@%'),
    
    CONSTRAINT uq_nom_prenom
    UNIQUE(nomProprietaire, prenomProprietaire)
);

CREATE TABLE Emplacement(
    idEmplacement NUMBER
    CONSTRAINT pk_idEmplacement PRIMARY KEY,
    
    longueurEmplacement NUMBER
    CONSTRAINT nn_longueurEmplacement NOT NULL,
    
    coutJournalier NUMBER
    CONSTRAINT nn_coutJournalier NOT NULL
);

CREATE TABLE Bateau(
    idBateau NUMBER
    CONSTRAINT pk_idBateau PRIMARY KEY,
    
    nomBateau VARCHAR2(30),
    
    longueurBateau NUMBER
    CONSTRAINT nn_longueurBateau NOT NULL
    CONSTRAINT ck_longueurBateau CHECK (longueurBateau < 20),
    
    leProprietaire NUMBER
    CONSTRAINT fk_idProprietaire_Propretaire
    REFERENCES Proprietaire(idProprietaire)
    CONSTRAINT nn_leProprietaire NOT NULL,
    
    leStationnement NUMBER
    CONSTRAINT fk_idEmplacement_Emplacement
    REFERENCES Emplacement(idEmplacement)
    CONSTRAINT uq_leStationnement UNIQUE
);

CREATE TABLE Reservation(
    idReservation NUMBER
    CONSTRAINT pk_idReservation PRIMARY KEY,
    
    dateDebut DATE
    CONSTRAINT nn_dateDebut NOT NULL,
    
    dateFin DATE
    CONSTRAINT nn_dateFin NOT NULL
    CONSTRAINT ck_dateFin CHECK (dateFin > dateDebut),
    
    leBateau NUMBER
    CONSTRAINT fk_idBateau_Bateau REFERENCES Bateau(idBateau)
    CONSTRAINT nn_leBateau NOT NULL,
    
    lEmplacement NUMBER
    CONSTRAINT fk_Reservation_Emplacement REFERENCES
    Emplacement(idEmplacement)
    CONSTRAINT nn_lEmplacement NOT NULL
);

    ALTER TABLE Emplacement
    ADD CONSTRAINT ck_longueurEmplacement CHECK (longueurEmplacement >= 20);

/*
QUESTION 5 :
- CONSTRAINT ck_longueurBateau CHECK (longueurBateau < 20)
- CONSTRAINT ck_dateFin CHECK (dateFin > dateDebut)
- CONSTRAINT ck_emailProprietaire CHECK (email_column LIKE '%@%')
*/



/*
QUESTION 6 :
On peut ajouter la contrainte pour que longueurEmplacement soit superieur ou 
egale à 20 pour que tout les bateaux puissent se garer aux emplacements.

ALTER TABLE Emplacement
ADD CONSTRAINT ck_longueurEmplacement CHECK (longueurEmplacement >= 20)
*/
