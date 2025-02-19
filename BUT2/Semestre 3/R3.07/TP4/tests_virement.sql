--Le virement est impossible car dépassant 1000 euros ! Veuillez contacter l'agent Dupont:)
EXECUTE proc_virement(13,14,2000, 1);

-- Solde avant
select numCompte, TO_NUMBER(solde) from Compte where numCompte IN ( 13, 14 );
--OK
EXECUTE proc_virement(13,14,1000, 1); 
--Virement effectué
select numCompte, TO_NUMBER(solde) from Compte where numCompte IN ( 13, 14 );

-- Le virement est impossible car le solde du compte source est insuffisant !
EXECUTE proc_virement(4,14,1000, 5);

ROLLBACK;
