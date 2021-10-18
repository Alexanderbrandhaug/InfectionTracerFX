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
    private String path = "C:\\Users\\sylte\\GitProjects\\IT1901\\Release 1\\gr2181\\Infectiontracer\\core\\src\\main\\java\\infectiontracer\\core\\users.json";

    // method to set the path of Infectiontracer and filehandler, mostly needed for jUnit tests
    public void setPath(String path) {
        this.path = path;
        fileHandler.setFilePath(path);
    }

    // Method to add a close contact for the current active user
    // Use email for now, as each user has a unique email
    public void addCloseContact2(String username, String email) throws IOException {
        if (!fileHandler.checkUserList(email)) {
            throw new IllegalArgumentException("The user does not exist");
        }

        try {
            List<User> allUsers = fileHandler.getUsers();
            FileWriter writer = new FileWriter(path, StandardCharsets.UTF_8);
            try {
                for (User current_user : allUsers) {
                    if (current_user.getEmail().contains(username)) {
                        for (User current_user2 : allUsers) {
                            if (current_user2.getEmail().equals(email)) {
                                current_user.addCloseContact(current_user2.getEmail());
                            }
                        }
                    }
                }
                Gson gson = new Gson();
                gson.toJson(allUsers, writer);
                writer.flush();
            } finally {
                writer.close();
            }
        } catch (
        IOException e) {
            e.printStackTrace();
        }
    }

    // retrieving all closecontact of a user with the help of the email
    public List<User> getRelevantMap(String username) throws IOException {
        List<User> users = fileHandler.getUsers();
        System.out.println(users.toString());
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
    public User getActiveUser(String username) throws IOException {
        List<User> users = fileHandler.getUsers();
        for (User user : users) {
            if (username.equals(user.getEmail())) {
                return user;
            }
        }
        return null;
    }
}
