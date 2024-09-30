import java.util.*;
import java.io.*;

/**
* Cette class fait une lecture de fichier contenant differents trajet et
* ces horaires puis les affiches de differentes manieres.
* 
* @author O.Gunes
*/
class Planification {
    /**
    * ArrayList contenant les trajets de tous les trajets qui sont dans le fichier qu'on a lu 
    */
    ArrayList<String> trajets = new ArrayList<String>();

    /**
    * ArrayList contenant les horaires de tous les trajets qui sont dans le fichier qu'on a lu 
    */
    ArrayList<Integer> horaires = new ArrayList<Integer>();

    /**
    * Point d'entr&eacute;e du programme. 
    */
    void principal(){
        //remplirLesCollections("./TrajetsEtHoraires.txt");
        //afficherHorairesEtDureeTousTrajets();
        testRemplirLesCollections();
        
        //afficherEtDureeTrajets2Gares("Vannes", "Nantes");
        testAfficherEtDureeTrajets2Gares();
        
        //obtenirInfosUnTrajet("5");
        //obtenirInfosUnHoraire("5");
        //chercherCorrespondances("Vannes", d2 = new Duree(10,00,00));
        testChercherCorrespondances();

        //trouverTousLesTrajets("Redon");
        testTrouverTousLesTrajets();
    }

