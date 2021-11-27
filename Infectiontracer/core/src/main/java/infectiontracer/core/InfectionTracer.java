package infectiontracer.core;

import infectiontracer.json.FileHandler;
import java.io.IOException;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class changes the status of users and their number of close contacts. This is achieved
 * through its FileHandler object, which changes the contents in a Json-file.
 */
public class InfectionTracer {

  private final FileHandler fileHandler = new FileHandler();

  /**
   * Method to set the path of InfectionTracer and FileHandler, mostly needed for jUnit tests.
   *
   * @param path The filepath to the file that FileHandler will utilize.
   */
  public void setPath(String path) {
    fileHandler.setFilePath(path);
  }

  /**
   * Method to add a close contact for the current active user Use email for now, as each user has a
   * unique email.
   *
   * @param userEmail The email to the user trying to add a close contact.
   * @param closeContactEmail The email to the user being added as a close contact.
   */
  public void addCloseContact(String userEmail, String closeContactEmail)
      throws IOException, IllegalArgumentException {
    List<User> allUsers = fileHandler.getUsers();
    if (!checkUserList(closeContactEmail, allUsers)) {
      throw new IllegalArgumentException("The user does not exist!");
    }
    // If one user adds another as close contact, both will become a close contact to each other
    User currentUser =
        allUsers.stream().filter(user -> userEmail.equals(user.getEmail())).findAny().orElse(null);
    User closeContact =
        allUsers.stream()
            .filter(user -> closeContactEmail.equals(user.getEmail()))
            .findAny()
            .orElse(null);
    if (closeContact != null && currentUser != null) {
      currentUser.addCloseContact(closeContact.getEmail());
      closeContact.addCloseContact(currentUser.getEmail());
      fileHandler.writeUsersToFile(allUsers);
    }
  }

  /**
   * Method that removes a close contact from a user.
   *
   * @param userEmail Email to user that removes a close contact
   * @param closeContactEmail Email to close contact
   */
  public void removeCloseContact(String userEmail, String closeContactEmail)
      throws IOException, IllegalArgumentException {
    List<User> allUsers = fileHandler.getUsers();
    if (!checkUserList(userEmail, allUsers)) {
      throw new IllegalArgumentException("The user does not exist");
    }
    User currentUser =
        allUsers.stream().filter(user -> userEmail.equals(user.getEmail())).findAny().orElse(null);
    User closeContact =
        allUsers.stream()
            .filter(user -> closeContactEmail.equals(user.getEmail()))
            .findAny()
            .orElse(null);

    if (closeContact != null && currentUser != null) {
      currentUser.removeCloseContact(closeContact.getEmail());
      closeContact.removeCloseContact(currentUser.getEmail());
      fileHandler.writeUsersToFile(allUsers);
    }
  }

  /**
   * Method that changes a user's health status to 'infected'.
   *
   * @param username Username of the logged-in user.
   */
  public void makeUserInfected(String username) throws IllegalArgumentException, IOException {

    List<User> allUsers = fileHandler.getUsers();
    User currentUser =
        allUsers.stream().filter(user -> username.equals(user.getEmail())).findAny().orElse(null);

    assert currentUser != null;
    if (currentUser.getHealthStatus().equals("Infected")) {
      throw new IllegalArgumentException("User is already infected!");
    }
    currentUser.setInfected();
    currentUser.setDateOfInfected();
    fileHandler.writeUsersToFile(allUsers);
  }

  /**
   * Method that changes a user's health status to 'Covid-19 Negative'.
   *
   * @param username Username of the logged-in user.
   */
  public void makeUserHealthy(String username) throws IllegalArgumentException, IOException {
    List<User> allUsers = fileHandler.getUsers();
    User currentUser =
        allUsers.stream().filter(user -> username.equals(user.getEmail())).findAny().orElse(null);
    if (currentUser != null) {
      if (currentUser.getHealthStatus().equals("Covid-19 Negative")) {
        throw new IllegalArgumentException("User is already healthy!");
      }
      currentUser.setHealthy();
      currentUser.setDateOfHealthy();
      fileHandler.writeUsersToFile(allUsers);
    }
  }

