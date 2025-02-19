package Exercice1;
public class DisconnectedState implements UserState{
    @Override
    public void handle(NotificationStrategy strategy, String message){
        System.out.println("Utilisateur déconnecté. Aucun message envoyé.");
    }
}