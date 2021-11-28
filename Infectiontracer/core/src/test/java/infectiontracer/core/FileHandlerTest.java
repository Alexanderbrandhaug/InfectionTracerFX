package infectiontracer.core;

import infectiontracer.json.FileHandler;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FileHandlerTest {

    private User user, user2;
    private File tempJsonFile;
    private final FileHandler fileHandler = new FileHandler();
    final String testFilePath = System.getProperty("user.home") + File.separator + "user_test_fileHandler.json";


    @BeforeAll
    public void setUpUsers() throws IOException {
        user = new User("Ola", "Nordmann", "test@gmail.com", "Passord321!", "frisk", "");
        user2 = new User("TestA", "Testesen", "dummy@gmail.com", "Passord321!", "frisk", "");
        tempJsonFile = new File(testFilePath);
        if (!tempJsonFile.createNewFile()) {
            throw new IOException();
        }
        fileHandler.setFilePath(testFilePath);
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
    public void testGson() {
    Assertions.assertEquals(fileHandler.jsonToUser(fileHandler.userToJson(user)), user);
    }

    @Test
    public void testGetUsers() throws IOException {
        ArrayList<User> userArrayList = new ArrayList<>();
        userArrayList.add(user);
        userArrayList.add(user2);
        fileHandler.writeUsersToFile(userArrayList);
        Assertions.assertEquals(fileHandler.getUsers(), userArrayList);
    }

}
