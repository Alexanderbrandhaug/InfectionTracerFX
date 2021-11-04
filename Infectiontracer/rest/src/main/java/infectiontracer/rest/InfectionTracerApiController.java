package infectiontracer.rest;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import infectiontracer.core.*;
import infectiontracer.json.FileHandler;

@RestController
public class InfectionTracerApiController {

    private final FileHandler filehandler = new FileHandler();
    private final InfectionTracer infectionTracer = new InfectionTracer();

    @GetMapping("/infectiontracer/users")
    protected List<User> getAllActiveUsersApi() {
           return filehandler.getUsers();
            
    }

    @GetMapping("/infectiontracer/user/{email}")
    protected User getUserByEmailApi(@PathVariable String email) {
        return infectionTracer.getActiveUser(email);
    }

    @PostMapping(path = "/infectiontracer/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    protected boolean newUserApi(@RequestBody /*String json_file*/ User newUser) {
        filehandler.insertUser(newUser);
             return true;
    }

    @PutMapping(path = "/infectiontracer/user/{email}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    protected boolean newUserApi(@PathVariable String email, @RequestBody User newUser){
        filehandler.insertUser(newUser);
        return true;
    }
}
