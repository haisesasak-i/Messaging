import java.time.LocalDateTime;

public class Message {
    private int senderId;
    private int receiverId;
    private String text;
    private LocalDateTime timestamp;
    private MessageStatus status;

    public Message(int senderId, int receiverId, String text) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.text = text;
        this.timestamp = LocalDateTime.now();
        this.status = MessageStatus.UNREAD; // Default status is UNREAD
    }

    public void markAsRead() { this.status = MessageStatus.READ; }

    public void markAsUnread() { this.status = MessageStatus.UNREAD; }

    public void displayMessage() {
        System.out.println("From: " + senderId + " To: " + receiverId + " | " +
                           text);
        System.out.println("Timestamp: " + timestamp + " | Status: " + status);
    }

    public int getSenderId() { return senderId; }

    public int getReceiverId() { return receiverId; }

    public String getText() { return text; }

    public LocalDateTime getTimestamp() { return timestamp; }

    public MessageStatus getStatus() { return status; }
}
