// (c) F. Merciol
// Plus d'informations sur http://r305.merciol.fr
package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class RecvData {
    static public void main (String[] args) {
	if (args.length != 1) {
	    System.err.println ("Usage: RecvData port");
	    System.exit (1);
	}
	try {
	    int port = Integer.parseInt (args [0]);
	    DatagramSocket socket = new DatagramSocket (port);

	    byte[] buf = new byte[256];
	    DatagramPacket packet = new DatagramPacket (buf, buf.length);
	    socket.receive (packet);

	    String received = new String (packet.getData(), 0, packet.getLength ());
	    String srcIP = packet.getAddress ().getHostAddress ();
	    int scrPort = packet.getPort (); 
	    System.out.println (srcIP+":"+scrPort+" send : "+received);
	} catch (SocketException e) {
	    e.printStackTrace ();
	} catch (IOException e) {
	    e.printStackTrace ();
	}
    }
}
