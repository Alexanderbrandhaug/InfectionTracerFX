package infectiontracer.core;

public class InfectionTracerTest {
    private User user;

    @BeforeEach
    public void setup() {
        user = new User("Ola", "Nordmann", "test@gmail.com", "Passord321!", false, "");
    }

}