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

public class FileHandler {

    private String filePath;
    final Gson gson;

    public FileHandler() {
        filePath = "src/main/resources/infectiontracer/ui/users.json"; // "src/main/java/gr2181/infectiontracer/users.json";
        gson = new Gson();

    }

    public void setFilePath(String path) {
        this.filePath = path;
    }

    // Function that attempts to insert user into users.json
    public void insertUser(User user) {
        FileWriter writer = null;
        try {

            List<User> registered_users = checkUserList(user);
            writer = new FileWriter(filePath, StandardCharsets.UTF_8);
            if (registered_users != null) {
                registered_users.add(user);
                gson.toJson(registered_users, writer);
                writer.flush();
                writer.close();
                System.out.println("User added!");
            } else {
                System.out.println("User already in file!");
            }
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

    // Function to check if a user is already registered
    // Returns list of users if user not in file
    // This is so the insertUsers function can add the earlier users as well
    public List<User> checkUserList(User user) {
        JsonReader reader = null;
        try {

            reader = new JsonReader(new FileReader(filePath, StandardCharsets.UTF_8));
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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // Function to check if a user in is the users.json file
    // returns true if user is in the file
    public boolean checkUserList(String email) {
        try (JsonReader reader = new JsonReader(new FileReader(filePath, StandardCharsets.UTF_8))) {

            while (reader.hasNext()) {
                List<User> user_list = gson.fromJson(reader, new TypeToken<List<User>>() {
                }.getType());
                for (User current_user : user_list) {
                    if (current_user.getEmail().equals(email)) {
                        reader.close();
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<User> getUsers() {
        try (JsonReader reader = new JsonReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
            while (reader.hasNext()) {
                return gson.fromJson(reader, new TypeToken<List<User>>() {
                }.getType());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
