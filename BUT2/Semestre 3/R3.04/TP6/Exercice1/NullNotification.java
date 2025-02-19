package Exercice1;

public class NullNotification implements NotificationStrategy {
    
    @Override
    public void sendNotification(String message,boolean critique) {
        System.out.println("Aucun message envoyé.");
    }
}
