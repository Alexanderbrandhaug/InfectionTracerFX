package infectiontracer.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import infectiontracer.core.*;

public class UserTest {
    private User user;

    @BeforeEach
    public void setup() {
        user = new User("Ola", "Nordmann", "test@gmail.com", "Passord321!", false, "");
    }

    @Test
    public void testConstructor() {
        // test for specialchar in lastname
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new User("Alex", "@Test", "test@gmail.com", "Passord321", false, "");
        });
        // test for email without a @
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new User("Alex", "Test", "testgmail.com", "Passord321", false, "");
        });
        // test for email without a dot
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new User("Alex", "Nordmann", "test@gmailcom", "Passord321", false, "");
        });
        // test for weak password <5 chars
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new User("Alex", "Nordmann", "test@gmail.com", "12345", false, "");
        });
        // test for numbers in forname
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new User("Alex12", "Nordmann", "test@gmail.com", "Passord321", false, "");
        });
    }

    @Test
    public void testSetForname() {
        user.setForname("Peter");
        Assertions.assertEquals("Peter", user.getForname());
        user.setForname("Maria");
        Assertions.assertEquals("Maria", user.getForname());
        // test to see if you are able to set forName as a whitespace
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setForname(" ");
        });
        // test to see if you can use numbers and whitespace
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setForname("Alex 92");
        });

    }

    @Test
    public void testEmail() {
        user.setEmail("test@gmail.com");
        Assertions.assertEquals("test@gmail.com", user.getEmail());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setEmail("testgmail.com");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setEmail("test@gmailcom");
        });

    }

    @Test
    public void testPassword() {
        user.setPassword("Password321");
        Assertions.assertEquals("Password321", user.getPassword());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setPassword("1234");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setPassword(" ");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setPassword("Password");
        });
    }

}
