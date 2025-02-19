package SingleThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public void run() throws IOException {
        int port = 8010;
        InetAddress address = InetAddress.getByName("localhost");
        Socket clientSocket = new Socket(address, port);
        // System.out.println("Connected to server: " + clientSocket.getRemoteSocketAddress());
        PrintWriter toSocket = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader fromSocket = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        toSocket.println("Hello from client");
        System.out.println("Response from the Server Socket is : " + fromSocket.readLine());

        toSocket.close();
        fromSocket.close();
        clientSocket.close();
    }
    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
