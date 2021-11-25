package infectiontracer.core;

import java.beans.ConstructorProperties;
import java.lang.IllegalArgumentException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Each user of the application needs to register themselves to use the application. The user they
 * create are analogous to the User class, and these objects are what is stored in the Json-file.
 */
public class User {

  private String forename;
  private String lastname;
  private String email;
  private String password;
  private String dateOfInfection;
  private String healthStatus;
  private List<String> closeContacts = new ArrayList<>();

  /**
   * Constructor for User class.
   *
   * @param forename Forename of the user.
   * @param lastname Lastname of the user.
   * @param email Email of the user.
   * @param password Password of the user.
   * @param healthStatus Health status of the user, either 'Infected' or 'Covid-19 Negative'.
   * @param dateOfInfection Date when user was infected with Covid-19, is empty when not infected.
   */
  public User(
      String forename,
      String lastname,
      String email,
      String password,
      String healthStatus,
      String dateOfInfection) {
    setForename(forename);
    setLastname(lastname);
    setEmail(email);
    setPassword(password);
    this.healthStatus = "Covid-19 Negative";
    this.dateOfInfection = dateOfInfection;
  }

  public User() {
    super();
  }

  /**
   * Constructor for User class, where you can also set close contacts.
   *
   * @param forename Forename of the user.
   * @param lastname Lastname of the user.
   * @param email Email of the user.
   * @param password Password of the user.
   * @param healthStatus Health status of the user, either 'Infected' or 'Covid-19 Negative'.
   * @param dateOfInfection Date when user was infected with Covid-19, is empty when not infected.
   * @param allCloseContacts Close contacts of the user.
   */
  @ConstructorProperties({
    "forename",
    "lastname",
    "email",
    "password",
    "dateOfInfection",
    "healthStatus",
    "allCloseContacts"
  })
  public User(
      String forename,
      String lastname,
      String email,
      String password,
      String dateOfInfection,
      String healthStatus,
      List<String> allCloseContacts) {
    this.forename = forename;
    this.lastname = lastname;
    this.email = email;
    this.password = password;
    this.dateOfInfection = dateOfInfection;
    this.healthStatus = healthStatus;
    this.closeContacts = allCloseContacts;
  }

  /** Sets the dateOfInfection to today's date. */
  public void setDateOfInfected() {
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LLLL-dd");
    this.dateOfInfection = today.format(formatter);
  }

  /** Sets the dateOfInfection to an empty string, because the user is healthy. */
  public void setDateOfHealthy() {
    this.dateOfInfection = "";
  }

  public String getDateOfInfection() {
    return this.dateOfInfection;
  }

  /** Sets a user's health status to 'Infected'. */
  public void setInfected() {
    this.healthStatus = "Infected";
  }

  /** Sets a user's health status to 'Covid-19 Negative'. */
  public void setHealthy() {
    this.healthStatus = "Covid-19 Negative";
  }

  public String getHealthStatus() {
    return this.healthStatus;
  }

  /**
   * Helper method to check if the user that is being added as a close contact already exists as a
   * close contact for the active user.
   *
   * @return True if the user is a close contact to the active user, false otherwise.
   */
  private boolean checkIfUserAlreadyExistsAsCloseContact(String email) {
    return closeContacts.contains(email);
  }

  /** Adds a close contact to the current user. */
  public void addCloseContact(String user) {
    if (checkIfUserAlreadyExistsAsCloseContact(user)) {
      throw new IllegalArgumentException("User is already added");
    } else {
      closeContacts.add(user);
    }
  }

  /**
   * Method to remove a close contact for a user.
   *
   * @param user Email to close contact that is to be removed.
   */
  public void removeCloseContact(String user) {
    if (!checkIfUserAlreadyExistsAsCloseContact(user)) {
      throw new IllegalArgumentException("User is not a close contact!");
    } else {
      closeContacts.remove(user);
    }
  }

  public List<String> getAllCloseContacts() {
    return new ArrayList<>(closeContacts);
  }

  /**
   * Method that checks if a given forename is valid, when creating a user. Throws
   * IllegalArgumentException if invalid.
   *
   * @param forename Forename for user.
   */
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

  /**
   * Method that checks if a given lastname is valid, when creating a user. Throws
   * IllegalArgumentException if invalid.
   *
   * @param lastname Lastname for user.
   */
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

  /**
   * Method that checks if a given email is valid, when creating a user. Throws
   * IllegalArgumentException if invalid.
   *
   * @param email Email for user.
   */
  public void setEmail(String email) {
    if (!email.contains("@") || !email.contains(".com")) {
      throw new IllegalArgumentException("Invalid email");
    }
    this.email = email;
  }

  public String getEmail() {
    return this.email;
  }

  /**
   * Method that checks if a given password is valid, when creating a user. Throws
   * IllegalArgumentException if invalid.
   *
   * @param password Password for user.
   */
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

  @Override
  public String toString() {
    return this.email;
  }
}
