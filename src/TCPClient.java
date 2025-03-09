import java.io.*;
import java.net.*;
public class TCPClient {

    public static void main(String args[]) throws IOException {
        // Create a server socket
        int port = 8888;
        try {
            Socket mySocket = new Socket("localhost", port);
            System.out.println("Client has started on port 8888 :)");
            while(true) {
                OutputStream os = mySocket.getOutputStream();
                InputStream is = mySocket.getInputStream();

                PrintWriter out = new PrintWriter(os, true);
                BufferedReader in = new BufferedReader(new InputStreamReader(is));
                System.out.print("> ");
                //String command

            }
        } catch (IOException e) {
            System.out.println("Client has failed to start on port 8888 :(");
        }


    }
}