    /**
    * Methode qui remplit les 2 collections trajets et
    * horaires &agrave; partir d'une lecture de fichier texte.
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
	* Methode qui permet de faire une s&eacute;paration entre deux methodes de test
	* pour un meilleur confort de lecture lors de l'&eacute;xecution de plusieurs 
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
            System.out.println("Affichage : Le nombre d'horaire n'est pas \u00E9gale au nombre de trajets");
        } else {
            for(int i = 0; i < nombreElementsTrajets; i++){
                for(int j = 0; j < nombreElementHoraires; j++){
                    if(Integer.parseInt(trajets.get(i*4)) == horaires.get(j*5)){

                        System.out.println("Train " + trajets.get((i*4)+1) + " num\u00E9ro " + trajets.get(i*4));
                        
                        int heureDepart = horaires.get((j*5)+1);
                        int minuteDepart = horaires.get((j*5)+2);
                        Duree depart = new Duree(heureDepart, minuteDepart, 0);
                        String texteDuree = depart.enTexte('H');

                        System.out.println("Depart de " + trajets.get((i*4)+2) + " \u00E0 " + texteDuree);

                        int heureArriver = horaires.get((j*5)+3);
                        int minuteArriver = horaires.get((j*5)+4);
                        Duree arrive = new Duree(heureArriver, minuteArriver, 0);
                        texteDuree = arrive.enTexte('H');

                        System.out.println("Arriv\u00E9e \u00E0 " + trajets.get((i*4)+3) + " \u00E0 " + texteDuree);

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
    * C'est le meme principe que la m&eacute;thode afficherHorairesEtDureeTousTrajets() 
    * mais on se limite cette fois aux seules gares de d&eacute;part et d’arriv&eacute;e 
    * pass&eacute;es en param&egrave;tre (trajets directs)
    * 
    * @param gareDep La gare de depart
    * @param gareDest La gare de destination
    */
    void afficherEtDureeTrajets2Gares(String gareDep, String gareDest){
        Duree d1 = new Duree(0);
        Duree d2 = new Duree(0);

        boolean trainExistant = false;

        System.out.println("Recherche des trajets de la gare de " + gareDep + " \u00E0 " + gareDest);

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
                System.out.println("Train " + trajets.get(4*i+1) + " num\u00E9ro " + trajets.get(0+4*i) + " : ");
                System.out.println("Depart de " + trajets.get(4*i+2) + " \u00E0 " + d1.enTexte('H')); //Ville Depart
                System.out.println("Arrive a " + trajets.get(4*i+3) + " \u00E0 " + d2.enTexte('H')); //Ville Arrive
                d2.soustraire(d1);
                System.out.println("Duree de trajet : " + d2.enTexte('H') + "\n");
                trainExistant = true;
            }
        }

        if(!trainExistant){
                System.out.println("Aucun train ! \n");        
            }
    }

    /**
    * Cette methode trouve les informations concernant le trajet d'un train grace a son numero 
    * d'identification et renvoie un tableau de 3 case rempli de cette facon :
    * Indice 0 : Type de Train, 
    * Indice 1 : Gare de Depart et 
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
    * Indice 0 : Heure Depart, 
    * Indice 1 : Minute Depart, 
    * Indice 2 : Heure Arrivee et 
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
    ArrayList<String> chercherCorrespondances(String gare, Duree heure) {
        ArrayList<String> gareDepartId = trouverTousLesTrajets(gare);
        ArrayList<String> correspondances = new ArrayList<>();

        for (String trajetId : gareDepartId) {
            int[] horaireGareDepart = obtenirInfosUnHoraire(trajetId);

            int heureDepart = horaireGareDepart[0];
            int minuteDepart = horaireGareDepart[1];
            Duree total = new Duree(heureDepart, minuteDepart, 0);

            if (heure.compareA(total) == -1) {
                correspondances.add(trajetId);
            }
        }

        return correspondances;
    }

    /**
     * M&eacute;thode qui affiche les trajets en fonction des IDs de trajets fournis.
     *
     * @param correspondances Liste des IDs de trajets &agrave; afficher
     */
    void afficherCorrespondances(ArrayList<String> correspondances) {
        boolean affichageUnique = true;

        for (int i = 0; i < correspondances.size(); i++) {
            String trajetId = correspondances.get(i);
            int[] horaireGareDepart = obtenirInfosUnHoraire(trajetId);
            String[] infoTrajet = obtenirInfosUnTrajet(trajetId);

            int heureDepart = horaireGareDepart[0];
            int minuteDepart = horaireGareDepart[1];
            Duree heureDepartDuree = new Duree(heureDepart, minuteDepart, 0);

            if (affichageUnique) {
                System.out.println("\nCorrespondance de " + infoTrajet[1] + " \u00E0 partir de " + heureDepartDuree.enTexte('H'));
                System.out.println("\n");
                affichageUnique = false;
            }

            System.out.println("Le train num\u00E9ro " + trajetId + " de type " + infoTrajet[0]);
            System.out.println("D\u00E9part de " + infoTrajet[1] + " \u00E0 " + heureDepartDuree.enTexte('H'));
            Duree heureArrive = new Duree(horaireGareDepart[2], horaireGareDepart[3], 0);
            System.out.println("Arriv\u00E9 pr\u00E9vu \u00E0 " + infoTrajet[2] + " \u00E0 " + heureArrive.enTexte('H'));
            heureArrive.soustraire(heureDepartDuree);
            System.out.println("Le temps de trajet est de " + heureArrive.enTexte('H'));

            separationHoraire();
        }

        if (correspondances.isEmpty()) {
            System.out.println("Aucune correspondance disponible. ");
            separationHoraire();
        }
    }

    

















    // LES  METHODES DE TEST



    /**
    * Methode qui test les methodes suivantes :
    * - remplirLesCollections()
    * - afficherHorairesEtDureeTousTrajets()
    */
    void testRemplirLesCollections(){
        System.out.println(" ------ testRemplirLesCollections ------ ");
        remplirLesCollections("./TrajetsEtHoraires.txt");
        afficherHorairesEtDureeTousTrajets();
    }

    /**
    * Methode de test qui test la methode afficherEtDureeTrajets2Gares()
    * avec differentes gare de depart et arrive. 
    */
    void testAfficherEtDureeTrajets2Gares(){
        System.out.println("\n\n\n\n\n");
        System.out.println(" ------ testAfficherEtDureeTrajets2Gares ------ ");
        afficherEtDureeTrajets2Gares("Vannes", "Nantes");

        afficherEtDureeTrajets2Gares("Paris", "Nice");

        afficherEtDureeTrajets2Gares("Redon", "Nantes");
    }

    /**
    * Methode de test qui permet de tester la methode chercherCorrespondances() dans
    * plusieurs cas. 
    */
    void testChercherCorrespondances(){
        System.out.println("\n\n\n\n\n");
        System.out.println(" ------ testChercherCorrespondances ------ ");

        Duree d2 = new Duree(14,10,25);
        afficherCorrespondances(chercherCorrespondances("Redon", d2));

        d2 = new Duree(18,00,18);
        afficherCorrespondances(chercherCorrespondances("Vannes", d2));

        d2 = new Duree(8,04,18);
        afficherCorrespondances(chercherCorrespondances("Rennes", d2));

        d2 = new Duree(8,04,18);
        afficherCorrespondances(chercherCorrespondances("Quimper", d2));
    }

    /**
    * Methode de test qui permet de tester la methode trouverTousLesTrajets() dans
    * differents cas. 
    */
    void testTrouverTousLesTrajets(){
        String[] Ville = {"Vannes", "Redon", "Rennes", "Paris", "Nantes"};

        System.out.println(" ------ testTrouverTousLesTrajets ------ ");

        for(int i = 0; i < Ville.length; i++){
            System.out.println("\nIDs des trajets partant de " + Ville[i]);
            ArrayList<String> trajetVille = trouverTousLesTrajets(Ville[i]);

            for(int j = 0; j < trajetVille.size()-1; j++){
                System.out.print(trajetVille.get(j) + ", ");
            }
            if(!(trajetVille.isEmpty())){
                System.out.print(trajetVille.get(trajetVille.size()-1));
            }

            System.out.println();
        } 
    }
} 