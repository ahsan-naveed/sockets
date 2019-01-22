// a java program for a server
import java.net.*;
import java.io.*;

public class Server {
    // initialize sockets and input stream
    private Socket socket = null; // for communication with the client
    private ServerSocket server = null; // waits for the client requests (when a client makes a new Socket())
    private DataInputStream input = null;

    // constructor with the port
    public Server(int port) {
        // starts server and waits for a connection
        try {
            server = new ServerSocket(port);
            System.out.println(String.format("Server is listening on %d", port));

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("Client accepted...");

            // takes input from the client socket
            input = new DataInputStream(
                new BufferedInputStream(
                    socket.getInputStream()
                ));

            String line = "";

            // reads messages from client until Over is sent
            while(!line.equals("Over")) {
                try {
                    line = input.readUTF();
                    System.out.println(line);
                }
                catch(IOException i) {
                    System.out.println(i);
                }
            }

            System.out.println("Closing connection");

            // close connection
            socket.close();
            input.close();
        }
        catch(IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        Server server = new Server(5000); 
    } 
}