DROP TABLE EnseignantInfo;
DROP TABLE EtudiantInfo;

CREATE TABLE EnseignantInfo(
		noEns NUMBER(4)
			CONSTRAINT pk_EnseignantInfo PRIMARY KEY,
		nomEns VARCHAR2(20),
		prenomEns VARCHAR2(20)
	)
;

INSERT INTO EnseignantInfo VALUES(1,'KAmP','Jean Francois');
INSERT INTO EnseignantInfo VALUES(2,'BEAuDONT','Pascal');
INSERT INTO EnseignantInfo VALUES(3,'GODIN','Thibault');
INSERT INTO EnseignantInfo VALUES(4,'KhayaTa','Mohammed');
INSERT INTO EnseignantInfo VALUES(5,'TONIN','Philippe');
INSERT INTO EnseignantInfo VALUES(6,'ROIRAND','Xavier');
INSERT INTO EnseignantInfo VALUES(7,'LE SOmMER','Nicolas');
INSERT INTO EnseignantInfo VALUES(8,'MErCIOL','Francois');
INSERT INTO EnseignantInfo VALUES(9,'LE Lain','Matthieu');
INSERT INTO EnseignantInfo VALUES(10,'LEFEVRE','Sebastien');
INSERT INTO EnseignantInfo VALUES(11,'BORNE','Isabelle');
INSERT INTO EnseignantInfo VALUES(12,'PHAM','Minh Tan');
INSERT INTO EnseignantInfo VALUES(13,'FLEURQUIN','Regis');
INSERT INTO EnseignantInfo VALUES(14,'NAeRT','Lucie');
INSERT INTO EnseignantInfo VALUES(15,'Belmouhcine','Abdelbadie');
INSERT INTO EnseignantInfo VALUES(16,'LeMaitre','Elodie');
INSERT INTO EnseignantInfo VALUES(17,'TUFFIGOT','Helene');
INSERT INTO EnseignantInfo VALUES(18,'Joucla','Philippe');
INSERT INTO EnseignantInfo VALUES(19,'Kerbellec','Goulven');
INSERT INTO EnseignantInfo VALUES(20,'Raut','Sophie');


CREATE TABLE EtudiantInfo(
		noEtu NUMBER(4)
			CONSTRAINT pk_EtudiantInfo PRIMARY KEY,
		nomEtu VARCHAR2(40),
		prenom VARCHAR2(30),
		groupe VARCHAR2(5)
	)
;

