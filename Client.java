import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try (BufferedReader consoleReader =
                 new BufferedReader(new InputStreamReader(System.in))) {
            // Prompt for server IP and port
            System.out.println("Enter the server IP address (e.g., 127.0.0.1 " +
                               "for localhost): ");
            String serverIP = consoleReader.readLine();

            System.out.println("Enter the server port (default: 12345): ");
            int port = Integer.parseInt(consoleReader.readLine());

            // Connect to server
            Socket socket = new Socket(serverIP, port);
            System.out.println("Connected to the server!");

            // Set up input/output streams
            BufferedReader serverReader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            PrintWriter serverWriter =
                new PrintWriter(socket.getOutputStream(), true);

            // Thread for listening to messages from server
            new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = serverReader.readLine()) != null) {
                        System.out.println("Server: " + serverMessage);
                    }
                } catch (IOException e) {
                    System.out.println("Connection to server lost.");
                }
            }).start();

            // Main thread for sending messages to server
            String message;
            System.out.println(
                "Type your messages below (type 'exit' to disconnect):");
            while ((message = consoleReader.readLine()) != null) {
                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Disconnecting...");
                    break;
                }
                serverWriter.println(message);
            }

            // Clean up resources
            socket.close();
            System.out.println("Disconnected from server.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
