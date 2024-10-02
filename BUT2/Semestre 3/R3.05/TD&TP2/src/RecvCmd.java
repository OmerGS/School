// (c) F. Merciol
// Plus d'informations sur http://r305.merciol.fr
package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RecvCmd {

    static public void main (String[] args) {
	if (args.length != 1) {
	    System.err.println ("Usage: RecvCmd port");
	    System.exit (1);
	}
	try {
	    int port = Integer.parseInt (args [0]);
	    ServerSocket serverSocket = new ServerSocket (port);
	    System.err.println ("Start server on port "+port);

	    for (;;) {
		Socket call = serverSocket.accept ();
		System.err.println ("Client comming from "+
				    call.getRemoteSocketAddress ());
		BufferedReader in =
		    new BufferedReader
		    (new InputStreamReader (call.getInputStream ()));
		PrintStream out =
		    new PrintStream (call.getOutputStream (), true);
		BufferedReader sysin =
		    new BufferedReader (new InputStreamReader (System.in));
		for (;;) {
		    String line1 = in.readLine ();
		    if (line1 == null)
			break;
		    System.out.println (line1);
		    System.out.flush ();
		    String line2 = sysin.readLine ();
		    out.println (line2);
		    out.flush ();
		}
		System.err.println ("Socket closed");
	    }
	} catch (Exception e) {
	    e.printStackTrace ();
	}
	System.err.println ("Server closed");
	System.err.flush ();
    }
}
