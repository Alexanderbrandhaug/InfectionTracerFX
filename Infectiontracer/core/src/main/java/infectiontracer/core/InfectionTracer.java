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

public class InfectionTracer {

    private final FileHandler fileHandler = new FileHandler();

    // Method to add a close contact for a user
    // Use email for now, as each user has a unique email
    public void addCloseContact(String username, String email) throws IOException {
        FileWriter writer = null;

        if (!fileHandler.checkUserList(email)) {
            System.out.println("No such user in file!");
            return;
        }
        try {
            List<User> allUsers = fileHandler.getUsers();
            writer = new FileWriter("/fxui/src/main/resources/infectiontracer/users.json", StandardCharsets.UTF_8);

            for (User current_user : allUsers) {
                if (current_user.getEmail().contains(username)) {
                    current_user.getCloseContacts().put(email, LocalDate.now().toString());
                }
            }
            Gson gson = new Gson();
            gson.toJson(allUsers, writer);
            writer.flush();
            writer.close();

        } catch (IOException e) {
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

    public SortedMap<String, String> getRelevantMap(String username) throws IOException {
        List<User> users = fileHandler.getUsers();
        for (User currentUser : users) {
            if (currentUser.getEmail().contains(username)) {
                return currentUser.getCloseContacts();
            }
        }
        return null;
    }
}
