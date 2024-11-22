import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Contact> contacts = new ArrayList<>();
        ArrayList<Message> messages = new ArrayList<>();

        // Adding some contacts
        contacts.add(new Contact(1, "Ahmed"));
        contacts.add(new Contact(2, "Ali"));
        contacts.add(new Contact(3, "Fatima"));
        contacts.add(new Contact(4, "Ayesha"));
        contacts.add(new Contact(5, "Hassan"));

        System.out.println(
            "Welcome to the Console-Based Messaging Application!");

        while (true) {
            try {
                System.out.println("\nMenu:");
                System.out.println("Enter 1 to view contacts");
                System.out.println("Enter 2 to send a message");
                System.out.println("Enter 3 to receive message");
                System.out.println("Enter 4 to search messages");
                System.out.println(
                    "Enter 5 to delete all messages of a contact");
                System.out.println(
                    "Enter 6 to display all messages for a contact");
                System.out.println("7. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();

                switch (choice) {
                case 1:
                    Contact.displayAllContacts(contacts);
                    break;
                case 2:
                    System.out.print("Enter your ID: ");
                    int senderId = scanner.nextInt();
                    System.out.print("Enter recipient ID: ");
                    int receiverId = scanner.nextInt();
                    scanner.nextLine(); // Clear buffer
                    System.out.print("Enter your message: ");
                    String text = scanner.nextLine();
                    Sender.sendMessage(messages, senderId, receiverId, text);
                    break;
                case 3:
                    System.out.print("Enter your ID to check messages: ");
                    int userId = scanner.nextInt();
                    Receiver.receiveMessage(messages, userId);
                    break;
                case 4:
                    scanner.nextLine(); // Clear buffer
                    System.out.print("Enter search text: ");
                    String searchText = scanner.nextLine();
                    Receiver.searchMessages(messages, searchText);
                    break;
                case 5:
                    System.out.print(
                        "Enter contact ID to delete all messages: ");
                    int contactIdToDelete = scanner.nextInt();
                    Receiver.deleteAllMessages(messages, contactIdToDelete);
                    break;
                case 6:
                    System.out.print(
                        "Enter contact ID to display all messages: ");
                    int contactIdToDisplay = scanner.nextInt();
                    Receiver.displayMessagesForContact(messages,
                                                       contactIdToDisplay);
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println(
                    "Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the buffer
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " +
                                   e.getMessage());
            }
        }
    }
}