INSERT INTO EtudiantInfo VALUES(1,'ALEXANDRE','Nathan','B');
INSERT INTO EtudiantInfo VALUES(2,'ANNEIX','Amélie','B');
INSERT INTO EtudiantInfo VALUES(3,'ARANDEL','Cyprien','D');
INSERT INTO EtudiantInfo VALUES(4,'BARATHON','Polig','B');
INSERT INTO EtudiantInfo VALUES(5,'BARIOU','Melvyn','A');
INSERT INTO EtudiantInfo VALUES(6,'BELMOKHTAR','Hocin','B');
INSERT INTO EtudiantInfo VALUES(7,'BELOUAHRANI KEBIR HOCEINI','Ilias','D');
INSERT INTO EtudiantInfo VALUES(8,'BELTRAME','Maël','A');
INSERT INTO EtudiantInfo VALUES(9,'BERNARD-GRIFFITHS','Samuel','A');
INSERT INTO EtudiantInfo VALUES(10,'BILLARD','Louis','B');
INSERT INTO EtudiantInfo VALUES(11,'BURBAN','Léo','C');
INSERT INTO EtudiantInfo VALUES(12,'CABARET','Louann','D');
INSERT INTO EtudiantInfo VALUES(13,'CACQUEVEL','Robinson','C');
INSERT INTO EtudiantInfo VALUES(14,'CAMPION','Brayan','B');
INSERT INTO EtudiantInfo VALUES(15,'CAROMEL','Merlin','B');
INSERT INTO EtudiantInfo VALUES(16,'CARRÉ','Louan','B');
INSERT INTO EtudiantInfo VALUES(17,'CELINI','Alvin','D');
INSERT INTO EtudiantInfo VALUES(18,'CESMAT-BELLIARD','Alexis','B');
INSERT INTO EtudiantInfo VALUES(19,'CHAUVELIN','Thomas','A');
INSERT INTO EtudiantInfo VALUES(20,'CHAZEL','Matteo','A');
INSERT INTO EtudiantInfo VALUES(21,'COIGNARD','Maël','C');
INSERT INTO EtudiantInfo VALUES(22,'COUTANT','Jade','C');
INSERT INTO EtudiantInfo VALUES(23,'CRUNCHANT','Manon','D');
INSERT INTO EtudiantInfo VALUES(24,'DESBROUSSES','Victor','A');
INSERT INTO EtudiantInfo VALUES(25,'DIONNE','Clément','D');
INSERT INTO EtudiantInfo VALUES(26,'DITTBERNER','Emmeran','A');
INSERT INTO EtudiantInfo VALUES(27,'DJERBI','Ilies','A');
INSERT INTO EtudiantInfo VALUES(28,'DOLIVET','Milo','C');
INSERT INTO EtudiantInfo VALUES(29,'DUBOIS','Ryann','A');
INSERT INTO EtudiantInfo VALUES(30,'EL HOMADI--LEVEQUE','Ayette','C');
INSERT INTO EtudiantInfo VALUES(31,'EMERAUD','Jean-Louis','A');
INSERT INTO EtudiantInfo VALUES(32,'ESKHADJIEV','Abdul-Malik','D');
INSERT INTO EtudiantInfo VALUES(33,'FERRON','Sarah','A');
INSERT INTO EtudiantInfo VALUES(34,'FRANCERIES','Iban','D');
INSERT INTO EtudiantInfo VALUES(35,'GARCIA','Ellaïs','A');
INSERT INTO EtudiantInfo VALUES(36,'GAUFFENY','PAuL','A');
INSERT INTO EtudiantInfo VALUES(37,'GIRE','Aliaume','A');
INSERT INTO EtudiantInfo VALUES(38,'GODEFROY','Marius','D');
INSERT INTO EtudiantInfo VALUES(39,'GOMEZ--JEGO','Inaki','A');
INSERT INTO EtudiantInfo VALUES(40,'GUHENEUF-LE BREC','NaTHAN','A');
INSERT INTO EtudiantInfo VALUES(41,'GUILLEMAUD','Glenn','B');
INSERT INTO EtudiantInfo VALUES(42,'GUILLOUET','Ismaël','C');
INSERT INTO EtudiantInfo VALUES(43,'GUINOT','William','D');
INSERT INTO EtudiantInfo VALUES(44,'GÜNES','Omer','B');
INSERT INTO EtudiantInfo VALUES(45,'HAMON','Louis','C');
INSERT INTO EtudiantInfo VALUES(46,'HEULAN','Malki','C');
INSERT INTO EtudiantInfo VALUES(47,'HUET CHAPUIS','Martin','C');
INSERT INTO EtudiantInfo VALUES(48,'HUYNH-BA','Baptiste','C');
INSERT INTO EtudiantInfo VALUES(49,'ISMAIL OMAR','Tasnim','B');
INSERT INTO EtudiantInfo VALUES(50,'JAN','Océane','C');
INSERT INTO EtudiantInfo VALUES(51,'JULES--VACHET','Matthéo','A');
INSERT INTO EtudiantInfo VALUES(52,'KERVEILLANT','Elie','D');
INSERT INTO EtudiantInfo VALUES(53,'KOLB','Mattis','D');
INSERT INTO EtudiantInfo VALUES(54,'LABBÉ','Pierre-Louis','D');
INSERT INTO EtudiantInfo VALUES(55,'LABHINI','Lucas','C');
INSERT INTO EtudiantInfo VALUES(56,'LACOUR','Alexandre','C');
INSERT INTO EtudiantInfo VALUES(57,'LE BRECH','Camille','D');
INSERT INTO EtudiantInfo VALUES(58,'LE MAGUER','NATHAN','A');
INSERT INTO EtudiantInfo VALUES(59,'LE PROVOST','Louen','D');
INSERT INTO EtudiantInfo VALUES(60,'LEFÈVRE','Nathan','C');
INSERT INTO EtudiantInfo VALUES(61,'LEFRANC','Théau','B');
INSERT INTO EtudiantInfo VALUES(62,'LEGEAY','Quentin','B');
INSERT INTO EtudiantInfo VALUES(63,'LEGRAND','Gabin','B');
INSERT INTO EtudiantInfo VALUES(64,'LESAUVAGE','Léandre','B');
INSERT INTO EtudiantInfo VALUES(65,'LOISANCE--PINEL','Melen','D');
INSERT INTO EtudiantInfo VALUES(66,'LORCY','Ilan','C');
INSERT INTO EtudiantInfo VALUES(67,'MAREC','Amaury','B');
INSERT INTO EtudiantInfo VALUES(68,'MARIOT','Estéban','B');
INSERT INTO EtudiantInfo VALUES(69,'MARQUE','Dylan','B');
INSERT INTO EtudiantInfo VALUES(70,'MELLAH','Rayanne','C');
INSERT INTO EtudiantInfo VALUES(71,'MICHELET','Eliaz','D');
INSERT INTO EtudiantInfo VALUES(72,'MIGNIER--NHEAN','Aymeric','C');
INSERT INTO EtudiantInfo VALUES(73,'MORHAN','William','B');
INSERT INTO EtudiantInfo VALUES(74,'MORNET','Quentin','C');
INSERT INTO EtudiantInfo VALUES(75,'MOUELLO','Samuel','B');
INSERT INTO EtudiantInfo VALUES(76,'NACHIN','Alexia','D');
INSERT INTO EtudiantInfo VALUES(77,'NATIVELLE','Elliot','C');
INSERT INTO EtudiantInfo VALUES(78,'NDUNGINI','Jérémy-Yoan','B');
INSERT INTO EtudiantInfo VALUES(79,'NOUVION','Mateo','B');
INSERT INTO EtudiantInfo VALUES(80,'PARCOLLET','Noé','C');
INSERT INTO EtudiantInfo VALUES(81,'PATINEC','Francois','A');
INSERT INTO EtudiantInfo VALUES(82,'PÉRON','Romain','B');
INSERT INTO EtudiantInfo VALUES(83,'PICARD','Benjamin','A');
INSERT INTO EtudiantInfo VALUES(84,'PIVRON','Jonas','A');
INSERT INTO EtudiantInfo VALUES(85,'PONDAVEN','Thibault','D');
INSERT INTO EtudiantInfo VALUES(86,'REBOURS','Maxence','C');
INSERT INTO EtudiantInfo VALUES(87,'RIVALLAND','Kyllian','A');
INSERT INTO EtudiantInfo VALUES(88,'RODRIGUES','Mattéo','B');
INSERT INTO EtudiantInfo VALUES(89,'ROUSSEL','Paul','C');
INSERT INTO EtudiantInfo VALUES(90,'SAUNDERS','Benjamin','C');
INSERT INTO EtudiantInfo VALUES(91,'SCHELL','Yanis','A');
INSERT INTO EtudiantInfo VALUES(92,'SITKO','Galian','A');
INSERT INTO EtudiantInfo VALUES(93,'SZCZEPANSKI','Serge','D');
INSERT INTO EtudiantInfo VALUES(94,'THÉBAULT','Claire','A');
INSERT INTO EtudiantInfo VALUES(95,'TRÉVIAN','Benjamin','D');
INSERT INTO EtudiantInfo VALUES(96,'TROUSSELET','Kylian','A');
INSERT INTO EtudiantInfo VALUES(97,'TUCAT','Mathieu','A');
INSERT INTO EtudiantInfo VALUES(98,'VARRIER','Mathis','C');
INSERT INTO EtudiantInfo VALUES(99,'VIDAL','Titouan','D');
INSERT INTO EtudiantInfo VALUES(100,'VION','Iann','D');
INSERT INTO EtudiantInfo VALUES(101,'VRIGNAUD','pAul','B');
INSERT INTO EtudiantInfo VALUES(102,'ZENSEN--DA SILVA','Gabriel','D');
INSERT INTO EtudiantInfo VALUES(103,'ZIEGELMEYER','Eliot','D');

