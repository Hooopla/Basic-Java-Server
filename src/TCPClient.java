import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {

    public static void main(String[] args) {
        // Create a server socket
        String serverAddress = "localhost";
        int port = 8888;
        try {
            Socket mySocket = new Socket(serverAddress, port);
            PrintWriter output = new PrintWriter(mySocket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
            Scanner scanner = new Scanner(System.in);

            System.out.println("Connect to server! Type a message or Type 'bye' to disconnect from server.");
            while(true) {
                System.out.print("> ");
                String message = scanner.nextLine();
                output.println(message);

                // Read Responses from the server
                String serverResponse;
                while ((serverResponse = input.readLine()) != null) {
                    System.out.println("Server: " + serverResponse);

                    // Stop reading if no more lines are available
                    if (!input.ready()) {
                        break;
                    }
                }

                // Disconnecting
                if(message.equalsIgnoreCase("Bye")) {
                    break;
                }

            }
        } catch (IOException e) {
            System.out.println("Client has failed to start on port 8888 :(");
        }
        System.out.println("Client Disconnected.");
    }
}
