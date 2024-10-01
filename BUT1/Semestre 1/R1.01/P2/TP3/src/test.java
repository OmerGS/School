import java.util.*;
import java.io.*;
class test {
    
    ArrayList<String> trajets = new ArrayList<String>();
    ArrayList<Integer> horaires = new ArrayList<Integer>();

    Duree d1 = new Duree(5000);
	Duree d2 = new Duree(10,00,00);

    void principal(){
        remplirLesCollections("./TrajetsEtHoraires.txt");
        //afficherHorairesEtDureeTousTrajets();
        //testRemplirLesCollections();
        //afficherEtDureeTrajets2Gares("Vannes", "Nantes");
        
        //chercherCorrespondances("Vannes", d2 = new Duree(10,00,00));
        testChercherCorrespondances();
        //obtenirInfosUnTrajet("5");
        //obtenirInfosUnHoraire("5");
        //trouverTousLesTrajets("Redon");
    }

    /**
    * Methode  qui remplit les 2 collections trajets et
    * horaires a partir d’une lecture de fichier texte.
    * 
    * @param nomFich Le chemin d'acces du fichier ou se trouve les trajets et horaires
    */
    void remplirLesCollections(String nomFich){
        trajets.clear();
        horaires.clear();

        int nbComposant;
        int i = 1;
        boolean eof = false;
        String str;
        BufferedReader tampon;
        FileReader file;

        try{
            tampon = new BufferedReader(new FileReader(nomFich));

            while(!eof){
                str = tampon.readLine();

                if(str == null){
                    eof = true;
                } else {
                        if(i%2 == 0){
                            nbComposant = 5;
                        } else {
                            nbComposant = 4;
                        }
                        
                        for(int j = 0; j < nbComposant; j++){
                            String[] infos = str.split("/");
                            if(nbComposant == 5){
                                horaires.add(Integer.parseInt(infos[j].trim()));
                            } else {
                                trajets.add(infos[j].trim());
                            }
                        }
                    }
                i++;
            }
            tampon.close();

        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
	* Methode qui permet de faire une separation entre deux methodes de test
	* pour un meilleur confort de lecture lors de l'éxecution de plusieurs 
    * test ou l'affichage de plusieurs horaires.
	**/
    void separationHoraire(){
		System.out.println("\n ---------- \n");
	}
    
    /**
    * Methode qui affiche les horaires de differents trajets qui sont
    * deja stocke dans deux tableaux differents.
    */
    void afficherHorairesEtDureeTousTrajets(){
        int nombreElementsTrajets = (trajets.size()/4);
        int nombreElementHoraires = (horaires.size()/5);

        if(nombreElementHoraires != nombreElementsTrajets){
            System.out.println("Affichage : Le nombre d'horaire n'est pas egale au nombre de trajets");
        } else {
            for(int i = 0; i < nombreElementsTrajets; i++){
                for(int j = 0; j < nombreElementHoraires; j++){
                    if(Integer.parseInt(trajets.get(i*4)) == horaires.get(j*5)){

                        System.out.println("Train " + trajets.get((i*4)+1) + " numero " + trajets.get(i*4));
                        
                        int heureDepart = horaires.get((j*5)+1);
                        int minuteDepart = horaires.get((j*5)+2);
                        Duree depart = new Duree(heureDepart, minuteDepart, 0);
                        String texteDuree = depart.enTexte('H');

                        System.out.println("Depart de " + trajets.get((i*4)+2) + " a " + texteDuree);

                        int heureArriver = horaires.get((j*5)+3);
                        int minuteArriver = horaires.get((j*5)+4);
                        Duree arrive = new Duree(heureArriver, minuteArriver, 0);
                        texteDuree = arrive.enTexte('H');

                        System.out.println("Arrivee a " + trajets.get((i*4)+3) + " a " + texteDuree);

                        arrive.soustraire(depart);
                        texteDuree = arrive.enTexte('H');

                        System.out.println("Duree du trajet : " + texteDuree);

                        separationHoraire();

                    }
                }
            }
        }
    }

    /**
    * Methode qui test les methodes suivantes :
    * - remplirLesCollections()
    * - afficherHorairesEtDureeTousTrajets()
    */
    void testRemplirLesCollections(){
        remplirLesCollections("./TrajetsEtHoraires.txt");
        afficherHorairesEtDureeTousTrajets();
    }

    /**
    * C’est le même principe que la méthode afficherHorairesEtDureeTousTrajets 
    * mais on se limite cette fois aux seules gares de départ et d’arrivée 
    * passées en paramètre (trajets directs)
    * 
    * @param gareDep La gare de depart
    * @param gareDest La gare de destination
    */
    void afficherEtDureeTrajets2Gares(String gareDep, String gareDest){
        Duree d1 = new Duree(0);
        Duree d2 = new Duree(0);

        boolean trainExistant = false;

        System.out.println("Recherche des trajets de la gare " + gareDep + " a " + gareDest);

        int nombreElementsTrajets = (trajets.size()/4);
        int nombreElementHoraires = (horaires.size()/5);
        
        for(int i = 0; i < nombreElementsTrajets; i++){
            for(int j = 0; j < nombreElementHoraires; j++){
                if(Integer.parseInt(trajets.get(i*4)) == horaires.get(j*5)){
                    d1 = new Duree(horaires.get(5*j+1), horaires.get(5*j+2), 0);
                    d2 = new Duree(horaires.get(5*j+3), horaires.get(5*j+4), 0);
                    break;
                }
            }
        


            if(gareDep.equals(trajets.get(4*i+2)) && gareDest.equals(trajets.get(4*i+3))){
                System.out.println("Train " + trajets.get(4*i+1) + " numero " + trajets.get(0+4*i) + " : ");
                System.out.println("Depart de " + trajets.get(4*i+2) + " a " + d1.enTexte('H')); //Ville Depart
                System.out.println("Arrive a " + trajets.get(4*i+3) + " a " + d2.enTexte('H')); //Ville Arrive
                d2.soustraire(d1);
                System.out.println("Duree de trajet  : " + d2.enTexte('H') + "\n");
                trainExistant = true;
            }
        }

        if(!trainExistant){
                System.out.println("Aucun train ! ");        
            }
    }

    /**
    * Cette methode trouve les informations concernant le trajet d'un train grace a son numero 
    * d'identification et renvoie un tableau de 3 case rempli de cette facon :
    * Indice 0 : Type de Train
    * Indice 1 : Gare de Depart
    * Indice 2 : Gare de Destination
    * 
    * @param idTrajet l'identifiant du trajet
    * @return tableau rempli de chaine de caractere avec les informations trajets 
    */
    String[] obtenirInfosUnTrajet(String idTrajet) {
        int nombreElementsTrajets = trajets.size() / 4;
        String[] tableauRempli = new String[3];
        boolean idTrouve = false;

        for (int j = 0; j < nombreElementsTrajets; j++) {
            if (idTrajet.equals(trajets.get(j * 4))) {
                for (int i = 0; i < 3; i++) {
                    tableauRempli[i] = trajets.get((j * 4) + i + 1);
                    //DEBUG : System.out.println(trajets.get((j * 4) + i + 1));
                }
                idTrouve = true;
            }
        }

        if (!idTrouve) {
            System.out.println("obtenirInfosTrajets : Aucun train pour l'id :  " + idTrajet);
        }

        return(tableauRempli);
    }



    /**
    * Cette methode trouve les informations concernant l'horaire d'un train grace a son numero 
    * d'identification et renvoie un tableau de 4 cases rempli de cette facon :
    * Indice 0 : Heure Depart
    * Indice 1 : Minute Depart
    * Indice 2 : Heure Arrivee
    * Indice 3 : Minute Arrivee
    * 
    * @param idTrajet l'identifiant du trajet
    * @return tableau rempli d'entier avec les informations sur l'horaire 
    */
    int[] obtenirInfosUnHoraire(String idTrajet) {
        int nombreElementHoraires = horaires.size() / 5;
        int[] tableauRempli = new int[4];
        boolean idTrouve = false;

        for (int j = 0; j < nombreElementHoraires; j++) {
            if (Integer.parseInt(idTrajet) == horaires.get(j * 5)) {
                for (int i = 0; i < 4; i++) {
                    tableauRempli[i] = horaires.get((j * 5) + i + 1);
                    //DEBUG : System.out.println(horaires.get((j * 5) + i + 1));
                }
                idTrouve = true;
            }
        }

        if (!idTrouve) {
            System.out.println("obtenirInfosUnHoraire : Aucune horaire pour l'id :  " + idTrajet);
        }

        return(tableauRempli);
    }

    /**
    * Methode qui trouve tout les trajets qui partent d'une gare donne en parametre
    * et qui leurs identifiants dans une ArrayList nommee tableauRempli
    * 
    * @param gareDep Gare de depart
    * @return ArrayList contenant les identifiants des trains qui partent de la gare
    */
    ArrayList<String> trouverTousLesTrajets(String gareDep){
        int nombreElementsTrajets = trajets.size()/4;
        ArrayList<String> tableauRempli = new ArrayList<String>();
        boolean horaireExistant = false;
        
        for(int i = 0; i < nombreElementsTrajets; i++){
            if(gareDep.equals(trajets.get(4*i+2))){
                tableauRempli.add(trajets.get(4*i));
                //DEBUG : System.out.println(trajets.get(4*i));
                horaireExistant = true;
            }
        }

        if(!horaireExistant){
            System.out.println("trouverTousLesTrajets : Aucune Horaires disponible pour la gare de " + gareDep);
        }

        /*
        //DEBUG : Code de test pour voir si la ArrayList etait belle est bien rempli
        for (String element : tableauRempli) {
            System.out.println(element);
        }
        */

        return(tableauRempli);
    }

    /**
    * Cette methode permet de trouver des correspondances en se basant sur une gare et une heure de depart
    * qui lui est donné en paramètre.
    * 
    * @param gare La gare de depart
    * @param heure L'heure a partir du quelle on veut voir les horaires
    * 
    * @return Une ArrayList qui contient les id des trajets qui correspondent au critere  
    */
    ArrayList<String> chercherCorrespondances(String gare, Duree heure){
        boolean affichageUnique = true;

        ArrayList<String> ArrayListRempli = new ArrayList<String>();
        ArrayList<String> gareDepartId = trouverTousLesTrajets(gare);
        
        int[] horaireGareDepart = new int[5];
        String[] infoTrajet = new String[3];
        
        boolean correspondanceExistante = false;

        for(int i = 0; i < gareDepartId.size(); i++){
            horaireGareDepart = obtenirInfosUnHoraire(gareDepartId.get(i));
            infoTrajet = obtenirInfosUnTrajet(gareDepartId.get(i));
            
            int heureDepart = (horaireGareDepart[0]);
            int minuteDepart = (horaireGareDepart[1]);
            Duree total = new Duree(heureDepart, minuteDepart, 0);
        
            if(heure.compareA(total) == -1){
                ArrayListRempli.add(gareDepartId.get(i));

                if(affichageUnique == true){
                    System.out.println("\nCorrespondance de " + gare + " à " + heure.enTexte('H'));
                    System.out.println("\n");
                }

                System.out.println("Le train numero " + gareDepartId.get(i) + " de type " + infoTrajet[0]);
                System.out.println("Depart de " + gare + " a " + heure.enTexte('H'));
                Duree heureArrive = new Duree(horaireGareDepart[2], horaireGareDepart[3], 0);
                System.out.print("Destination " + infoTrajet[2] + " arrive prevu a " + heureArrive.enTexte('H'));
                
                correspondanceExistante = true;
                separationHoraire();
                affichageUnique = false;

                correspondanceExistante = true;
            }
        }

        if(!correspondanceExistante){
            System.out.println("Aucune correspondance disponible pour " + gare + " a " + heure.enTexte('H'));
            separationHoraire();
        }
        
        //DEBUG : Code de test pour voir si la ArrayList etait belle est bien rempli
        /*
        for (String element : ArrayListRempli) {
            System.out.println(element);
        }
        */

        return(ArrayListRempli);
    } 

    /**
    * Methode de test qui permet de tester la methode chercherCorrespondances() dans
    * plusieurs cas. 
    */
    void testChercherCorrespondances(){
        Duree d2 = new Duree(14,10,25);
        chercherCorrespondances("Redon", d2);

        d2 = new Duree(18,00,18);
        chercherCorrespondances("Vannes", d2);

        d2 = new Duree(8,04,18);
        chercherCorrespondances("Rennes", d2);

        d2 = new Duree(8,04,18);
        chercherCorrespondances("Quimper", d2);
    }

}