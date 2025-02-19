
package Exercice1;

public class ConnectedState implements UserState{
    @Override
    public void handle(NotificationStrategy strategy, String message){
        strategy.sendNotification(message,false);
    }
}