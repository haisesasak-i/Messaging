import java.util.ArrayList;

public class Sender {
    public static void sendMessage(ArrayList<Message> messages, int senderId,
                                   int receiverId, String text) {
        if (text == null || text.trim().isEmpty()) {
            System.out.println("Error: Message text cannot be empty.");
            return;
        }

        try {
            messages.add(new Message(senderId, receiverId, text));
            System.out.println("Message sent successfully!");
        } catch (Exception e) {
            System.out.println("Error: Unable to send message. " +
                               e.getMessage());
        }
    }
}
