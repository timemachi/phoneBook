package contacts.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Person extends Contact {
    private String surname;
    private String gender;
    private LocalDate birthDate;

    public Person(String name, String surname, LocalDate birthDate, String gender, String phoneNumber) {
        super(name, phoneNumber);
        this.surname = surname;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
        setLastEdit(LocalDateTime.now());
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
        setLastEdit(LocalDateTime.now());
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        setLastEdit(LocalDateTime.now());
    }

    @Override
    public String toString() {
        String birthDate = getBirthDate() == null ? "[no data]" : getBirthDate().toString();
        return "Name: " + getName() + "\n"
                + "Surname: " + getSurname() + "\n"
                + "Birth date: " + birthDate + "\n"
                + "Gender: " + getGender() + "\n"
                + "Number: " + getPhoneNumber() + "\n"
                + "Time created: " + getTimeCreated() + "\n"
                + "Time last edit: " + getLastEdit();
    }

    @Override
    public String getFullName() {
        return getName() + " " + getSurname();
    }
}
