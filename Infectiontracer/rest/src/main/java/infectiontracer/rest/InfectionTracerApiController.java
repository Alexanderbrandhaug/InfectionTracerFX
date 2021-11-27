package infectiontracer.rest;

import infectiontracer.core.InfectionTracer;
import infectiontracer.core.User;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.DeleteMapping;

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
   * @return Http status code and all users in Json-file.
   */
  @GetMapping("/infectiontracer/users")
  public ResponseEntity<List<User>> getAllActiveUsersApi() {
    try {
      List<User> userlist = infectionTracer.getUsers();
      return ResponseEntity.ok(userlist);
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
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
    try {
      User user = infectionTracer.getActiveUser(email);
      return ResponseEntity.ok(user);
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
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
  public ResponseEntity<String> newUserApi(@RequestBody User newUser) {
    try {
      infectionTracer.addUser(newUser);
      return ResponseEntity.ok(null);
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  /**
   * Method to retrieve a user's close contacts.
   *
   * @param email Email to the user with close contacts that is to be returned.
   * @return List of close contacts belonging to user.
   */
  @GetMapping("/infectiontracer/user/{email}/closecontacts")
  public ResponseEntity<List<User>> getUsersCloseContactsApi(@PathVariable String email) {
    try {
      List<User> closeContacts = infectionTracer.getUsersCloseContacts(email);
      return ResponseEntity.ok(closeContacts);
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  /**
   * Method to change a user's health status to 'Infected'.
   *
   * @param email Email to user that has health status changed.
   * @param infectedUser Infected User object.
   * @return True if change is successful.
   */
  @PutMapping("infectiontracer/user/{email}/healthstatus/makesick")
  public ResponseEntity<String> setHealthStatusSickApi(
      @PathVariable String email, @RequestBody User infectedUser) {
    try {
      infectionTracer.makeUserInfected(email);
      infectionTracer.sendEmailToCloseContacts(infectedUser);
      return ResponseEntity.ok(null);
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  /**
   * Method that changes a user's health status to 'Covid-19 negative'.
   *
   * @param email Email to user that has health status changed.
   * @return True if change is successful.
   */
  @PutMapping("infectiontracer/user/{email}/healthstatus/makehealthy")
  public ResponseEntity<String> setHealthStatusHealthyApi(@PathVariable String email) {
    try {
      infectionTracer.makeUserHealthy(email);
      return ResponseEntity.ok(null);
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  /**
   * Method that adds a close contact to a user.
   *
   * @param email Email to user that is adding close contact.
   * @param newCloseContact User that is being added as a close contact.
   * @return True if change is successful.
   */
  @PostMapping("infectiontracer/user/{email}/closecontacts")
  public ResponseEntity<String> addCloseContactApi(
      @PathVariable String email, @RequestBody User newCloseContact) {
    try {
      infectionTracer.addCloseContact(email, newCloseContact.getEmail());
      return ResponseEntity.ok(null);
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  /**
   * Method to delete a close contact of a user.
   *
   * @param email Email of user that is deleting a close contact.
   * @param oldCloseContact User that is to be removed as close contact.
   * @return True if change is successful.
   */
  @PostMapping("infectiontracer/user/{email}/closecontacts/removecontact")
  public ResponseEntity<String> deleteCloseContactApi(
      @PathVariable String email, @RequestBody User oldCloseContact) {
    try {
      infectionTracer.removeCloseContact(email, oldCloseContact.getEmail());
      return ResponseEntity.ok(null);
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  /**
   * Method to update a user's password.
   *
   * @param email Email to user that is having password changed.
   * @param currentUser User that is having password changed.
   * @return True if change is successful.
   */
  @PutMapping("infectiontracer/user/{email}/updatepw")
  public ResponseEntity<String> updatePasswordApi(
      @PathVariable String email, @RequestBody User currentUser) {
    try {
      infectionTracer.changePw(currentUser.getEmail());
      return ResponseEntity.ok(null);
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @PutMapping("/infectiontracer/user/{email}")
  public ResponseEntity<String> updateUser(@PathVariable String email, @RequestBody User currentUser){
    try {
      infectionTracer.editUser(currentUser);
      return ResponseEntity.ok(null);
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @DeleteMapping(value = "/infectiontracer/user/{email}")
    public ResponseEntity<String> deletePost(@PathVariable String email) {
      try {
        infectionTracer.deleteUser(email);
        return ResponseEntity.ok(null);
      } catch (IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
      } catch (IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
      }
    }
  }

