import java.io.*;
import java.net.*;
// This server will keep reading messages till it hears the "bye"
public class TCPServer {
    public static void printSystemTime(PrintWriter output ) {
        if (output != null) {
            output.println("Responded in: " + System.currentTimeMillis() + " Milliseconds");
        }
    }

    public static void main(String[] args) {
        // Create a server socket
        int port = 8888;
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Listening on port 8888 :)");
            System.out.println("Waiting for connection! :D");
            while(true) {
                Socket client = server.accept(); // Client
                System.out.println("Found a client :D");
                // Get output and input!
                OutputStream os  = client.getOutputStream(); // Data for the Client
                InputStream is = client.getInputStream(); // Data from Client
                PrintWriter output = new PrintWriter(os, true); // Creating a data sender
                BufferedReader input = new BufferedReader(new InputStreamReader(is)); // Creating a data receiver

                String clientMessage;
                while ((clientMessage = input.readLine()) != null) {
                    clientMessage = clientMessage.toLowerCase();
                    if(clientMessage.equals("hello")) {
                        printSystemTime(output);
                        output.println("Hello! Welcome to Hooopla's first basic TCP server!");
                    } else if (clientMessage.equals("bye")) {
                        printSystemTime(output);
                        output.println("Oh.. Well I hope you take care bye!! :D");
                        break;
                    } else {
                        printSystemTime(output);
                        output.println("I don't understand the command: " + clientMessage);
                    }
                }
                System.out.println("Closing connection :D");
                os.close();
                is.close();
                client.close();
            }
        } catch (IOException e) {
            System.out.println("Server failed to start on port 8888 :(");
        }
    }
}
