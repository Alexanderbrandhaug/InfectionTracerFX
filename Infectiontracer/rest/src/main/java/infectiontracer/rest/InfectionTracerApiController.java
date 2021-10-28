package infectiontracer.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import infectiontracer.core.*;
import infectiontracer.json.FileHandler;

@RestController
public class InfectionTracerApiController {

    private FileHandler filehandler = new FileHandler();

    @GetMapping("/infectiontracer/users")
    public String index() {
        return filehandler.getUsers().toString();
    }

}
