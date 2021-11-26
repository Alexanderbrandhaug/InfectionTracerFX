package infectiontracer.ui;

import infectiontracer.rest.InfectionTracerApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import infectiontracer.core.User;
import org.springframework.test.context.ContextConfiguration;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;
import infectiontracer.json.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(classes = {InfectionTracerApplication.class})
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class InfectionTracerApiTest {

  @Autowired private TestRestTemplate testRestTemplate;
  private final FileHandler fileHandler = new FileHandler();
  private List<User> actualUsersList;

  @BeforeAll
  public void setupFile() {

    actualUsersList = fileHandler.getUsers();
    User testUser = new User("test", "test", "test@gmail.com", "Passord123", "", "");
    List<User> testUsers = new ArrayList<>();
    testUsers.add(testUser);
    fileHandler.writeUsersToFile(testUsers);
  }

  @AfterAll
  public void restoreFile() {
    fileHandler.writeUsersToFile(actualUsersList);
  }

  @Test
  void httpgetUser() {
    ResponseEntity<User> response =
        testRestTemplate.getForEntity("/infectiontracer/user/test@gmail.com", User.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
  }

  @Test
  void httpgetInvalidUser() {
    ResponseEntity<User> response =
        testRestTemplate.getForEntity("/infectiontracer/user/test", User.class);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertNull(response.getBody());
  }
}
