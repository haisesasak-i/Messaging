import java.util.ArrayList;

public class Receiver {
    public static void receiveMessage(ArrayList<Message> messages,
                                      int receiverId) {
        boolean messageFound = false;
        try {
            for (Message message : messages) {
                if (message.getReceiverId() == receiverId) {
                    message.displayMessage();
                    message.markAsRead();
                    messageFound = true;
                }
            }
            if (!messageFound) {
                System.out.println("No new messages for this ID.");
            }
        } catch (Exception e) {
            System.out.println("Error: Unable to receive messages. " +
                               e.getMessage());
        }
    }

    public static void deleteAllMessages(ArrayList<Message> messages,
                                         int contactId) {
        messages.removeIf(message
                          -> message.getReceiverId() == contactId ||
                                 message.getSenderId() == contactId);
        System.out.println("All messages for contact ID " + contactId +
                           " have been deleted.");
    }

    public static void displayMessagesForContact(ArrayList<Message> messages,
                                                 int contactId) {
        boolean messageFound = false;
        for (Message message : messages) {
            if (message.getSenderId() == contactId ||
                message.getReceiverId() == contactId) {
                message.displayMessage();
                messageFound = true;
            }
        }
        if (!messageFound) {
            System.out.println("No messages for contact ID " + contactId);
        }
    }

    public static void searchMessages(ArrayList<Message> messages,
                                      String searchText) {
        boolean messageFound = false;
        for (Message message : messages) {
            if (message.getText().contains(searchText)) {
                message.displayMessage();
                messageFound = true;
            }
        }
        if (!messageFound) {
            System.out.println("No messages found containing: " + searchText);
        }
    }

    public static void searchMessagesBySender(ArrayList<Message> messages,
                                              int senderId) {
        boolean messageFound = false;
        for (Message message : messages) {
            if (message.getSenderId() == senderId) {
                message.displayMessage();
                messageFound = true;
            }
        }
        if (!messageFound) {
            System.out.println("No messages found from sender ID: " + senderId);
        }
    }
}
