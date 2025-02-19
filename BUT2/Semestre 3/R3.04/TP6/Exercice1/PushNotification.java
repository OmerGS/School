package Exercice1;

public class PushNotification implements NotificationStrategy {
    
    @Override
    public void sendNotification(String message,boolean critique) {
        if(critique){
            System.out.println("Message critique envoyé par push : " + message);
        }else{
            System.out.println("Message envoyé par push : " + message);
        }
    }
}
