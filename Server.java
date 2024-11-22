import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {
    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static boolean isRunning = true;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println(
                "Server is running... Type 'shutdown' to stop the server.");

            // Thread to monitor for shutdown command
            new Thread(() -> {
                try (BufferedReader consoleReader =
                         new BufferedReader(new InputStreamReader(System.in))) {
                    String command;
                    while (isRunning &&
                           (command = consoleReader.readLine()) != null) {
                        if (command.equalsIgnoreCase("shutdown")) {
                            isRunning = false;
                            System.out.println("Shutting down the server...");
                            serverSocket.close(); // Close the server socket to
                                                  // break the accept loop
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error reading shutdown command: " +
                                       e.getMessage());
                }
            }).start();

            int clientCount = 0;
            while (isRunning) {
                try {
                    Socket socket = serverSocket.accept();
                    clientCount++;
                    System.out.println("Client " + clientCount + " connected!");
                    ClientHandler clientHandler =
                        new ClientHandler(socket, clientCount);
                    clients.add(clientHandler);
                    new Thread(clientHandler).start();
                } catch (IOException e) {
                    if (isRunning) {
                        System.out.println("Error accepting connection: " +
                                           e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }

        System.out.println("Goodbye! The server has been shut down.");
    }

    static class ClientHandler implements Runnable {
        private Socket socket;
        private BufferedReader reader;
        private PrintWriter writer;
        private int clientId;

        public ClientHandler(Socket socket, int clientId) throws IOException {
            this.socket = socket;
            this.clientId = clientId;
            this.reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);
        }

        @Override
        public void run() {
            try {
                writer.println("Welcome, Client " + clientId + "!");
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("Client " + clientId +
                                       " says: " + message);
                    broadcastMessage("Client " + clientId + ": " + message);
                }
            } catch (IOException e) {
                System.out.println("Connection lost with Client " + clientId +
                                   ".");
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Error closing socket for Client " +
                                       clientId + ": " + e.getMessage());
                }
                System.out.println("Client " + clientId + " has disconnected.");
            }
        }

        private void broadcastMessage(String message) {
            for (ClientHandler client : clients) {
                if (client != this) {
                    client.writer.println(message);
                }
            }
        }
    }
}
