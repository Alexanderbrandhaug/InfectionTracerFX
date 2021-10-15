package infectiontracer.core;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;
import java.nio.charset.StandardCharsets;
import java.lang.IllegalArgumentException;

public class InfectionTracer {

    private final FileHandler fileHandler = new FileHandler();
    private String path = "src/main/resources/infectiontracer/ui/users.json";

    public void setPath(String path) {
        this.path = path;
        fileHandler.setFilePath(path);
    }

    // Method to add a close contact for the current active user
    // Use email for now, as each user has a unique email
    public void addCloseContact2(String username, String email) throws IOException {
        FileWriter writer = null;

        if (!fileHandler.checkUserList(email)) {
            throw new IllegalArgumentException("The user does not exist");

        }
        try {

            List<User> allUsers = fileHandler.getUsers();
            writer = new FileWriter(path, StandardCharsets.UTF_8);

            for (User current_user : allUsers) {
                if (current_user.getEmail().contains(username)) {
                    for (User current_user2 : allUsers) {
                        if (current_user2.getEmail().equals(email)) {
                            current_user.addCloseContact(new User(current_user2.getForname(),
                                    current_user2.getLastname(), current_user2.getEmail(), current_user2.getPassword(),
                                    current_user2.getHealthStatus(), current_user2.getDateOfInfection()));

                        }
                    }
                }
            }

            Gson gson = new Gson();
            gson.toJson(allUsers, writer);
            writer.flush();
            writer.close();

        } catch (

        IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // retrieving all closecontact of a user with the help of the email
    public List<User> getRelevantMap(String username) throws IOException {
        List<User> users = fileHandler.getUsers();
        System.out.println(users.toString());
        for (User currentUser : users) {
            if (currentUser.getEmail().contains(username)) {
                return currentUser.getAllCloseContacts();
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
