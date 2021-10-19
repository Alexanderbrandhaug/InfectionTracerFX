package infectiontracer.core;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.*;

@TestInstance(Lifecycle.PER_CLASS)
public class InfectionTracerTest {
    private User user, user2, user3, user4;
    private final InfectionTracer infectiontracer = new InfectionTracer();
    private final FileHandler filehandler = new FileHandler();

    @BeforeAll
    public void setUpUsers() {
        user = new User("Ola", "Nordmann", "test@gmail.com", "Passord321!", "frisk", "");
        user2 = new User("TestA", "Testesen", "dummy@gmail.com", "Passord321!", "frisk", "");
        user3 = new User("TestB", "TestesenTo", "dwadwa@gmail.com", "Passord321!", "frisk", "");
        user4 = new User("TestB", "TestesenTo", "tesdwa@gmail.com", "Passord321!", "frisk", "");
        filehandler.setFilePath("src/test/java/infectiontracer/user_test.json");
        infectiontracer.setPath("src/test/java/infectiontracer/user_test.json");
    }

    @AfterEach
    public void setupExit() {
        try {
            new PrintWriter("src/test/java/infectiontracer/user_test.json").close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testActivateUsers() {
        filehandler.insertUser(user);
        assertNotNull(filehandler.getUsers());

    }

    @Test
    public void testAddInvalidUser() {
        filehandler.insertUser(user);
        Assertions.assertThrows(IllegalArgumentException.class, () -> infectiontracer.addCloseContact(user.getEmail(), user3.getEmail()));
    }

    @Test
    public void testRegisterDuplicateUser() {
        filehandler.insertUser(user2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> filehandler.insertUser(user2));

    }

    @Test public void testAddValidUser() {
        filehandler.insertUser(user3);
        filehandler.insertUser(user4);
        infectiontracer.addCloseContact(user4.getEmail(), user3.getEmail());

    }


}