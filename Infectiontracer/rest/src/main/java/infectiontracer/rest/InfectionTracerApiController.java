package infectiontracer.rest;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import infectiontracer.core.*;
import infectiontracer.json.FileHandler;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class InfectionTracerApiController {

    private FileHandler filehandler = new FileHandler();
    private InfectionTracer infectionTracer = new InfectionTracer();

    @GetMapping("/infectiontracer/users")
    protected List<User> getAllActiveUsersApi() {
           return filehandler.getUsers();
            
    }

    @GetMapping("/infectiontracer/user/{email}")
    protected User getUserByEmailApi(@PathVariable String email) {
        return infectionTracer.getActiveUser(email);
    }

    @PostMapping("/infectiontracer/users")
    protected boolean newUserApi(@RequestBody User newUser){
             filehandler.insertUser(newUser);
             return true;
    }

  

    


    

}
