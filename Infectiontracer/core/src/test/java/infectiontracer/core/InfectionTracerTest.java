package infectiontracer.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.TestInstance.Lifecycle;

import org.junit.jupiter.api.*;
import infectiontracer.json.*;

@TestInstance(Lifecycle.PER_CLASS)
public class InfectionTracerTest {
  private User user, user2, user3, user4, user5, user6, user7;
  private final InfectionTracer infectiontracer = new InfectionTracer();
  private final FileHandler filehandler = new FileHandler();
  File tempJsonFile;
  final String testFilePath = System.getProperty("user.home") + File.separator + "user_test.json";

  @BeforeAll
  public void setUpUsers() throws IOException {
    user = new User("Ola", "Nordmann", "test@gmail.com", "Passord321!", "frisk", "");
    user2 = new User("TestA", "Testesen", "dummy@gmail.com", "Passord321!", "frisk", "");
    user3 = new User("TestB", "TestesenTo", "dwadwa@gmail.com", "Passord321!", "frisk", "");
    user4 = new User("TestB", "TestesenTo", "tesdwa@gmail.com", "Passord321!", "frisk", "");
    user5 = new User("TestB", "TestesenTo", "tesdwa222@gmail.com", "Passord321!", "frisk", "");
    user6 = new User("TestB", "TestesenTo", "tsdwa222@gmail.com", "Passord321!", "frisk", "");
    user7 = new User("TestB", "TestesenTo", "dwa222@gmail.com", "Passord321!", "frisk", "");
    tempJsonFile = new File(testFilePath);
    tempJsonFile.createNewFile();
    filehandler.setFilePath(testFilePath);
    infectiontracer.setPath(testFilePath);
  }

  @AfterEach
  public void setupExit() {
    try {
      new PrintWriter(testFilePath).close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  @AfterAll
  public void closeFile() {
    tempJsonFile.deleteOnExit();
  }

  @Test
  public void testActivateUsers() {
    filehandler.insertUser(user);
    assertNotNull(filehandler.getUsers());
  }

  @Test
  public void testAddInvalidUser() {
    filehandler.insertUser(user);
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> infectiontracer.addCloseContact(user.getEmail(), user3.getEmail()));
  }

  @Test
  public void testRegisterDuplicateUser() {
    filehandler.insertUser(user2);
    Assertions.assertThrows(IllegalArgumentException.class, () -> filehandler.insertUser(user2));
  }

  @Test
  public void testAddValidUser() {
    filehandler.insertUser(user3);
    filehandler.insertUser(user4);
    infectiontracer.addCloseContact(user4.getEmail(), user3.getEmail());
  }

  @Test
  public void testMakeUserInfected() {
    filehandler.insertUser(user);
    infectiontracer.makeUserInfected(user.getEmail());
    Assertions.assertEquals(
        "Infected", infectiontracer.getActiveUser(user.getEmail()).getHealthStatus());
  }

  @Test
  public void testMakeUserHealthy() {
    filehandler.insertUser(user5);
    infectiontracer.makeUserInfected(user5.getEmail());
    infectiontracer.makeUserHealthy(user5.getEmail());
    Assertions.assertEquals(
        "Covid-19 Negative", infectiontracer.getActiveUser(user5.getEmail()).getHealthStatus());
  }

  @Test
  public void testGetUsersCloseContacts() {
    filehandler.insertUser(user6);
    filehandler.insertUser(user7);
    infectiontracer.addCloseContact(user6.getEmail(), user7.getEmail());
    List<User> liste = infectiontracer.getUsersCloseContacts(user6.getEmail());
    Assertions.assertEquals(user7.getEmail(), liste.get(0).getEmail());
  }
}
