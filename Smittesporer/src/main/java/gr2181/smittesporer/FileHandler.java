package gr2181.smittesporer;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    final String filePath;
    final Gson gson;

    public FileHandler() {
        filePath = "src/main/resources/gr2181/smittesporer/users.json"; //"src/main/java/gr2181/smittesporer/users.json";
        gson = new Gson();

    }

    // Function that attempts to insert user into users.json
    public void insertUser(User user) throws IOException {
        List<User> registered_users = checkUserList(user);
        if (registered_users != null) {
            FileWriter writer = new FileWriter(filePath);
            registered_users.add(user);
            JsonArray all_data_array = new JsonArray();
            for (User current_user : registered_users) {
                JsonObject each_data = new JsonObject();
                each_data.addProperty("forname", current_user.getForName());
                each_data.addProperty("lastname", current_user.getLastName());
                each_data.addProperty("email", current_user.getEmail());
                each_data.addProperty("password", current_user.getPassword());
                all_data_array.add(each_data);
            }
            gson.toJson(all_data_array, writer);
            writer.flush();
            writer.close();
            System.out.println("User added!");
        }
        else {
            System.out.println("User already in file!");
        }
    }

    // Function to check if a user is already registered
    // Returns list of users if user not in file
    // This is so the insertUsers function can add the earlier users as well
    private List<User> checkUserList(User user) throws IOException {
        JsonReader reader = new JsonReader(new FileReader(filePath));
        List<User> user_list = new Gson().fromJson(reader, new TypeToken<List<User>>() {}.getType());
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
    }
}
