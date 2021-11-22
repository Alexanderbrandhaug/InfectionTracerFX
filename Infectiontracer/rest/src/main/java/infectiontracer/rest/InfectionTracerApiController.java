package infectiontracer.rest;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import infectiontracer.core.*;
import infectiontracer.json.FileHandler;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;



@RestController
public class InfectionTracerApiController {

    private final FileHandler filehandler = new FileHandler();
    private final InfectionTracer infectionTracer = new InfectionTracer();


    @GetMapping("/infectiontracer/users")
    protected List<User> getAllActiveUsersApi() {
           return filehandler.getUsers();
            
    }

    @GetMapping("/infectiontracer/user/{email}")
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User does not exist")
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

    
    @GetMapping("/infectiontracer/user/{email}/closecontacts")
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User does not exist")
     protected List<User> getUsersCloseContactsApi(@PathVariable String email){
         return infectionTracer.getUsersCloseContacts(email);
     }

     @PutMapping("infectiontracer/user/{email}/healthstatus/makesick")
     protected boolean setHealthStatusSickApi(@PathVariable String email){
         infectionTracer.makeUserInfected(email);
         return true;
     }

     @PutMapping("infectiontracer/user/{email}/healthstatus/makehealthy")
     protected boolean setHealthStatusHealthyApi(@PathVariable String email){
         infectionTracer.makeUserHealthy(email);
         return true;
     }                                                     

    @PostMapping("infectiontracer/user/{email}/closecontacts")
    protected boolean addCloseContactApi(@PathVariable String email, @RequestBody User newCloseContact){
        infectionTracer.addCloseContact(email, newCloseContact.getEmail());
        return true;
    }

    @PostMapping("infectiontracer/user/{email}/closecontacts/removecontact")
    protected boolean deleteCloseContactApi(@PathVariable String email, @RequestBody User newCloseContact){
        infectionTracer.removeCloseContact(email, newCloseContact.getEmail());
        return true;
    }

    
    @PutMapping("infectiontracer/user/{email}")
    protected boolean updatePasswordApi(@PathVariable String email, @RequestBody User currentUser){
       if(infectionTracer.changePw(currentUser.getEmail())){
        return true;
       }
        return false;
        
    }
  

    


    

}
