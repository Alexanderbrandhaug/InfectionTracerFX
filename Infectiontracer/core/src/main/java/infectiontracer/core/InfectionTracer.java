package infectiontracer.core;

import infectiontracer.json.FileHandler;
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
  public void addCloseContact(String userEmail, String closeContactEmail) {
    if (!fileHandler.checkUserList(closeContactEmail)) {
      throw new IllegalArgumentException("The user does not exist");
    }
    List<User> allUsers = fileHandler.getUsers();
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
  public void removeCloseContact(String userEmail, String closeContactEmail) {
    if (!fileHandler.checkUserList(closeContactEmail)) {
      throw new IllegalArgumentException("The user does not exist");
    }
    List<User> allUsers = fileHandler.getUsers();
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
  public void makeUserInfected(String username) throws IllegalArgumentException {

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
  public void makeUserHealthy(String username) throws IllegalArgumentException {
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
  public List<User> getUsersCloseContacts(String username) {
    List<User> users = fileHandler.getUsers();
    User currentUser =
        users.stream().filter(user -> username.equals(user.getEmail())).findAny().orElse(null);
    List<User> closeContacts = new ArrayList<>();
    if (currentUser != null) {
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
  public User getActiveUser(String username) {
    List<User> users = fileHandler.getUsers();
    for (User user : users) {
      if (username.equals(user.getEmail())) {
        return user;
      }
    }
    return null;
  }

  /**
   * Method for user to delete themself.
   *
   * @param username User to be deleted.
   */
  public void deleteUser(String username) {
    List<User> users = fileHandler.getUsers();
    for (User user : users) {
      if (user.getAllCloseContacts().contains(username)) {
        user.removeCloseContact(username);
      }
    }
    users.removeIf(user -> user.getEmail().equals(username));
    fileHandler.writeUsersToFile(users);
  }

  public void addUser(User newUser) {
    fileHandler.insertUser(newUser);
  }

  public List<User> getUsers() {
    return fileHandler.getUsers();
  }

  // made it boolean in order to not get 500 error when trying to updatepw on server-side

  /**
   * Method to change the password of a users, using the EmailService class.
   *
   * @param username Email of the user that is having password changed.
   * @return True if successful, false otherwise.
   */
  public boolean changePw(String username) {
    List<User> users = fileHandler.getUsers();
    for (User user : users) {
      if (username.equals(user.getEmail())) {
        user.setPassword(new EmailService().sendEmailWithNewPassword(user.getEmail()));
        fileHandler.writeUsersToFile(users);
        return true;
      }
    }
    return false;
  }

  public void editUser(User user){
   List<User> currentUsers = fileHandler.getUsers();
   for(User userToBeEdit: currentUsers){
     if(user.getEmail().equals(userToBeEdit.getEmail()) && user.getPassword().equals(userToBeEdit.getPassword())){
       userToBeEdit.setForename(user.getForename());
       userToBeEdit.setLastname(user.getLastname());
       fileHandler.writeUsersToFile(currentUsers);
     }
     else if(user.getEmail().equals(userToBeEdit.getEmail()) && !user.getPassword().equals(userToBeEdit.getPassword())){
      userToBeEdit.setForename(user.getForename());
      userToBeEdit.setLastname(user.getLastname());
      userToBeEdit.setPassword(user.getPassword());
      fileHandler.writeUsersToFile(currentUsers);
     }
   }
  }

  public void sendEmailToCloseContacts(User user) {
    new EmailService().sendEmail(user.getEmail(), user.getAllCloseContacts());
  }
}
