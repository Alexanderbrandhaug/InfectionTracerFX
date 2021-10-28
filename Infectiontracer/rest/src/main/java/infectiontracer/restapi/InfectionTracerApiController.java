package infectiontracer.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfectionTracerApiController {

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
