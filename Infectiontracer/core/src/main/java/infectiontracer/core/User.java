package infectiontracer.core;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class User {

    private String forname, lastname, email, password, dateOfInfection, healthStatus;
    private List<User> closeContacts;

    public User(String forname, String lastname, String email, String password, String healthStatus,
            String dateOfInfection) {
        setForname(forname);
        setLastname(lastname);
        setEmail(email);
        setPassword(password);
        this.healthStatus = "Frisk";
        closeContacts = new ArrayList<User>();
        this.dateOfInfection = dateOfInfection;

    }

    public User(String forname, String lastname, String email, String password, String healthStatus,
            String dateOfInfection, List<User> closeContacts) {
        setForname(forname);
        setLastname(lastname);
        setEmail(email);
        setPassword(password);
        this.closeContacts = closeContacts;
        this.healthStatus = "Frisk";
        this.dateOfInfection = dateOfInfection;
    }

    public void setDateOfInfected() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LLLL-dd");
        String formattedString = today.format(formatter);
        this.dateOfInfection = formattedString;
    }

    public String getDateOfInfection() {
        return this.dateOfInfection;
    }

    public void setInfected(String infected) {
        this.healthStatus = infected;
    }

    public String getHealthStatus() {
        return this.healthStatus;
    }

    public void addCloseContact(User user) {
        if (!closeContacts.contains(user)) {
            closeContacts.add(user);
        }
    }

    public List<User> getAllCloseContacts() {
        return this.closeContacts;
    }

    public void setForname(String forname) {
        Pattern pattern = Pattern.compile("[^a-zA-Z]");
        Matcher match = pattern.matcher(forname);
        if (match.find() || forname.isEmpty()) {
            throw new IllegalArgumentException("Invalid forname");
        }
        this.forname = forname.trim();
    }

    public String getForname() {
        return this.forname;
    }

    public void setLastname(String lastname) {

        Pattern pattern = Pattern.compile("[^a-zA-Z]");
        Matcher match = pattern.matcher(lastname);
        if (match.find() || lastname.isEmpty()) {
            throw new IllegalArgumentException("Invalid lastname");
        }
        this.lastname = lastname.trim();
    }

    public String getLastname() {
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

    }

}