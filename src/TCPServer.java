import java.io.*;
import java.net.*;
public class TCPServer {

    public static void main(String args[]) throws IOException {
        // Create a server socket
        int port = 8888;
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Listening on port 8888 :)");
            System.out.println("Waiting for connection! :D");
            while(true) {
                Socket client = server.accept();
                // Get output and input!
                OutputStream os  = client.getOutputStream(); // Data for the Client
                InputStream is = client.getInputStream(); // Data from Client
                PrintWriter output = new PrintWriter(os, true); // Creating a data sender
                BufferedReader input = new BufferedReader(new InputStreamReader(is)); // Creating a data receiver
                // Read token
                String clientMessage = input.readLine();
                if (clientMessage != null && clientMessage.equals("Ray")) {
                    output.println(System.currentTimeMillis());
                    output.println("Eww it's Ray :(");
                }
                else if(clientMessage != null && clientMessage.equals("Exit")) {
                    output.println(System.currentTimeMillis());
                    output.println("Server closing!");
                    os.close();
                    is.close();
                    client.close();
                }
            }
        } catch (IOException e) {
            System.out.println("Server failed to start on port 8888 :(");
        }


    }
}
