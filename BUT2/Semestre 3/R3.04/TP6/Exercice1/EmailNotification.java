package Exercice1;

public class EmailNotification implements NotificationStrategy {
    
    @Override
    public void sendNotification(String message,boolean critique) {
        if(critique){
            System.out.println("Message critique envoyé par email : " + message);
        }else{
            System.out.println("Message envoyé par email : " + message);
        }
        
    }
}
