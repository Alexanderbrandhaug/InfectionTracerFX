package infectiontracer.ui;

import infectiontracer.rest.InfectionTracerApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import infectiontracer.core.InfectionTracer;
import infectiontracer.core.User;
import org.springframework.test.context.ContextConfiguration;
import org.junit.jupiter.api.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import java.beans.Transient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import infectiontracer.json.*;
import java.net.URI;
import java.net.URISyntaxException;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.http.HttpMethod;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(classes = {InfectionTracerApplication.class})
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class InfectionTracerApiTest {

  @Autowired private TestRestTemplate testRestTemplate;
  private final FileHandler fileHandler = new FileHandler();
  private List<User> actualUsersList;
  private User testUser;
  private InfectionTracer infectiontracer = new InfectionTracer();
 

  @BeforeAll
  public void setupFile() throws IOException{

    actualUsersList = fileHandler.getUsers();
    testUser = new User("test", "test", "test@gmail.com", "Passord123", "", "");
    List<User> testUsers = new ArrayList<>();
    testUsers.add(testUser);
    fileHandler.writeUsersToFile(testUsers);
   
  }

  @AfterAll
  public void restoreFile() throws IOException{
    fileHandler.writeUsersToFile(actualUsersList);
  }

  @Test
  void httpGetUser() {
    ResponseEntity<User> response =
        testRestTemplate.getForEntity("/infectiontracer/user/test@gmail.com", User.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
  }

  @Test
  void httpGetInvalidUser() {
    ResponseEntity<User> response =
        testRestTemplate.getForEntity("/infectiontracer/user/test", User.class);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertNull(response.getBody());
  }

  @Test
  void httpGetAllUsers() {
    ResponseEntity<User[]> response =
        testRestTemplate.getForEntity("/infectiontracer/users", User[].class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
  }

   @Test
   void httpGetUserCloseContact(){
      ResponseEntity<User[]> response =
          testRestTemplate.getForEntity("/infectiontracer/user/test@gmail.com/closecontacts", User[].class);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertNotNull(response.getBody());
    }

    @Test
    void postNewUser() throws URISyntaxException{
      URI uri = new URI("/infectiontracer/users");
      HttpHeaders headers = new HttpHeaders();
      headers.set("X-COM-PERSIST", "true"); 
     

      User testUser2 = new User("test", "test", "dummy@gmail.com", "Passord123", "", "");
      HttpEntity<User> request = new HttpEntity<>(testUser2, headers);
      ResponseEntity<String> result = testRestTemplate.postForEntity(uri, request, String.class);
      assertEquals(200, result.getStatusCodeValue());
    }
   
    @Test
    void postNewCloseContact() throws URISyntaxException{
      URI uri = new URI("/infectiontracer/user/test@gmail.com/closecontacts");
      HttpHeaders headers = new HttpHeaders();
      headers.set("X-COM-PERSIST", "true"); 
      User testUser2 = new User("test", "test", "dummy@gmail.com", "Passord123", "", "");
      HttpEntity<User> request = new HttpEntity<>(testUser2, headers);
      ResponseEntity<String> result = testRestTemplate.postForEntity(uri, request, String.class);
      assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    void postDeleteCloseContact() throws URISyntaxException, IOException{
      User testUser2 = new User("test", "test", "dummy@gmail.com", "Passord123", "", "");
      infectiontracer.addCloseContact(testUser.getEmail(), testUser2.getEmail());
      URI uri = new URI("/infectiontracer/user/test@gmail.com/closecontacts/removecontact");
      HttpHeaders headers = new HttpHeaders();
      headers.set("X-COM-PERSIST", "true"); 
      HttpEntity<User> request = new HttpEntity<>(testUser2, headers);
      ResponseEntity<String> result = testRestTemplate.postForEntity(uri,request, String.class);
      assertEquals(200, result.getStatusCodeValue());
    }
   }

