package infectiontracer.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.TestInstance.Lifecycle;

import org.junit.jupiter.api.*;

@TestInstance(Lifecycle.PER_CLASS)
public class InfectionTracerTest {
  private User user, user2, user3, user4, user5, user6, user7;
  private final InfectionTracer infectionTracer = new InfectionTracer();
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
     if (!tempJsonFile.createNewFile()) {
       throw new IOException();
     }
    infectionTracer.setPath(testFilePath);
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
  public void testActivateUsers() throws IOException {
    infectionTracer.addUser(user);
    assertNotNull(infectionTracer.getUsers());

  }
  @Test  
  public void testEditUser() throws IOException{
    infectionTracer.addUser(user);
    user.setForename("Bjarne");
    user.setLastname("navnesen");
    infectionTracer.editUser(user);

    Assertions.assertEquals(
        "Bjarne", infectionTracer.getActiveUser(user.getEmail()).getForename());
        
  }
  
  @Test
  public void testNotifyCloseContacts() throws IOException{
    infectionTracer.addUser(user);
    infectionTracer.addUser(user2);
    infectionTracer.addUser(user3);
    infectionTracer.addCloseContact(user.getEmail(), user2.getEmail());
    infectionTracer.addCloseContact(user.getEmail(), user3.getEmail());
    infectionTracer.sendEmailToCloseContacts(user);
    
  }

  @Test 
  void testDeleteUser() throws IOException{
    infectionTracer.addUser(user);
    infectionTracer.deleteUser(user.getEmail());
    Assertions.assertEquals(
        true, infectionTracer.getUsers().isEmpty());
  }

  @Test
  void testGeneratePw() throws IOException{
    infectionTracer.addUser(user);
    String pw = user.getPassword();
    infectionTracer.changePw(user.getEmail());
    assertNotEquals(infectionTracer.getUsers().get(0).getPassword(), pw);
  }

  @Test
  void testRemoveCloseContact() throws IOException{
    infectionTracer.addUser(user);
    infectionTracer.addUser(user2);
    infectionTracer.addCloseContact(user.getEmail(), user2.getEmail());
    assertNotNull(infectionTracer.getUsersCloseContacts(user.getEmail()));
    infectionTracer.removeCloseContact(user.getEmail(), user2.getEmail());
    Assertions.assertEquals(
      true, infectionTracer.getUsersCloseContacts(user.getEmail()).isEmpty());
  }
  


  @Test
  public void testAddInvalidUser() throws IOException {
    infectionTracer.addUser(user);
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> infectionTracer.addCloseContact(user.getEmail(), user3.getEmail()));
  }

  @Test
  public void testRegisterDuplicateUser() throws IOException {
    infectionTracer.addUser(user2);
    Assertions.assertThrows(IllegalArgumentException.class, () -> infectionTracer.addUser(user2));
  }

  @Test
  public void testAddValidUser() throws IOException {
    infectionTracer.addUser(user3);
    infectionTracer.addUser(user4);
    infectionTracer.addCloseContact(user4.getEmail(), user3.getEmail());
  }

  @Test
  public void testMakeUserInfected() throws IOException {
    infectionTracer.addUser(user);
    infectionTracer.makeUserInfected(user.getEmail());
    Assertions.assertEquals(
        "Infected", infectionTracer.getActiveUser(user.getEmail()).getHealthStatus());
  }

  @Test
  public void testMakeUserHealthy() throws IOException {
    infectionTracer.addUser(user5);
    infectionTracer.makeUserInfected(user5.getEmail());
    infectionTracer.makeUserHealthy(user5.getEmail());
    Assertions.assertEquals(
        "Covid-19 Negative", infectionTracer.getActiveUser(user5.getEmail()).getHealthStatus());
  }

  @Test
  public void testGetUsersCloseContacts() throws IOException {
    infectionTracer.addUser(user6);
    infectionTracer.addUser(user7);
    infectionTracer.addCloseContact(user6.getEmail(), user7.getEmail());
    List<User> liste = infectionTracer.getUsersCloseContacts(user6.getEmail());
    Assertions.assertEquals(user7.getEmail(), liste.get(0).getEmail());
  }
}
