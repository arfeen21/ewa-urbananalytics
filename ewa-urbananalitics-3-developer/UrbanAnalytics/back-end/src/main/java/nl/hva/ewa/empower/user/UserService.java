package nl.hva.ewa.empower.user;

import nl.hva.ewa.empower.group.Group;
import nl.hva.ewa.empower.notification.Notification;
import nl.hva.ewa.empower.user_in_group.UserInGroup;
import nl.hva.ewa.empower.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Constructor
     */
    public UserService() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Get all users
     *
     * @return list of all users
     */
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        userRepository.findAll().forEach(userList::add);

        return userList;
    }

    /**
     * Get single user object
     *
     * @param id id of user
     * @return user if found and null if not found
     */
    public User getSingleUser(int id) {
        User user = userRepository.findById(id).orElse(null);
//        System.out.println(user);
        return user;
    }

    /**
     * Updates an exsisting user
     * @param oldUser the old user
     * @param newUser the new user
     * @return the new user
     */
    public User updateUser(User oldUser, User newUser){
        newUser.setUserId(oldUser.getUserId());
        newUser.setPassword(oldUser.getPassword());
        newUser.setProfileImage(oldUser.getProfileImage());

        newUser.setSendNotifications(oldUser.getSendNotifications());
        userRepository.save(newUser);
        return newUser;
    }

    /**
     * Get single user object
     *
     * @param email email of user
     * @return user if found and null if not found
     */
    public User getSingleUser(String email) {
        User user = userRepository.findByEmail(email);
//        System.out.println(user);
        return user;
    }

    // Register a user
    public ResponseEntity<?> registerUser(User user) {
        // Firstname checks
        if (user.getFirstName() == null) {
            return ResponseEntity.badRequest().body("No firstname was provided.");
        } else if (!user.getFirstName().matches("^.{4,}$")) {
            return ResponseEntity.badRequest().body("Please enter a valid firstname with minimum length of 4.");
        }

        // Lastname checks
        if (user.getLastName() == null) {
            return ResponseEntity.badRequest().body("No lastname was provided.");
        } else if (!user.getLastName().matches("^.{4,}$")) {
            return ResponseEntity.badRequest().body("Please enter a valid lastname with minimum length of 4.");
        }

        // Email checks
        if (user.getEmail() == null) {
            return ResponseEntity.badRequest().body("No email was provided.");
        } else if (!user.getEmail().matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")) {
            return ResponseEntity.badRequest().body("Please enter a valid email address.");
        }

        // Password checks
        if (user.getPassword() == null) {
            return ResponseEntity.badRequest().body("No password was provided.");
        } else if (!user.getPassword().matches("^.{6,}$")) {
            return ResponseEntity.badRequest().body("Please enter a valid password with a minimum length of 6.");
        }

        // Check if email is in use
        if (this.getAllUsers().stream().filter(u -> u.getEmail().equals(user.getEmail())).collect(Collectors.toSet()).size() > 0) {
            return ResponseEntity.badRequest().body("This email address is already in use.");
        }

        User newUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        userRepository.save(newUser);

        return ResponseEntity.status(200).body("Successfully registered!");
    }

    // Login a user
    public ResponseEntity<?> loginUser(User aUser) {
        // Email check
        if (aUser.getEmail() == null) {
            return ResponseEntity.badRequest().body("Please provide a email.");
        } else if (!aUser.getEmail().matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")) {
            return ResponseEntity.badRequest().body("Please provide a valid email.");
        }

        //Check if user can be found
        User user = userRepository.findByEmail(aUser.getEmail());
        if (user == null) {
            return ResponseEntity.badRequest().body("Email was not found.");
        }

        // Password check
        if (aUser.getPassword() == null) {
            return ResponseEntity.badRequest().body("Please provide a password.");
        } else if (!bCryptPasswordEncoder.matches(aUser.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("User details where not correct");
        }

        return ResponseEntity.status(200).header(HttpHeaders.AUTHORIZATION, jwtUtil.generateToken(user)).body("Successfully logged in!");
    }

    public ResponseEntity<?> getAllGroups(String jwtToken) {
        User user = getSingleUser(jwtUtil.extractEmail(jwtToken));

        if (jwtUtil.validateToken(jwtToken, user)) {
            return ResponseEntity.ok().body(user.getGroups());
        } else {
            return ResponseEntity.badRequest().body("jwtToken invalid");
        }
    }

    public Group getAGroups(String jwtToken, int id) {
        User user = getSingleUser(jwtUtil.extractEmail(jwtToken));

        if (jwtUtil.validateToken(jwtToken, user)) {
            return user.getGroups().stream().filter(group -> group.getGroupId() == id).findFirst().orElse(null);
        }

        return null;
    }

    public User getUser(String jwtToken) {
        User user = getSingleUser(jwtUtil.extractEmail(jwtToken));

        if (jwtUtil.validateToken(jwtToken, user)) {
            return user;
        }

        return null;
    }

    public void removeUser(String email) {
        userRepository.delete(getSingleUser(email));
    }

    public void deleteUser(int id) {
        userRepository.delete(getSingleUser(id));
    }
    public List<Notification> readAllNotifications(User user){
        for (Notification n : user.getRecievedNotifications()){
            n.setRead(true);
        }
        userRepository.save(user);
        return user.getRecievedNotifications();
    }

}
