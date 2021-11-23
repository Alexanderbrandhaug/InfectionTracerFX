package infectiontracer.integrationtests;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import infectiontracer.core.User;
import org.springframework.test.context.ContextConfiguration;
import infectiontracer.rest.*;



@ContextConfiguration(classes = { InfectionTracerApplication.class })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class InfectiontracerTest {
 
  @Autowired
  private TestRestTemplate testRestTemplate;

  
  @Test
  void httpClientExample() {
    ResponseEntity<User> response = testRestTemplate.getForEntity("/infectiontracer/user/test@gmail.com", User.class);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNotNull(response.getBody());
    
  } 
  }
  