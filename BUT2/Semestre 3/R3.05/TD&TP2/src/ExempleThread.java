import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ExempleThread {

    // ========================================
    public class ClientHandler extends Thread {
	BufferedReader in;
	PrintStream out;
	String name;

	// ========================================
	public ClientHandler (Socket call) {
	    try {
		in = new BufferedReader
		    (new InputStreamReader (call.getInputStream ()));
		out = new PrintStream (call.getOutputStream (), true);
		start ();
	    } catch (Exception e) {
		e.printStackTrace ();
	    }
	}

 	// ========================================
	public void run () {
	    out.print ("qui es-tu ? ");
	    out.flush ();
	    name = in.readLine ();
	    out.println ("Bonjour "+name+" !");
	    // XXX ajout à la liste des participant
	    for (;;) {
		// XXX lire une ligne et la diffuser
		// XXX si FIN sortir de la boucle
		try {
		    sleep (1000);
		} catch (InterruptedException e) {
		}
		out.println ("coucou "+name);
	    }
	    // XXX retirer des participants
	}
    }

    // ========================================
    // XXX gestion de la liste des participants et diffusion
    
    public static void main (String[] args) {
	if (args.length != 1) {
	    System.err.println ("Usage: ExempleThread port");
	    System.exit (1);
	}
	new ExempleThread (Integer.parseInt (args [0]));
    }

    // ========================================
    public ExempleThread (int port) {
	try {
	    ServerSocket serverSocket = new ServerSocket (port);
	    System.err.println ("Start server on port "+port);
	    for (;;)
		new ClientHandler (serverSocket.accept ());
	} catch (Exception e) {
	    e.printStackTrace ();
	}
    }
}
