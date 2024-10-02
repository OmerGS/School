package network;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Server {
    private ServerSocket serverSocket;
    private List<User> connectedUsers = Collections.synchronizedList(new ArrayList<>());

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.err.println("Server started on port " + port);
    }

    public void acceptConnections() {
        try {
            while (true) {
                Socket client = serverSocket.accept();
                User newUser = new User(client);
                connectedUsers.add(newUser);
                System.err.println("User connected: " + newUser);
                new Thread(new UserHandler(newUser, this)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeUser(User user) {
        connectedUsers.remove(user);
        System.err.println("User disconnected: " + user);
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Server port");
            System.exit(1);
        }
        try {
            int port = Integer.parseInt(args[0]);
            Server server = new Server(port);
            server.acceptConnections();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class User {
    private Socket socket;

    public User(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public String toString() {
        return socket.getRemoteSocketAddress().toString();
    }
}

class UserHandler implements Runnable {
    private User user;
    private Server server;
    private BufferedReader in;
    private PrintStream out;

    public UserHandler(User user, Server server) throws IOException {
        this.user = user;
        this.server = server;
        Socket socket = user.getSocket();
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintStream(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Received from " + user + ": " + message);
                out.println("Server echo: " + message);
            }
        } catch (IOException e) {
            System.err.println("Error with user " + user);
        } finally {
            server.removeUser(user);
            try {
                user.getSocket().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}