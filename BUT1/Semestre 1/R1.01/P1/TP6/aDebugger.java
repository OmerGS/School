/**
 * Compte le nombre de jours entre deux dates
 * @author Stagiaire LN
 */
class aDebugger {
    
    void principal () {
		int jour1 = SimpleInput.getInt("Entrez le jour de la premiere date : ");
		int mois1 = SimpleInput.getInt("Entrez le mois de la premiere date : ");
		int annee1 = SimpleInput.getInt("Entrez l'annee de la premiere date : ");
		
		int jour2 = SimpleInput.getInt("Entrez le jour de la deuxieme date : ");
		int mois2 = SimpleInput.getInt("Entrez le mois de la deuxieme date : ");
		int annee2 = SimpleInput.getInt("Entrez l'annee de la deuxieme date : ");
		
		System.out.println(differenceJour(jour1, mois1, annee1, jour2, mois2, annee2));
    }
    
    /**
    * Fonction qui verifie si une année est bisextile
    * @param annee : L'année qu'on veut verifier 
    * @return : Vrai si il est bisextile, Faux sinon.
    */
    boolean anneeBisextile (int annee){
		boolean bisextile = false;
		
		if(annee%4 == 0 && annee%100 != 0){
			bisextile = true;
		}
		
        if(annee%400 == 0){
			bisextile = true;
		}
		return(bisextile);
    }
    
    /**
    * Cette fonction permet de savoir le nombre de jour dans une année
    * @param annee : L'année dont on veut savoir le nombre de jour.
    * @return : 366 si bisextile, 365 sinon.
    */
    int nombreJourParAnnee (int annee){
        int nbJour = 0;
        boolean bisextile = anneeBisextile(annee);
        
        if (bisextile == true){
			nbJour = 366;
		}else{
			nbJour = 365;
		}
		
        return(nbJour);
    }
       
    /**
    * Cette fonction permet de savoir le nombre de jour dans un mois.
    * @param 
    * @param
    **/
    int nombreJourParMois (int mois, int annee){
        int nbJour = 0;
        boolean bisextile = anneeBisextile(annee);
        
        if (mois == 2){
			if (bisextile == true){
			nbJour = 29;
		}else{
			nbJour = 28;
		}
		}
		
		int moisModulo = (mois%2);
        
        if(mois <= 7){
			nbJour = 30 + moisModulo;
        }else{
			nbJour = 31 - moisModulo;
        }   
        
        return (nbJour);   
    }
    
    /**
    * Fonction qui calcule la difference de jour entre deux dates.
    * @param jourDate1 : Jour de la premiere date.
    * @param moisDate1 : Mois de la premiere date.
    * @param anneeDate1 : Annee de la premiere date.
    * 
    * @param jourDate2 : Jour de la deuxieme date.
    * @param moisDate2 : Mois de la deuxieme date.
    * @param anneeDate2 : Annee de la deuxieme date.
    * 
    * @return : Le nombre de jour de difference entre les deux dates.
    * 
    **/
    int differenceJour (int jourDate1, int moisDate1, int anneeDate1, int jourDate2, int moisDate2, int anneeDate2){
       int nbJour = 0;
        int i = anneeDate1+1;

		if(anneeDate1 == anneeDate2 && moisDate1 == moisDate2){
			nbJour = jourDate2-jourDate1;
		} else{
			while (i < anneeDate2) {
				nbJour += nombreJourParAnnee (i);
				i = i + 1;
			}
			
			if(anneeDate1 != anneeDate2){
				i = moisDate1 + 1;
				while (i <= 12) {
					nbJour += nombreJourParMois (i,anneeDate1);
					i++;
				}
				
				i = 1;
				while (i < moisDate2) {
					nbJour += nombreJourParMois (i, anneeDate2);
					i++;
				}
			} else if (moisDate1 != moisDate2){
				i = moisDate1+1;
				while (i < moisDate2) {
					nbJour += nombreJourParMois(i, anneeDate2);
					i++;
				}
			}
			
			nbJour += nombreJourParMois(moisDate1,anneeDate1)-jourDate1;
			nbJour += jourDate2 ;
		}
        return(nbJour);
    }
}   
