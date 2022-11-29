package contacts.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private final static Pattern phoneNumberPattern = Pattern.compile(
            "\\+?((\\([0-9A-Za-z]+\\)|[0-9A-Za-z]+)|" +
                    "([0-9A-Za-z]+[ -]\\([0-9A-Za-z]{2,}\\))|" +
                    "[0-9A-Za-z]+[ -][0-9A-Za-z]{2,})([ -][0-9A-Za-z]{2,}[ -]?)*"
    );

    public static boolean isValidPhoneNumber(String number) {
        Matcher matcher = phoneNumberPattern.matcher(number);
        return matcher.matches();
    }

    public static boolean isValidBirthDate(String birthDate) {
        try {
            LocalDate.parse(birthDate);
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }

    public static boolean isValidGender(String gender) {
        return gender.equals("M") || gender.equals("F");
    }

}
