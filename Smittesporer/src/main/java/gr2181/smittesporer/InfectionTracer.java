package gr2181.smittesporer;

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

public class InfectionTracer {

    private final FileHandler fileHandler = new FileHandler();
    // Method to add a close contact for a user
    // Use email for now, as each user has a unique email
    public void addCloseContact(String username, String email) throws IOException {
        if (!fileHandler.checkUserList(email)) {
            System.out.println("No such user in file!");
            return;
        }
        List<User> allUsers = fileHandler.getUsers();
        FileWriter writer = new FileWriter("src/main/resources/gr2181/smittesporer/users.json");

        for (User current_user : allUsers) {
            if (current_user.getEmail().contains(username)) {
                current_user.getCloseContacts().put(email, LocalDate.now().toString());
            }
        }
        Gson gson = new Gson();
        gson.toJson(allUsers, writer);
        writer.flush();
        writer.close();
    }
    public SortedMap<String, String> getRelevantMap(String username) throws FileNotFoundException {
        List<User> users = fileHandler.getUsers();
        for (User currentUser : users) {
            if (currentUser.getEmail().contains(username)) {
                return currentUser.getCloseContacts();
            }
        }
        return null;
    }
}