  /**
   * Retrieves all close contacts of a user using their email.
   *
   * @param username Username of the logged-in user.
   */
  public List<User> getUsersCloseContacts(String username) throws IOException {
    List<User> users = fileHandler.getUsers();
    User currentUser =
        users.stream().filter(user -> username.equals(user.getEmail())).findAny().orElse(null);
    // Need to loop through list because a user only store their close contacts' emails.
    List<User> closeContacts = new ArrayList<>();
    if (currentUser != null) {
      users.removeIf(user -> user.getEmail().equals(username));
      for (User user : users) {
        if (currentUser.getAllCloseContacts().contains(user.getEmail())) {
          closeContacts.add(user);
        }
      }
    }
    return closeContacts;
  }

  /**
   * Helper method to get the currently logged-in user.
   *
   * @param username Username of the logged-in user.
   */
  public User getActiveUser(String username) throws IOException {
    List<User> users = fileHandler.getUsers();
    for (User user : users) {
      if (username.equals(user.getEmail())) {
        return user;
      }
    }
    throw new IllegalArgumentException("No user with that email!");
  }

  /**
   * Methods that checks if a user is already added in the Json-file. We can compare emails to
   * achieve this, because users need unique emails.
   *
   * @param userEmail Email to user being checked.
   * @param userList List of users in file.
   * @return True if user in file, false otherwise.
   */
  public Boolean checkUserList(String userEmail, List<User> userList) {
    for (User user : userList) {
      if (user.getEmail().equals(userEmail)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Method for user to delete themselves.
   *
   * @param username User to be deleted.
   */
  public void deleteUser(String username) throws IOException {
    List<User> users = fileHandler.getUsers();
    for (User user : users) {
      if (user.getAllCloseContacts().contains(username)) {
        user.removeCloseContact(username);
      }
    }
    users.removeIf(user -> user.getEmail().equals(username));
    fileHandler.writeUsersToFile(users);
  }

  /**
   * Function that attempts to insert user into users.json.
   *
   * @param newUser User that is inserted.
   */
  public void addUser(User newUser) throws IOException {
    List<User> allUsers = fileHandler.getUsers();
    if (checkUserList(newUser.getEmail(), allUsers)) {
      throw new IllegalArgumentException("User is already in file!");
    }
    allUsers.add(newUser);
    fileHandler.writeUsersToFile(allUsers);
  }

  public List<User> getUsers() throws IOException {
    return fileHandler.getUsers();
  }

  /**
   * Method to change the password of a users, using the EmailService class.
   *
   * @param username Email of the user that is having password changed.
   */
  public void changePw(String username) throws IOException, IllegalArgumentException {
    List<User> users = fileHandler.getUsers();
    if (!checkUserList(username, users)) {
      throw new IllegalArgumentException("User not in file!");
    }
    for (User user : users) {
      if (username.equals(user.getEmail())) {
        // TODO Change so that mail is only sent if user is successfully written to file
        user.setPassword(new EmailService().sendEmailWithNewPassword(user.getEmail()));
        fileHandler.writeUsersToFile(users);
      }
    }
  }

  /**
   * Method to edit a user's name and password
   *
   * @param changedUser User object with the new values
   * @throws IOException
   * @throws IllegalArgumentException
   */
  public void editUser(User changedUser) throws IOException, IllegalArgumentException {
    List<User> currentUsers = fileHandler.getUsers();
    if (!checkUserList(changedUser.getEmail(), currentUsers)) {
      throw new IllegalArgumentException("User not in file!");
    }
    for (User unchangedUser : currentUsers) {
      if (changedUser.getEmail().equals(unchangedUser.getEmail())) {
        unchangedUser.setForename(changedUser.getForename());
        unchangedUser.setLastname(changedUser.getLastname());
        unchangedUser.setPassword(changedUser.getPassword());
        fileHandler.writeUsersToFile(currentUsers);
      }
    }
  }

  public void sendEmailToCloseContacts(User user) {
    new EmailService().sendEmail(user.getEmail(), user.getAllCloseContacts());
  }
}
