import java.util.ArrayList;

public class Contact {
    private int contactId;
    private String name;

    public Contact(int contactId, String name) {
        this.contactId = contactId;
        this.name = name;
    }

    public int getContactId() { return contactId; }

    public String getName() { return name; }

    public void displayContact() {
        System.out.println("ID: " + contactId + ", Name: " + name);
    }

    public static void displayAllContacts(ArrayList<Contact> contacts) {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }

        for (Contact contact : contacts) {
            contact.displayContact();
        }
    }
}
