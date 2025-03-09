package GroupChat_TCP;
import java.io.*;
import java.net.*;
public class TCPClient {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Change this if the server is running elsewhere
        int serverPort = 8888;

        try (Socket socket = new Socket(serverAddress, serverPort)) {
            // Create input/output streams for communication
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Thread to listen for incoming messages
            Thread listenThread = new Thread(() -> {
                String message;
                try {
                    while ((message = in.readLine()) != null) {
                        System.out.println("Server: " + message);
                    }
                } catch (IOException e) {
                    System.out.println("Connection lost.");
                }
            });
            listenThread.start();

            // Send messages from the user to the server
            String message;
            while ((message = userInput.readLine()) != null) {
                if (message.equalsIgnoreCase("exit")) {
                    out.println("exit");
                    break;
                }
                out.println(message);
            }
        } catch (IOException e) {
            System.out.println("Error connecting to server: " + e.getMessage());
        }
    }
}
