package infectiontracer.core;

import infectiontracer.core.*;
import jdk.jfr.Timestamp;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class InfectionTracerTest {
    private User user, user2, user3;
    private InfectionTracer infectiontracer = new InfectionTracer();
    private FileHandler filehandler = new FileHandler();

    @BeforeEach
    public void setup() {
        user = new User("Ola", "Nordmann", "test999@gmail.com", "Passord321!", "frisk", "");
        user2 = new User("TestA", "Testesen", "test9999@gmail.com", "Passord321!", "frisk", "");
        user3 = new User("TestB", "TestesenTo", "test99999@gmail.com", "Passord321!", "frisk", "");
        filehandler.setFilePath("src/test/java/infectiontracer/user_test.json");
        infectiontracer.setPath("src/test/java/infectiontracer/user_test.json");
        filehandler.insertUser(user);

    }

    @Test
    public void testActivateUsers() throws IOException {
        filehandler.insertUser(user);
        assertNotNull(filehandler.getUsers());
    }

    @Test
    public void testAddInvalidUser() throws IOException {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            infectiontracer.addCloseContact(user.getEmail(), user2.getEmail());
        });

    }

    @Test
    public void testAddValidUser() throws IOException {
        filehandler.insertUser(user2);
        infectiontracer.addCloseContact(user.getEmail(), user2.getEmail());

    }

}