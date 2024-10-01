import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import mail.*;

public class MailScenario {

    private String nomUtilisateurCourant;

    private static ArrayList<MailClient> LesClients = new ArrayList<>();

    ArrayList<String> motsSpam = new ArrayList<>();

    MailServer server = new MailServer(motsSpam);

    public static void main(String[] args) {
        MailScenario mailScenario = new MailScenario();

        mailScenario.start();
    }

    public void start(){
        motsSpam.add("argent");
        motsSpam.add("riche");
        motsSpam.add("concours");
        motsSpam.add("gagner");

        MailClient omer = new MailClient(server, "omer");

        LesClients.add(omer);

        connectionOuInscripion();
    }

    public void connectionOuInscripion(){
        System.out.print("\033[H\033[2J"); 
        System.out.println("Bienvenue sur Mailer !");
        System.out.println("Appuyer sur 'A' pour vous connecter");
        System.out.println("Appuyer sur 'B' pour vous inscrire");
        String userPrompt = SimpleInput.getString("");
        if(userPrompt.equals("A")){
            connection();
        } else if(userPrompt.equals("B")){
            inscription();
        }
    }

    public void connection(){
        System.out.print("\033[H\033[2J"); 
        System.out.println("*** CONNECTION SUR MAILER ***");
        System.out.println("Saisissez 'echap' pour revenir en arriere !");
        String nameOfUser;
        do{
            nameOfUser = SimpleInput.getString("Entrez votre identifiant : ");

            if(getClientByName(nameOfUser) == true){
                this.nomUtilisateurCourant = nameOfUser;
                System.out.print("\033[H\033[2J");  
                System.out.println("Connecté avec succès");
                menuPrincipal();
            } else if(nameOfUser.equals("echap")) {
                connectionOuInscripion();
            } else {
                System.out.println("Erreur identifiant inconnu !");
            }

        } while(getClientByName(nameOfUser) == false);
    }

    private boolean getClientByName(String nom) {
        boolean existe = false;
        for (MailClient client : LesClients) {
            if (client.getUser().equals(nom)) {
                existe = true;
            }
        }
        return existe; // Retourne null si aucun client n'est trouvé avec cet identifiant
    }
    

    public void inscription(){
        System.out.print("\033[H\033[2J"); 
        System.out.println("*** INSCRIPTION SUR MAILER ***");
        String nameOfUser;
        boolean sorti = false;

        do{
            nameOfUser = SimpleInput.getString("Entrez votre identifiant (ne l'oubliez pas) : ");
            
            if(getClientByName(nameOfUser) == true){
                System.out.println("Erreur cet identifiant existe deja");
            } else {
                System.out.println("Inscription realisé avec succès !");
                MailClient client = new MailClient(server, nameOfUser);
                LesClients.add(client);
                System.out.print("\033[H\033[2J");
                sorti = true;
                connection();
            }
        } while(getClientByName(nameOfUser) == true && !sorti);
    }

    public void menuPrincipal(){
        System.out.print("\033[H\033[2J");
        int choix;
        System.out.println(nomUtilisateurCourant + ", bienvenue sur votre compte Mailer !");
        
        do{
            System.out.print("\033[H\033[2J");
            int nbMailNonLu = server.howManyMailItems(nomUtilisateurCourant);
            System.out.println("Vous avez " + nbMailNonLu + " mail non lu.");

            System.out.println("Appuyez sur '1' pour envoyer des mails");
            System.out.println("Appuyez sur '2' pour consultez vos mails");
            System.out.println("Appuyez sur '3' pour consultez vos mails indesirables");
            System.out.println("Appuyez sur '4' pour allez dans les parametres");
            System.out.println("Appuyez sur '5' pour vous deconnectez");
            
            Scanner inChoix = new Scanner(System.in);
            choix = inChoix.nextInt();

            if(choix == 1){
                mailSend();
            } else if(choix == 2){
                boiteDeReception();
            } else if(choix == 3){
                spamMail();
            } else if(choix == 4){
                parameters();
            } else if(choix == 5){
                logout();
            } else {
                System.out.println("Choix inconnu");
            }
        } while(choix < 5);
    }

