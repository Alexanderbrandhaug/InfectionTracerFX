package gr2181.smittesporer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.nio.charset.StandardCharsets;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class FileHandler {

    final String filePath;
    final Gson gson;

    public FileHandler() {
        filePath = "src/main/resources/gr2181/smittesporer/users.json"; // "src/main/java/gr2181/smittesporer/users.json";
        gson = new Gson();

    }

    // Function that attempts to insert user into users.json
    public void insertUser(User user) {

        try {

            List<User> registered_users = checkUserList(user);
            FileWriter writer = new FileWriter(filePath, StandardCharsets.UTF_8);
            if (registered_users != null) {
                registered_users.add(user);
                gson.toJson(registered_users, writer);
                writer.flush();
                writer.close();
                System.out.println("User added!");
            } else {
                System.out.println("User already in file!");
            }
        } catch (Exception e) {
            e.getStackTrace();

            // } finally {
            // writer.close();
        }
    }

    // Function to check if a user is already registered
    // Returns list of users if user not in file
    // This is so the insertUsers function can add the earlier users as well
    public List<User> checkUserList(User user) {
        try {

            JsonReader reader = new JsonReader(new FileReader(filePath, StandardCharsets.UTF_8));
            List<User> user_list = new Gson().fromJson(reader, new TypeToken<List<User>>() {
            }.getType());
            if (user_list == null) {
                return new ArrayList<>();
            }
            for (User current_user : user_list) {
                if (current_user.getEmail().contentEquals(user.getEmail())) {
                    reader.close();
                    return null;
                }
            }
            reader.close();
            return user_list;
        } catch (Exception e) {
            e.getStackTrace();
            // } finally {
            // reader.close();
        }
        return null;
    }

    // Function to check if a user in is the users.json file
    // returns true if user is in the file
    public boolean checkUserList(String email) throws IOException {
        JsonReader reader = new JsonReader(new FileReader(filePath, StandardCharsets.UTF_8));
        List<User> user_list = gson.fromJson(reader, new TypeToken<List<User>>() {
        }.getType());
        for (User current_user : user_list) {
            if (current_user.getEmail().contentEquals(email)) {
                reader.close();
                return true;
            }
        }
        return false;
    }

    public List<User> getUsers() throws IOException {
        JsonReader reader = new JsonReader(new FileReader(filePath, StandardCharsets.UTF_8));
        return gson.fromJson(reader, new TypeToken<List<User>>() {
        }.getType());
    }
}
