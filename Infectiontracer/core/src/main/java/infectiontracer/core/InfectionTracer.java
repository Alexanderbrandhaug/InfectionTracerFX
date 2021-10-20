package infectiontracer.core;

import java.util.ArrayList;
import java.util.List;
import java.lang.IllegalArgumentException;

public class InfectionTracer {

    private final FileHandler fileHandler = new FileHandler();

    // method to set the path of Infectiontracer and filehandler, mostly needed for jUnit tests
    public void setPath(String path) {
        fileHandler.setFilePath(path);
    }

    // Method to add a close contact for the current active user
    // Use email for now, as each user has a unique email
    public void addCloseContact(String username, String email) {
        if (!fileHandler.checkUserList(email)) {
            throw new IllegalArgumentException("The user does not exist");
        }
        List<User> allUsers = fileHandler.getUsers();
        // If one user adds another as close contact, both will become a close contact to each other
        User currentUser = allUsers.stream().filter(user -> username.equals(user.getEmail())).findAny().orElse(null);
        User closeContact = allUsers.stream().filter(user -> email.equals(user.getEmail())).findAny().orElse(null);
        if (closeContact != null && currentUser != null) {
            currentUser.addCloseContact(closeContact.getEmail());
            closeContact.addCloseContact(currentUser.getEmail());
            fileHandler.writeUsersToFile(allUsers);
        }
    }

    // Method that changes a user's health status to 'infected'
    public void makeUserInfected(String username) throws IllegalArgumentException {
        List<User> allUsers = fileHandler.getUsers();
        User currentUser = allUsers.stream().filter(user -> username.equals(user.getEmail())).findAny().orElse(null);

        if (currentUser != null) {
            if (currentUser.getHealthStatus().equals("Infected")) {
                throw new IllegalArgumentException("User is already infected!");
            }
            currentUser.setInfected();
            currentUser.setDateOfInfected();
            fileHandler.writeUsersToFile(allUsers);
        }
    }
    // Method that changes a user's health status to 'Covid-19 Negative'
    public void makeUserHealthy(String username) throws IllegalArgumentException {
        List<User> allUsers = fileHandler.getUsers();
        User currentUser = allUsers.stream().filter(user -> username.equals(user.getEmail())).findAny().orElse(null);
        if (currentUser != null) {
            if (currentUser.getHealthStatus().equals("Covid-19 Negative")) {
                throw new IllegalArgumentException("User is already healthy!");
            }
            currentUser.setHealthy();
            currentUser.setDateOfHealthy();
            fileHandler.writeUsersToFile(allUsers);
        }
    }

    // retrieving all closecontacts of a user with the help of the email
    public List<User> getUsersCloseContacts(String username) {
        List<User> users = fileHandler.getUsers();
        User currentUser = users.stream().filter(user -> username.equals(user.getEmail())).findAny().orElse(null);
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

    // Helper method to get the currently logged-in user
    public User getActiveUser(String username) {
        List<User> users = fileHandler.getUsers();
        for (User user : users) {
            if (username.equals(user.getEmail())) {
                return user;
            }
        }
        return null;
    }

}
