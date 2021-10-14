package infectiontracer.core;

import infectiontracer.core.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.*;


@TestInstance(Lifecycle.PER_CLASS)
public class InfectionTracerTest {
    private User user, user2, user3, user4;
    private InfectionTracer infectiontracer = new InfectionTracer();
    private FileHandler filehandler = new FileHandler();

    
    @BeforeAll
    public void setup() {
        user = new User("Ola", "Nordmann", "test@gmail.com", "Passord321!", "frisk", "");
        user2 = new User("TestA", "Testesen", "test12@gmail.com", "Passord321!", "frisk", "");
        user3 = new User("TestB", "TestesenTo", "test123@gmail.com", "Passord321!", "frisk", "");
        user4 = new User("TestB", "TestesenTo", "test19@gmail.com", "Passord321!", "frisk", "");
        filehandler.setFilePath("src/test/java/infectiontracer/user_test.json");
        infectiontracer.setPath("src/test/java/infectiontracer/user_test.json");
        
      
        
       
       

    }

    @Test
    public void testActivateUsers() throws IOException {
        filehandler.insertUser(user);
        assertNotNull(filehandler.getUsers());
        
    }

    @Test
    public void testAddInvalidUser() throws IOException {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            infectiontracer.addCloseContact2(user.getEmail(), user2.getEmail());
        });
    }

    @Test
    public void testRegisterDuplicateUser() throws IOException {
        filehandler.insertUser(user2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            filehandler.insertUser(user2);
        });
        
    }

    @Test
    public void testAddValidUser() throws IOException {
        System.out.println(user2.getEmail());
        System.out.println(user.getEmail());
       // infectiontracer.addCloseContact2(user.getEmail(), user2.getEmail());

    }

    
   



   

}