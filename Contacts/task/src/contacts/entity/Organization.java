package contacts.entity;

import java.time.LocalDateTime;

public class Organization extends Contact {

    private String address;

    public Organization(String name, String address, String phoneNumber) {
        super(name, phoneNumber);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        setLastEdit(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Organization name: " + getName() + "\n"
                + "Address: " + getAddress() + "\n"
                + "Number: " + getPhoneNumber() + "\n"
                + "Time created: " + getTimeCreated() + "\n"
                + "Time last edit: " + getLastEdit();
    }

    @Override
    public String getFullName() {
        return getName();
    }
}
