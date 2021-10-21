package infectiontracer.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.nio.file.Path;

import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.lang.IllegalArgumentException;
import infectiontracer.core.User;

public class FileHandler {

    // TODO Fix the filepath so that it is not absolute
    private String sysHome = System.getProperty("user.home");
    String filePath = sysHome + File.separator + "users.json";
    final Gson gson = new Gson();

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    // Function that attempts to insert user into users.json
    public void insertUser(User user) {
        List<User> registered_users = getUsers();

        for (User newUser : registered_users) {
            if (user.getEmail().equals(newUser.getEmail())) {
                throw new IllegalArgumentException("Email already exists");
            }
        }
        registered_users.add(user);
        writeUsersToFile(registered_users);
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

    // Function to retrieve the list of users from the Json file
    public List<User> getUsers() {
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(filePath, StandardCharsets.UTF_8));
            List<User> userList = gson.fromJson(reader, new TypeToken<List<User>>() {
            }.getType());
            return Objects.requireNonNullElseGet(userList, ArrayList::new);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    // Function to write users to the Json file
    public void writeUsersToFile(List<User> userList) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(filePath, StandardCharsets.UTF_8);
            gson.toJson(userList, writer);
            writer.flush();
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
}
