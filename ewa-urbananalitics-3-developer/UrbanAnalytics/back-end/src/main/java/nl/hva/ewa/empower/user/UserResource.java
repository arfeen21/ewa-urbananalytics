package nl.hva.ewa.empower.user;

import com.sun.net.httpserver.Authenticator;
import io.jsonwebtoken.Header;
import nl.hva.ewa.empower.group.Group;
import nl.hva.ewa.empower.user_in_group.UserInGroup;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserResource {
    @Autowired
    private UserService userService;

    // Global user information end points
    @GetMapping(path = "user/user/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "user/user/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getSingleUser(id);
    }

    @PostMapping(path = "user/update")
    public User updateUser(@RequestHeader("Authentication") String jwtToken, @RequestBody User update){
        System.out.println(userService.getUser(jwtToken));
        System.out.println(update);
        return userService.updateUser(userService.getUser(jwtToken), update);
    }

    @GetMapping(path = "user/user")
    public User getUser(@RequestHeader("Authentication") String jwtToken) {
        return userService.getUser(jwtToken);
    }

    //Auth end points
    @PostMapping(path = "auth/register")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping(path = "auth/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        return userService.loginUser(user);
    }

    // Group end points
    @GetMapping(path = "user/group/all")
    public ResponseEntity<?> getAllGroups(@RequestHeader("Authentication") String jwtToken) {
        return userService.getAllGroups(jwtToken);
    }

    @GetMapping(path = "user/group/{id}")
    public Group getAGroups(@RequestHeader("Authentication") String jwtToken, @PathVariable int id) {
        return userService.getAGroups(jwtToken, id);
    }

    // verwijderd een user voledig van de database(DANGER)
    @DeleteMapping(path = "user/user/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }



//    //dit hoort een gebruiker uit de group de deleten zonder hem uit de database te halen , door er gewoon GETMAPPING voor te doen
//    @GetMapping(path = "group/user/{id}")
//    public void removeUser(@PathVariable int id) {
//        userService.deleteUser(id);
//    }


}
