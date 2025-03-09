package GroupChat_TCP;

import java.io.*;
import java.net.*;
import java.util.*;


public class TCPServer {

    private static List<PrintWriter> clientWriters = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int port = 8888;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Listening on port " + port);

            while (true) {
                // Accept client connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Get input and output streams for communication with the client
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                synchronized (clientWriters) {
                    clientWriters.add(out);
                }

                // Create a new thread to handle client communication

            }
        } catch (IOException e) {
            System.out.println("Error in server: " + e.getMessage());
        }
    }

    public class ClientHandler extends Thread {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public ClientHandler(Socket socket, BufferedReader in, PrintWriter out) {
            this.socket = socket;
            this.in = in;
            this.out = out;
        }

        @Override
        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    if (message.equalsIgnoreCase("Exit")) {
                        break;
                    }
                }
                // Broadcast the message to all connected clients
                synchronized (clientWriters) {
                    for (PrintWriter writer: clientWriters) {
                        writer.println(message);
                    }
                }
            } catch (IOException e) {
                System.out.println("Client disconnect: " + socket.getInetAddress());
            } finally {
                try {
                    socket.close();
                    synchronized(clientWriters) {
                        clientWriters.remove(out);
                    }
                } catch (IOException e) {
                    System.out.println("Error closing socket: " + e.getMessage());
                }
            }
        }
    }
}

