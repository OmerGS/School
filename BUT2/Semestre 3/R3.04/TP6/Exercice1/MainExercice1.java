package Exercice1;

public class MainExercice1{
    public static void main(String[] args) {
        UserState s = new ConnectedState();
        NotificationService n = new NotificationService(s);

        //cas 1
        n.handle(new EmailNotification(),"Bonjour, utilisateur !");

        //cas 2
        n.setState(new DisconnectedState());
        n.handle(new EmailNotification(),"gutentag");

        //cas 3
        n.setState(new DoNotDisturbState());
        n.handle(new SMSNotification(),"Alerte c'est très le important ça dit donc!");

        //cas 4
        n.setState(new DoNotDisturbState());
        n.handle(new PushNotification(), "hola !");
    }
}