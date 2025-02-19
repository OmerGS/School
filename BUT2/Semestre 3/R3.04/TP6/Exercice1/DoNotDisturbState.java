package Exercice1;
public class DoNotDisturbState implements UserState{
    @Override
    public void handle(NotificationStrategy strategy, String message){
        if(message.toLowerCase().contains("alerte")){
            strategy.sendNotification(message,true);
        } else {
            System.out.println("Mode \"Ne pas déranger\" activé. Aucun message envoyé.");
        }
    }
}