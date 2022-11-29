package contacts;

import contacts.entity.Contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PhoneBook implements Serializable {
    private final List<Contact> phoneBook;

    public PhoneBook() {
        phoneBook = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        phoneBook.add(contact);
    }

    public void removeContact(int recordNumber) {
        phoneBook.remove(recordNumber);
    }

    public void removeContact(Contact contact) {
        phoneBook.remove(contact);
    }

    public Contact getContact(int recordNumber) {
        return phoneBook.get(recordNumber);
    }

    public int getSize() {
        return phoneBook.size();
    }

    public List<Contact> getPhoneBook() {
        return new ArrayList<>(phoneBook);
    }

    public List<Contact> search(String input) {
        List<Contact> res = new ArrayList<>();
        for (Contact contact : phoneBook) {
            if (contact.getFullName().toLowerCase().contains(input.toLowerCase()) || contact.getPhoneNumber().contains(input)) {
                res.add(contact);
            }
        }
        return res;
    }
}
