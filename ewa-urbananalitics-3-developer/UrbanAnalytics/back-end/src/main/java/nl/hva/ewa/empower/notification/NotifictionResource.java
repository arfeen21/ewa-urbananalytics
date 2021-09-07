package nl.hva.ewa.empower.notification;

import nl.hva.ewa.empower.user.User;
import nl.hva.ewa.empower.user.UserService;
import nl.hva.ewa.empower.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotifictionResource {
    @Autowired
    private NotificationService noteService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;


    /**
     * Gets all the notifications
     * @return all the notifications
     */
    @GetMapping(path = "notification/")
    public List<Notification> getAllNotifications() {
        return noteService.getAllNotifications();
    }

    /**
     * Send a notification to a user
     *
     * @param jwtToken The personal login-token
     * @param notification the notification object
     * @return status
     */
    @PostMapping(path = "user/notifications/send")
    public ResponseEntity<String> sendNotification(@RequestHeader("Authentication") String jwtToken, @RequestBody Notification notification){
        User from = userService.getSingleUser(jwtUtil.extractEmail(jwtToken));
        User to = userService.getSingleUser(notification.getTo().getEmail());
        if (from.getEmail().equals(to.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sender is same as receiver");
        }
        notification.setFrom(from);
        notification.setTo(to);
        noteService.addNotification(notification);
        return ResponseEntity.status(HttpStatus.OK).body("Notficication created: \nfrom:" +notification.getFrom().getFirstName() + "\nto:" + notification.getTo().getFirstName() + "\nmessage: " + notification.getMessage() );
    }

    /**
     * Sets all the unread notifications of the logged in user on read
     * @param jwtToken the logged in user
     * @return statuscode
     */
    @PostMapping(path = "user/notifications/read")
    public ResponseEntity<String> readNotifications(@RequestHeader("Authentication") String jwtToken) {
        User user = userService.getSingleUser(jwtUtil.extractEmail(jwtToken));
        userService.readAllNotifications(user);
        return ResponseEntity.status(HttpStatus.OK).body("All recieved notifications set to read");
    }

    /**
     * Get all recieved notifications
     * @param jwtToken The personal login-token
     * @return all recieved notifications
     */
    @GetMapping(path = "user/notifications/recieved")
    public List<Notification> getAllRecievedNotifications(@RequestHeader("Authentication") String jwtToken){
        User currentUser = userService.getSingleUser(jwtUtil.extractEmail(jwtToken));

        return currentUser.getRecievedNotifications();
    }

    /**
     * Get all send notifications
     * @param jwtToken The personal login-token
     * @return all sned notifications
     */
    @GetMapping(path = "user/notifications/sended")
    public List<Notification> getSendNotifications(@RequestHeader("Authentication") String jwtToken){
        User currentUser = userService.getSingleUser(jwtUtil.extractEmail(jwtToken));

        return currentUser.getSendNotifications();
    }

}
