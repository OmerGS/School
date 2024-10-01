package mail;

/**
* Class who create a mail with a sender, receiver and the message 
*/
public class MailItem{
    /**
    * The sender of the mail 
    */
    private String from;

    /**
    * The mail receiver
    */
    private String to;
    
    /**
    * The message 
    */
    private String message;

    /**
    * The constructor of MailItem
    *  
    * @param from L'envoyeur du message
    * @param to Le destinateur
    * @param message - Le message
    */
    public MailItem(String from, String to, String message){
        if((to != null) && (from != null) && (message != null)){
            this.to = to;
            this.from = from;
            this.message = message;
        } else {
            System.out.println("mailItem : Valeur Nulle");
        }
    }

    /**
    * Return the sender of the message
    *
    * @return sender of the message
    */
    public String getFrom(){
        return(this.from);
    }

    /**
    * Return the receiver of the message
    *
    * @return receiver of the message
    */
    public String getTo(){
        return(this.to);
    }

    /**
    * Return the message.
    *
    * @return the message
    */
    public String getMessage(){
        return(this.message);
    }

    /**
    * Print the mail :
    *      -  The Sender
    *       - The Receiver
    *       - The message
    */
    public void print(){
        System.out.println(
            "De : " + this.from + "\n" + 
            "A : " + this.to + "\n" + 
            "Message : " + this.message);
    }
}