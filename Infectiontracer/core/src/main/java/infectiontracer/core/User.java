package infectiontracer.core;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import java.util.regex.Matcher;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.lang.IllegalArgumentException;

public class User {

    private String forename, lastname, email, password, dateOfInfection, healthStatus;
    private List<String> closeContacts;

    public User(String forename, String lastname, String email, String password, String healthStatus,
                String dateOfInfection) {
        setForename(forename);
        setLastname(lastname);
        setEmail(email);
        setPassword(password);
        this.healthStatus = "Covid19 Negative";
        closeContacts = new ArrayList<String>();
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

    // Helper method to check if the user that is being added as a closecontact
    // already
    // exists as a closecontact for the active user
    public boolean checkIfUserAlreadyExistsAsCloseContact(String email) {
        return closeContacts.contains(email);
    }

    // Adding a closecontact to the current user
    public void addCloseContact(String user) {
        if (checkIfUserAlreadyExistsAsCloseContact(user)) {
            throw new IllegalArgumentException("User is already added");
        } else {
            closeContacts.add(user);
        }
    }

    public List<String> getAllCloseContacts() {
        return new ArrayList<String>(closeContacts);
    }

    public void setForename(String forename) {
        Pattern pattern = Pattern.compile("[^a-zA-Z]");
        Matcher match = pattern.matcher(forename);
        if (match.find() || forename.isEmpty()) {
            throw new IllegalArgumentException("Invalid forename");
        }
        this.forename = forename.trim();
    }

    public String getForename() {
        return this.forename;
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
        if (!email.contains("@") || !email.contains(".com")) {
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

}