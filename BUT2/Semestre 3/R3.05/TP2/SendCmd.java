// (c) F. Merciol
// Plus d'informations sur http://r305.merciol.fr
package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class SendCmd {

    static public void main (String[] args) {
	if (args.length != 3) {
	    System.err.println ("Usage: SendCmd host port request");
	    System.exit (1);
	}
	try {
	    Socket clientSocket =
		new Socket (args [0], Integer.parseInt (args [1]));
	    PrintStream out =
		new PrintStream (clientSocket.getOutputStream (), true);
	    out.println (args[2]);
	    out.flush ();
	    clientSocket.shutdownOutput ();
	    BufferedReader in =
		new BufferedReader
		(new InputStreamReader (clientSocket.getInputStream ()));
	    for (;;) {
		String line = in.readLine ();
		if (line == null)
		    break;
		System.out.println (line);
		System.out.flush ();
		out.close ();
	    }
	} catch (IOException e) {
	    System.err.println ("Socket closed");
	    System.err.flush ();
	} catch (Exception e) {
	    e.printStackTrace ();
	}
    }
}
