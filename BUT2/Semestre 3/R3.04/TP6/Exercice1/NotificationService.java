package Exercice1;
public class NotificationService {
    private UserState state;

    public NotificationService(UserState state){
        this.state = state;
    }

    public void setState(UserState state){
        this.state = state;
    }

    public void handle(NotificationStrategy strat, String message){
        state.handle(strat,message);
    }
}