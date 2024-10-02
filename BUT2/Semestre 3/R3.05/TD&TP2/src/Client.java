import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    
    static public void main (String[] args) {
        String input= "";
        Scanner scanner;
        if (args.length != 2) {
            System.err.println ("Usage: SendData host port");
            System.exit (1);
        }

        try {
            while (true) {
                scanner = new Scanner(System.in);
        
                
                input = scanner.nextLine();
                InetAddress address = InetAddress.getByName (args[0]);
                int port = Integer.parseInt (args [1]);
                byte[] buf = input.getBytes ();
                DatagramPacket packet =
                new DatagramPacket (buf, buf.length, address, port);
        
                DatagramSocket socket = new DatagramSocket ();
                socket.send(packet);




                byte[] boeuf = new byte[256];
                DatagramPacket packet2gateau = new DatagramPacket (boeuf, boeuf.length);
                socket.receive (packet2gateau);

                String received = new String (packet2gateau.getData(), 0, packet2gateau.getLength ());
                String srcIP = packet2gateau.getAddress ().getHostAddress ();
                int scrPort = packet2gateau.getPort (); 
                System.out.println (srcIP+":"+scrPort+" send : "+received);
            }
            
        } catch (UnknownHostException e) {
            e.printStackTrace ();
        } catch (SocketException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
        }
}
