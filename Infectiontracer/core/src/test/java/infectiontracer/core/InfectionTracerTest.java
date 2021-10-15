package infectiontracer.core;

import infectiontracer.core.*;
import org.junit.jupiter.api.Test;

import java.io.File;
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
    private File file;

    @BeforeAll
    public void setUpUsers() {
        user = new User("Ola", "Nordmann", "test@gmail.com", "Passord321!", "frisk", "");
        user2 = new User("TestA", "Testesen", "dummy@gmail.com", "Passord321!", "frisk", "");
        user3 = new User("TestB", "TestesenTo", "dwadwa@gmail.com", "Passord321!", "frisk", "");
        user4 = new User("TestB", "TestesenTo", "tesdwa@gmail.com", "Passord321!", "frisk", "");

    }

    @BeforeEach
    public void setup() {
        filehandler.setFilePath("src/test/java/infectiontracer/user_test2.json");
        infectiontracer.setPath("src/test/java/infectiontracer/user_test2.json");
        file = new File("src/test/java/infectiontracer/user_test2.json");

    }

    @AfterEach
    public void setupExit() {
        file.deleteOnExit();
    }

    @Test
    public void testActivateUsers() throws IOException {
        filehandler.insertUser(user);
        assertNotNull(filehandler.getUsers());

    }

    @Test
    public void testAddInvalidUser() throws IOException {
        filehandler.insertUser(user);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            infectiontracer.addCloseContact2(user.getEmail(), user3.getEmail());
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
        filehandler.insertUser(user3);
        filehandler.insertUser(user4);
        infectiontracer.addCloseContact2(user3.getEmail(), user4.getEmail());

    }

}