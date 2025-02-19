package Exercice1;

public class SMSNotification implements NotificationStrategy {
    
    @Override
    public void sendNotification(String message, boolean critique) {
        if(critique){
            System.out.println("Message critique envoyé par SMS : " + message);
        }else{
            System.out.println("Message envoyé par SMS : " + message);
        }
    }
}
