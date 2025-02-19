package SingleThread;
// package SingleThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void run() throws IOException {

        int port = 8010;
        ServerSocket serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
        while (true) {
            try {
                System.out.println("Server is listening on port " + port);
                Socket acceptedConnection = serverSocket.accept();
                // System.out.println("Client connected: " + acceptedConnection.getInetAddress().getHostAddress());
                System.out.println("Client connected: " + acceptedConnection.getRemoteSocketAddress());
                PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true);
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
                toClient.println("Hello from server");

                String message = fromClient.readLine();
                System.out.println("Client sent: " + message);
                toClient.println("Server received: " + message);

                fromClient.close();
                toClient.close();
                acceptedConnection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public static void main(String[] args) {
        System.out.println("Server is running...");
        Server server = new Server();
        try {
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}