    public void mailSend(){
        System.out.print("\033[H\033[2J"); 
        System.out.println("*** ENVOIE DE MAIL ***");
        MailItem mail;

        System.out.println("A qui voulez-vous envoyez le mail ? : ");
        Scanner inTo = new Scanner(System.in);
        String to = inTo.next();

        System.out.println("Destinataire : " + to);
        
        String message = SimpleInput.getString("Que voulez vous envoyez ? : ");
        /*System.out.println("Que voulez vous envoyez ? : ");
        Scanner inMessage = new Scanner(System.in);
        String message = inMessage.next();*/

        mail = new MailItem(nomUtilisateurCourant, to, message);

        server.post(mail);

        mail.print();
    }

    public void boiteDeReception(){
        System.out.print("\033[H\033[2J"); 
        System.out.println("*** BOITES DE RECEPTION ***");
        int nbMailNonLu = server.howManyMailItems(nomUtilisateurCourant);
        System.out.println("Vous avez " + nbMailNonLu + " mail non lu.");

        MailItem mail = server.getNextMailItem(nomUtilisateurCourant);

        if(mail != null){
            mail.print();
        } else {
            System.out.println("Pas de nouveau mail !");
        }

        System.out.println("Appuyez sur une touche pour sortir.");
        Scanner inSortie = new Scanner(System.in);
        String sortie = inSortie.next();
    }

    public void spamMail(){
        System.out.print("\033[H\033[2J"); 
        System.out.println("*** COURRIER INDESIRABLES ***");

        MailItem mail = server.getNextMailSpam(nomUtilisateurCourant);

        if(mail != null){
            mail.print();
        } else {
            System.out.println("Pas de nouveau mail !");
        }

        System.out.println("Appuyez sur une touche pour sortir.");
        Scanner inSortie = new Scanner(System.in);
        String sortie = inSortie.next();
    }

    public void parameters(){
        System.out.print("\033[H\033[2J"); 
        System.out.println("*** PARAMETRES ***");

        for(String mot : motsSpam){
            System.out.println(mot);
        }


        System.out.println("\nAppuyez sur F pour ajouter des mots\nAppuyez sur H supprimer des mots");
        System.out.println("Appuyez sur une autre touche pour quitter");

        Scanner inSortie = new Scanner(System.in);
        String sortie = inSortie.next();


        // ? FAIRE DES DO WHILE COMME CA ON EN AJOUTE PLUSIEURS D'UN COUP

        if(sortie.equals("F")){
            System.out.println("Veillez saisir un mot a ajouter (tapez 'echap' pour quitter)");
            Scanner inAdd = new Scanner(System.in);
            String add = inAdd.next();   
            
            do{
                if(add.equals("echap")){
                    parameters();
                } else {
                    motsSpam.add(add);
                    parameters();
                }
            } while(!(add.equals("echap")));

        } else if(sortie.equals("H")){
            System.out.println("Veillez saisir un mot a supprimer (tapez 'echap' pour quitter)");
            Scanner inAdd = new Scanner(System.in);
            String add = inAdd.next();
            
            do{
                if(add.equals("echap")){
                    parameters();
                } else {
                    motsSpam.remove(add);
                    parameters();
                }
            }while(!(add.equals("echap")));
        }
         else {
            menuPrincipal();
        }
    }

    public void logout(){
        System.out.print("\033[H\033[2J"); 
        String choix;
    
        System.out.println("*** PAGE DE DECONNEXION ***");
    
        do {
            choix = SimpleInput.getString("Êtes-vous sûr de vous déconnecter ? (Oui/Non)");
        } while(!choix.equalsIgnoreCase("Oui") && !choix.equalsIgnoreCase("Non"));
    
        if(choix.equalsIgnoreCase("Oui")){
            System.out.println("Déconnexion...");
            System.out.print("\033[H\033[2J");  
            connectionOuInscripion();
        } else if(choix.equalsIgnoreCase("Non")){
            System.out.println("Redirection vers le menu");
            System.out.print("\033[H\033[2J");
            menuPrincipal(); 
        }
    }
    
}