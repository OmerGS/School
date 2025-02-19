// (c) F. Merciol
// Plus d'informations sur http://r305.merciol.fr
package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SendData {
    static public void main (String[] args) {
	if (args.length != 3) {
	    System.err.println ("Usage: SendData host port data");
	    System.exit (1);
	}

	try {
	    InetAddress address = InetAddress.getByName (args[0]);
	    int port = Integer.parseInt (args [1]);
	    byte[] buf = args[2].getBytes ();
	    DatagramPacket packet =
		new DatagramPacket (buf, buf.length, address, port);

	    DatagramSocket socket = new DatagramSocket ();
	    socket.send(packet);
	} catch (UnknownHostException e) {
	    e.printStackTrace ();
	} catch (SocketException e) {
	    e.printStackTrace ();
	} catch (IOException e) {
	    e.printStackTrace ();
	}
    }
}