/* QUESTION 10 */
/* SQL */
SELECT DISTINCT UPPER(nomEtu)
FROM EtudiantInfo, EnseignantInfo
WHERE prenom = prenomEns
AND UPPER(groupe) = 'A';
/*
PATINEC

1 Tuple
*/

/* Algèbre Relationnel */
/* (EnseignantInfo[[prenom = prenomEns]]EtudiantInfo)(groupe='A')[nomEtu] */


/* QUESTION 11 */
/* SQL */
SELECT UPPER(nomEtu), UPPER(prenom)
FROM EtudiantInfo, EnseignantInfo
WHERE prenomEns = prenom
AND (noEns = 3 OR noEns = 4);
/*
PONDAVEN THIBAULT

1 Tuple
*/

/* Algèbre Relationnel */
/* (EnseignantInfo(noEns = 3 OR noEns = 4)[[prenomEns = prenom]]EtudiantInfo)[nomEtu][prenom] */


/* QUESTION 12 */
/* SQL */
SELECT DISTINCT UPPER(e2.nomEns)
FROM EnseignantInfo e1, EnseignantInfo e2
WHERE UPPER(e1.prenomEns) = UPPER(e2.prenomEns)
AND e1.noEns = 5
AND e2.noEns != 5;

/*
JOUCLA

1 Tuple
*/

