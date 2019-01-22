// a java program for a client
import java.net.*;
import java.io.*;

public class Client {
    // initialize socket and io streams
    private Socket socket = null;
    private BufferedReader input = null;
    private DataOutputStream output = null;

    // Note: Port number can be from 0 to 65535
    // constructor to put ip address and port
    public Client(String ipAddress, int port) {
        // establish a connection
        try {
            socket = new Socket(ipAddress, port);
            System.out.println("Connection successful...");

            // takes input from terminal 
            input = new BufferedReader(new InputStreamReader(System.in));

            // sends output to the socket
            output = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u) {
            System.out.println(u);
        }
        catch(IOException i) {
            System.out.println(i);
        }

        // string to read messages from input
        String line = "";

        // keep reading until over is input
        while(!line.equals("Over")) {
            try {
                line = input.readLine();
                output.writeUTF(line);
            }
            catch(IOException i) {
                System.out.println(i);
            }
        }

        // close the connection
        try {
            input.close();
            output.close();
            socket.close();
        }
        catch(IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 5000); 
    }
}