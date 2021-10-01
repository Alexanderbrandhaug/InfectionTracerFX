package gr2181.infectiontracer;

import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class User {

    private String forname, lastname, email, password;
    private SortedMap<String, String> closeContacts;

    public User(String forname, String lastname, String email, String password) {
        setForName(forname);
        setLastName(lastname);
        setEmail(email);
        setPassword(password);
        closeContacts = new TreeMap<>();

    }

    public User(String forname, String lastname, String email, String password, SortedMap<String, String> closeContacts) {
        this.forname = forname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.closeContacts = closeContacts;
    }

    public SortedMap<String, String> getCloseContacts() {
        return closeContacts;
    }

    public void setCloseContacts(SortedMap<String, String> closeContacts) {
        this.closeContacts = closeContacts;
    }

    public void setForName(String forname) {
        Pattern pattern = Pattern.compile("[^a-zA-Z]");
        Matcher match = pattern.matcher(forname);
        if (match.find() || forname.isEmpty()) {
            throw new IllegalArgumentException("Invalid forname");
        }
        this.forname = forname.trim();
    }

    public String getForName() {
        return this.forname;
    }

    public void setLastName(String lastname) {

        Pattern pattern = Pattern.compile("[^a-zA-Z]");
        Matcher match = pattern.matcher(lastname);
        if (match.find() || lastname.isEmpty()) {
            throw new IllegalArgumentException("Invalid lastname");
        }
        this.lastname = lastname.trim();
    }

    public String getLastName() {
        return this.lastname;
    }

    public void setEmail(String email) {
        if (!email.contains("@") || !email.contains(".com") || email.isEmpty()) {
            throw new IllegalArgumentException("Invalid email");
        }
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setPassword(String password) {
        if (password.length() >= 8) {
            Pattern letter = Pattern.compile("[a-zA-z]");
            Pattern digit = Pattern.compile("[0-9]");
            Matcher hasLetter = letter.matcher(password);
            Matcher hasDigit = digit.matcher(password);

            if (hasLetter.find() && hasDigit.find()) {
                this.password = password;
            } else {
                throw new IllegalArgumentException("Not correct");
            }

        } else {
            throw new IllegalArgumentException("Not enough");
        }
    }

    public String getPassword() {
        return this.password;
    }

    public static void main(String[] args) {
        User newUser = new User("Alex", "test", "alex@gmail.com", "password321");
        // newUser.setLastName("");
        System.out.println(newUser.getLastName());

    }

}