package infectiontracer.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import infectiontracer.core.User;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Class responsible for writing and reading user objects to and from Json-files. */
public class FileHandler {

  String filePath = System.getProperty("user.home") + File.separator + "users.json";
  final Gson gson = new Gson();

  /**
   * If a json-file for the application does not exist, then make it. Primarily necessary the first
   * time a user starts the application.
   */
  public FileHandler() {
    String filePath = System.getProperty("user.home") + File.separator + "users.json";
    if (Files.notExists(Paths.get(filePath))) {
      try {
        writeUsersToFile(new ArrayList<>());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  /**
   * Function to retrieve the list of users from the Json-file.
   *
   * @return List of users in Json-file.
   */
  public List<User> getUsers() throws IOException {
    JsonReader reader = new JsonReader(new FileReader(filePath, StandardCharsets.UTF_8));
    List<User> userList = gson.fromJson(reader, new TypeToken<List<User>>() {}.getType());
    reader.close();
    return Objects.requireNonNullElseGet(userList, ArrayList::new);
  }

  /**
   * Function to write users to the Json file.
   *
   * @param userList List of users to write to file.
   */
  public void writeUsersToFile(List<User> userList) throws IOException {
    FileWriter writer = null;
    try {
      writer = new FileWriter(filePath, StandardCharsets.UTF_8);
      gson.toJson(userList, writer);
      writer.flush();
      writer.close();
    } catch (IOException e) {
      if (writer != null) {
        writer.close();
        throw new IOException(e);
      }
    }
  }

  public User jsonToUser(String userJson) {
    return gson.fromJson(userJson, new TypeToken<User>() {}.getType());
  }

  public List<User> jsonToUserList(String userListJson) {
    return gson.fromJson(userListJson, new TypeToken<List<User>>() {}.getType());
  }

  public String userToJson(User user) {
    return gson.toJson(user);
  }
}
