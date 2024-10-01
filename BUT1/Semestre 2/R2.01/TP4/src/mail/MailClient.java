package mail;

/**
* Class who manages client capabilities
* @author O.Gunes 
*/
public class MailClient {
    /**
    * The user 
    */
    private String user;

    /**
     * The server
     */
    private MailServer server;
    
    /**
     * The constructor of the class
     * @param server the server
     * @param user the user
     */
    public MailClient(MailServer server, String user){
        if(server != null){
            this.server = server;
        } else {
            System.out.println("MailClient : Le serveur est nulle !");
        }

        if(user != null){
            this.user = user;
        } else {
            System.out.println("MailClient : Le nom est nulle !");
        }
    }

    /**
     * return the user
     * @return user
     */
    public String getUser(){
        return(this.user);
    }

    /**
     * return the next mail
     * @return mail
     */
    public MailItem getNextMailItem(){
        return(server.getNextMailItem(this.user));
    }

    /**
     * print the next mail item
     */
    public void printNextMailItem(){
        MailItem mail = getNextMailItem();
        if(mail == null){
            System.out.println("Pas de mail");
        } else {
            mail.print();
        }
    }

    /**
     * send a mail
     * @param to the user who we wanted to send mail
     * @param message the content of the mail
     */
    public void sendMailItem(String to, String message){
        MailItem mail;
        if(to != null && message != null){
            mail = new MailItem(this.user, to, message);
            server.post(mail);
        } else {
            System.out.println("Erreur dans le destinateur ou le message !");
        }
    }
}
