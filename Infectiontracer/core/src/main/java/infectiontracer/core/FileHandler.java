package infectiontracer.core;

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
import java.lang.IllegalArgumentException;

public class FileHandler {

    private String filePath = "src/main/resources/infectiontracer/ui/users.json";
    final Gson gson;

    public FileHandler() {
        // "src/main/java/gr2181/infectiontracer/users.json";
        gson = new Gson();

    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    // Function that attempts to insert user into users.json
    public void insertUser(User user) {
        FileWriter writer = null;
        try {

            List<User> registered_users = getUsers();

            if (registered_users == null) {
                registered_users = new ArrayList<>();
            }
            writer = new FileWriter(filePath, StandardCharsets.UTF_8);
            for (User newUser : registered_users) {
                if (user.getEmail().equals(newUser.getEmail())) {
                    throw new IllegalArgumentException("Email already exists");
                }
            }
            registered_users.add(user);
            gson.toJson(registered_users, writer);
            writer.flush();
            writer.close();
            System.out.println("User added!");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    // Function to check if a user in is the users.json file
    // returns true if user is in the file
    public boolean checkUserList(String email) {
        try (JsonReader reader = new JsonReader(new FileReader(filePath, StandardCharsets.UTF_8))) {

            List<User> user_list = gson.fromJson(reader, new TypeToken<List<User>>() {
            }.getType());
            for (User current_user : user_list) {
                if (current_user.getEmail().equals(email)) {
                    reader.close();
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<User> getUsers() {
        try  {
            JsonReader reader = new JsonReader(new FileReader(filePath, StandardCharsets.UTF_8));
            try {
                return gson.fromJson(reader, new TypeToken<List<User>>() {}.getType());
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
