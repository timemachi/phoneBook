package contacts;

import contacts.entity.Contact;
import contacts.entity.Organization;
import contacts.entity.Person;
import contacts.util.SerializationUtils;
import contacts.util.Validator;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Actions {
    private PhoneBook phoneBook;
    private final Scanner scanner;
    private final String filename = "phonebook.db";

    public Actions() {
        try {
            phoneBook = (PhoneBook) SerializationUtils.deserialize(filename);
        } catch (Exception ignored) {}
        System.out.println("open phonebook.db");
        if (phoneBook == null) {
            phoneBook = new PhoneBook();
        }
        scanner = new Scanner(System.in);
    }
    public void exit() {
        try {
            SerializationUtils.serialize(phoneBook, filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addPerson() {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the surname: ");
        String surname = scanner.nextLine();

        System.out.print("Enter the birth date: ");
        LocalDate birthDate = setBirthDate(scanner.nextLine());

        System.out.print("Enter the gender (M, F): ");
        String gender = setGender(scanner.nextLine());

        System.out.print("Enter the number: ");
        String number = setPhoneNumber(scanner.nextLine());

        phoneBook.addContact(
                new Person(
                        name,
                        surname,
                        birthDate,
                        gender,
                        number
                ));

    }

    public void add() {
        System.out.print("Enter the type (person, organization): ");
        String type = scanner.nextLine().toLowerCase();
        switch (type) {
            case "person":
                addPerson();
                break;
            case "organization":
                addOrganization();
                break;
            default:
                System.out.println("Incorrect type.");
                return;
        }
        System.out.println("The record added.");
    }

    public void search() {
        System.out.println("Enter search query:");
        String searchWord = scanner.nextLine();
        List<Contact> result = search(searchWord);
        System.out.print("\n[search] Enter action ([number], back, again):");
        String action = scanner.nextLine().toLowerCase();
        switch (action) {
            case "back" -> {}
            case "again" -> search();
            default -> {
                int index = isCorrectIndex(action);
                if (index == -1 || index > result.size()) {
                    return;
                } else {
                    Contact curr = result.get(index - 1);
                    setRecord(curr);
                }
            }
        }
    }

    private void setRecord(Contact contact) {
        System.out.print("\n[record] Enter action (edit, delete, menu): ");
        String action = scanner.nextLine();
        switch (action) {
            case "menu" -> {}
            case "edit" -> {
                switch (contact.getClass().getSimpleName()) {
                    case "Person" -> editPerson(contact);
                    case "Organization" -> editOrganization(contact);
                }
                System.out.println("The record updated!");
            }
            case "delete" -> {
                phoneBook.removeContact(contact);
                System.out.println("The record removed.");
            }
        }
    }

    private List<Contact> search(String input) {
        List<Contact> result = phoneBook.search(input);
        if (result.isEmpty()) {
            System.out.println("Not found");
            return result;
        }
        System.out.printf("Found %d results:\n", result.size());
        int index = 0;
        for (Contact contact : result) {
            System.out.printf("%d. %s\n", ++index, contact.getFullName());
        }
        return result;
    }
    private LocalDate setBirthDate(String birthDate) {
        boolean validBirthDate = Validator.isValidBirthDate(birthDate);
        if (!validBirthDate) {
            System.out.println("Bad birth date!");
        }
        return validBirthDate ? LocalDate.parse(birthDate) : null;
    }
    private String setGender(String gender) {
        boolean validGender = Validator.isValidGender(gender);
        if (!validGender) {
            System.out.println("Bad gender!");
        }
        return validGender ? gender : "[no data]";
    }
    private String setPhoneNumber(String number) {
        boolean validNumber = Validator.isValidPhoneNumber(number);
        if (!validNumber) {
            System.out.println("Wrong number format!");
        }
        return validNumber ? number : "[no data]";
    }
    public void addOrganization() {
        System.out.print("Enter the Organization name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the Address: ");
        String address = scanner.nextLine();

        System.out.print("Enter the Number: ");
        String number = setPhoneNumber(scanner.nextLine());

        phoneBook.addContact(
                new Organization(
                        name,
                        address,
                        number
                ));
    }
    public void edit(String action) {
        int index = isCorrectIndex(action);
        if (index == -1) {
            System.out.println("Wrong index!");
            return;
        }
        Contact contact = phoneBook.getContact(index - 1);
        System.out.println(contact);

        System.out.print("\n[record] Enter action (edit, delete, menu): ");
        String nextAction = scanner.nextLine();
        switch (nextAction) {
            case "edit" -> {
                switch (contact.getClass().getSimpleName()) {
                    case "Person" -> editPerson(contact);
                    case "Organization" -> editOrganization(contact);
                }
                System.out.println("The record updated!");
            }
            case "delete" -> {
                phoneBook.removeContact(index);
                System.out.println("The record removed.");
            }
            case "menu" -> {}
        }

    }
    private void editPerson(Contact contact) {
        Person person = (Person) contact;
        System.out.print("Select a field (name, surname, birth, gender, number): ");
        String field = scanner.nextLine().toLowerCase();
        switch (field) {
            case "name":
                System.out.print("Enter name: ");
                person.setName(scanner.nextLine());
                break;
            case "surname":
                System.out.print("Enter surname: ");
                person.setSurname(scanner.nextLine());
                break;
            case "birth":
                System.out.print("Enter birth date: ");
                person.setBirthDate(setBirthDate(scanner.nextLine()));
                break;
            case "gender":
                System.out.print("Enter gender: ");
                person.setGender(setGender(scanner.nextLine()));
                break;
            case "number":
                System.out.print("Enter number: ");
                person.setPhoneNumber(setPhoneNumber(scanner.nextLine()));
                break;
            default:
                System.out.println("Incorrect field.");
        }
    }
    private void editOrganization(Contact contact) {
        Organization organization = (Organization) contact;
        System.out.print("Select a field (name, address, number): ");
        String field = scanner.nextLine().toLowerCase();
        switch (field) {
            case "name":
                System.out.print("Enter name: ");
                organization.setName(scanner.nextLine());
                break;
            case "address":
                System.out.print("Enter address: ");
                organization.setAddress(scanner.nextLine());
                break;
            case "number":
                System.out.print("Enter number: ");
                organization.setPhoneNumber(setPhoneNumber(scanner.nextLine()));
                break;
            default:
                System.out.println("Incorrect field.");
        }
    }
    public void count() {
        System.out.printf("The phone book has %s records.\n", phoneBook.getSize());
    }
    public void info() {
        if (phoneBook.getSize() == 0) {
            System.out.println("Phone book is empty.");
            return;
        }
        showInfo();

    }
    public void closeScanner() {
        scanner.close();
    }
    private void showInfo() {
        int index = 0;
        for (Contact contact : phoneBook.getPhoneBook()) {
            System.out.printf("%d. %s\n", ++index, contact.getFullName());
        }
        System.out.print("\n[list] Enter action ([number], back):");
        String action = scanner.nextLine();
        if (action.equalsIgnoreCase("back")) {
            return;
        }
        edit(action);
    }
    private int isCorrectIndex(String input) {
        int index;
        try {
            index = Integer.parseInt(input);
        } catch (Exception e) {
            index = -1;
        }
        if (index > phoneBook.getSize()) {
            index = -1;
        }
        return index;
    }
}
