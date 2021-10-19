package infectiontracer.core;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.lang.IllegalArgumentException;

public class InfectionTracer {

    private final FileHandler fileHandler = new FileHandler();

    // method to set the path of Infectiontracer and filehandler, mostly needed for jUnit tests
    public void setPath(String path) {
        fileHandler.setFilePath(path);
    }

    // Method to add a close contact for the current active user
    // Use email for now, as each user has a unique email
    public void addCloseContact(String username, String email) throws IOException {
        if (!fileHandler.checkUserList(email)) {
            throw new IllegalArgumentException("The user does not exist");
        }
        List<User> allUsers = fileHandler.getUsers();
        for (User current_user : allUsers) {
            if (current_user.getEmail().contains(username)) {
                for (User current_user2 : allUsers) {
                    if (current_user2.getEmail().equals(email)) {
                        // If one user adds another as close contact, both will become a close contact to each other
                        current_user.addCloseContact(current_user2.getEmail());
                        current_user2.addCloseContact(current_user.getEmail());
                    }
                }
            }
        }
        fileHandler.writeUsersToFile(allUsers);
    }

    // retrieving all closecontact of a user with the help of the email
    public List<User> getUsersCloseContacts(String username) {
        List<User> users = fileHandler.getUsers();
        for (User currentUser : users) {
            if (currentUser.getEmail().contains(username)) {
                List<User> closeContacts = new ArrayList<>();
                for (User user : users) {
                    if (currentUser.getAllCloseContacts().contains(user.getEmail())) {
                        closeContacts.add(user);
                    }
                }
                return closeContacts;
            }
        }
        return null;
    }

    // Helper method to get the currently logged in user
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
