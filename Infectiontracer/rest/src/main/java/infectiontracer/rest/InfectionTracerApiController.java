package infectiontracer.rest;

import infectiontracer.core.InfectionTracer;
import infectiontracer.core.User;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;



/**
 * Controller that controls calls to the REST-API, mainly controlled by the URL's sent in the
 * controller classes.
 */
@RestController
public class InfectionTracerApiController {

  private final InfectionTracer infectionTracer = new InfectionTracer();

  /**
   * Method that returns all users stored in the application.
   *
   * @return All users
   */
  @GetMapping("/infectiontracer/users")
  protected List<User> getAllActiveUsersApi() {
    return infectionTracer.getUsers();
  }

  /**
   * Method to retrieve a specific user.
   *
   * @param email Email of the user that is retrieved.
   * @return Http status code and user object.
   */
  @GetMapping(path = "/infectiontracer/user/{email}" /*,
            produces = MediaType.APPLICATION_JSON_VALUE*/)
  // @ResponseStatus(code = HttpStatus.OK, reason = "OK")
  public ResponseEntity<User> getUserByEmailApi(@PathVariable String email) {
    User user = infectionTracer.getActiveUser(email);
    if (user == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.ok(user);
  }

  /**
   * Method to add a new user to the application.
   *
   * @param newUser User to be added.
   * @return True if user was successfully added.
   */
  @PostMapping(
      path = "/infectiontracer/users",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  protected boolean newUserApi(@RequestBody User newUser) {
    infectionTracer.addUser(newUser);
    return true;
  }

  /**
   * Method to retrieve a user's close contacts.
   *
   * @param email Email to the user with close contacts that is to be returned.
   * @return List of close contacts belonging to user.
   */
  @GetMapping("/infectiontracer/user/{email}/closecontacts")
  protected List<User> getUsersCloseContactsApi(@PathVariable String email) {
    return infectionTracer.getUsersCloseContacts(email);
  }

  /**
   * Method to change a user's health status to 'Infected'.
   *
   * @param email Email to user that has health status changed.
   * @param infectedUser Infected User object.
   * @return True if change is successful.
   */
  @PutMapping("infectiontracer/user/{email}/healthstatus/makesick")
  protected boolean setHealthStatusSickApi(
      @PathVariable String email, @RequestBody User infectedUser) {
    infectionTracer.makeUserInfected(email);
    infectionTracer.sendEmailToCloseContacts(infectedUser);
    return true;
  }

  /**
   * Method that changes a user's health status to 'Covid-19 negative'.
   *
   * @param email Email to user that has health status changed.
   * @return True if change is successful.
   */
  @PutMapping("infectiontracer/user/{email}/healthstatus/makehealthy")
  protected boolean setHealthStatusHealthyApi(@PathVariable String email) {
    infectionTracer.makeUserHealthy(email);
    return true;
  }

  /**
   * Method that adds a close contact to a user.
   *
   * @param email Email to user that is adding close contact.
   * @param newCloseContact User that is being added as a close contact.
   * @return True if change is successful.
   */
  @PostMapping("infectiontracer/user/{email}/closecontacts")
  protected boolean addCloseContactApi(
      @PathVariable String email, @RequestBody User newCloseContact) {
    infectionTracer.addCloseContact(email, newCloseContact.getEmail());
    return true;
  }

  /**
   * Method to delete a close contact of a user.
   *
   * @param email Email of user that is deleting a close contact.
   * @param oldCloseContact User that is to be removed as close contact.
   * @return True if change is successful.
   */
  @PostMapping("infectiontracer/user/{email}/closecontacts/removecontact")
  protected boolean deleteCloseContactApi(
      @PathVariable String email, @RequestBody User oldCloseContact) {
    infectionTracer.removeCloseContact(email, oldCloseContact.getEmail());
    return true;
  }

  /**
   * Method to update a user's password.
   *
   * @param email Email to user that is having password changed.
   * @param currentUser User that is having password changed.
   * @return True if change is successful.
   */
  @PutMapping("infectiontracer/user/{email}/updatepw")
  protected boolean updatePasswordApi(@PathVariable String email, @RequestBody User user) {
    return infectionTracer.changePw(user.getEmail());
  }

  @PutMapping("infectiontracer/user/{email}")
  protected boolean updateUser(@PathVariable String email, @RequestBody User currentUser){
    infectionTracer.editUser(currentUser);
    return true;
  }


}