/* Algèbre Relationnel */
/* : (EnseignantInfo e1[[e1.prenomEns = e2.prenomEns]]EnseignantInfo e2){e1.noEns = 5 && e2.noEns != 5}[nomEns]*/

/* QUESTION 13 */
/* SQL */
SELECT DISTINCT UPPER(e1.prenom), UPPER(e1.nomEtu)
FROM EtudiantInfo e1, EtudiantInfo e2
WHERE UPPER(e1.prenom) = UPPER(e2.prenom)
AND UPPER(e1.noEtu) != UPPER(e2.noEtu);

/*
NATHAN                         GUHENEUF-LE BREC                        
NATHAN                         ALEXANDRE                               
WILLIAM                        MORHAN                                  
BENJAMIN                       PICARD                                  
LOUIS                          BILLARD    

20 Tuples
*/

/* Algèbre Relationnel */
/* (EtudiantInfo e1 [[e1.EtudiantInfo = e2.EtudiantInfo && e1.noEtu != e2.noEtu]]EtudiantInfo e2)[e1.prenom][e2.prenom] */


/* QUESTION 14 */
/* SQL */
SELECT DISTINCT UPPER(e1.prenom)
FROM EtudiantInfo e1, EtudiantInfo e2
WHERE UPPER(e1.prenom) = UPPER(e2.prenom)
AND UPPER(e1.groupe) != UPPER(e2.groupe);

/*
WILLIAM
BENJAMIN
SAMUEL
LOUIS
PAUL

8 Tuples
*/

/* Algèbre Relationnel */
/* (EtudiantInfo e1 [[e1.EtudiantInfo = e2.EtudiantInfo && e1.groupe != e2.grouoe]]EtudiantInfo e2)[e1.prenom][e2.prenom] */



/* QUESTION 15 */
/* SQL */
SELECT DISTINCT UPPER(prenom)
FROM ( SELECT *
FROM EtudiantInfo
ORDER BY prenom ASC)
WHERE ROWNUM <= 5;

/*
ALEXANDRE
ALEXIA
ALIAUME
ABDUL-MALIK
ALEXIS
*/

/* QUESTION 16 */
/* SQL */
SELECT DISTINCT UPPER(prenom)
FROM ( SELECT *
FROM EtudiantInfo
ORDER BY prenom DESC)
WHERE ROWNUM <= 6;

/*
WILLIAM
THOMAS
VICTOR
TITOUAN
YANIS
*/

/* QUESTION 17 */
/* SQL */
SELECT *
FROM (
    SELECT DISTINCT UPPER(prenom)
    FROM EtudiantInfo
    WHERE prenom LIKE '%S%'
    
    ORDER BY UPPER(prenom) DESC
)
WHERE ROWNUM <= 6

UNION

SELECT *
FROM (
    SELECT DISTINCT UPPER(prenomEns)
    FROM EnseignantInfo
    WHERE prenomEns LIKE '%S%'
    
    ORDER BY UPPER(prenomEns) DESC
)
WHERE ROWNUM <= 6;

/*
SAMUEL
SARAH
SEBASTIEN
SERGE
SOPHIE
*/

/* QUESTION 18 */
SELECT DISTINCT noEns, UPPER(nomEtu)
FROM EnseignantInfo, EtudiantInfo
WHERE UPPER(prenomEns) = UPPER(prenom);

/*
3 PONDAVEN                                
8 PATINEC   
*/

/* QUESTION 19 */
SELECT noEns, UPPER(nomEtu), UPPER(prenom)
FROM EnseignantInfo, EtudiantInfo
WHERE UPPER(prenomEns) = UPPER(prenom(+));

/*
         8 PATINEC                                  FRANCOIS                      
         3 PONDAVEN                                 THIBAULT                      
         6                                                                        
        20                                                                        
        11                                                                        
         9                                                                        
        13                                                                        
         2                                                                        
         1                                                                        
        19                                                                        
        14                                                                        

     NOENS UPPER(NOMETU)                            UPPER(PRENOM)                 
---------- ---------------------------------------- ------------------------------
        10                                                                        
        15                                                                        
        16                                                                        
        12                                                                        
        18                                                                        
         5                                                                        
         7                                                                        
         4                                                                        
        17                                                               

20 Tuple
*/