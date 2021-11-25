package infectiontracer.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import infectiontracer.core.User;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.IllegalArgumentException;
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
   * If a json-file for the application does not exist, then make it. Primarily necessary for the
   * first time user starts program.
   */
  public FileHandler() {
    String filePath = System.getProperty("user.home") + File.separator + "users.json";
    if (Files.notExists(Paths.get(filePath))) {
      writeUsersToFile(new ArrayList<>());
    }
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  /**
   * Method that deletes a user from the Json-file
   *
   * @param email Email to the user that is to be deleted
   * @return True if change was successful, False otherwise.
   */
  public boolean deleteUserFromFile(String email) {
    List<User> currentUsers = getUsers();
    for (User activeUser: currentUsers) {
      if (email.equals(activeUser.getEmail())) {
        currentUsers.remove(activeUser);
        writeUsersToFile(currentUsers);
        return true;
      }
    } 
    return false;
  }

  /**
   * Function that attempts to insert user into users.json.
   *
   * @param user User that is inserted.
   */
  public void insertUser(User user) {
    List<User> registeredUsers = getUsers();

    for (User newUser : registeredUsers) {
      if (user.getEmail().equals(newUser.getEmail())) {
        throw new IllegalArgumentException("Email already exists");
      }
    }
    registeredUsers.add(user);
    writeUsersToFile(registeredUsers);
  }

  /**
   * Function to check if a user in is the users.json file.
   *
   * @param email Email to the user that file is checked for.
   * @return True if user is in the file, False otherwise.
   */
  public boolean checkUserList(String email) {
    try (JsonReader reader = new JsonReader(new FileReader(filePath, StandardCharsets.UTF_8))) {

      List<User> userList = gson.fromJson(reader, new TypeToken<List<User>>() {}.getType());
      for (User currentUser : userList) {
        if (currentUser.getEmail().equals(email)) {
          reader.close();
          return true;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  /**
   * Function to retrieve the list of users from the Json-file.
   *
   * @return List of users in Json-file.
   */
  public List<User> getUsers() {
    JsonReader reader = null;
    try {
      reader = new JsonReader(new FileReader(filePath, StandardCharsets.UTF_8));
      List<User> userList = gson.fromJson(reader, new TypeToken<List<User>>() {}.getType());
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

  /**
   * Function to write users to the Json file.
   *
   * @param userList List of users to write to file.
   */
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
        if (writer != null) {
          writer.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
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
