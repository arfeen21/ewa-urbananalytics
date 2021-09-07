package nl.hva.ewa.empower.notification;

import nl.hva.ewa.empower.user.User;
import nl.hva.ewa.empower.user.UserRepository;
import nl.hva.ewa.empower.user.UserResource;
import nl.hva.ewa.empower.user.UserService;
import org.hibernate.Hibernate;
import org.junit.After;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import nl.hva.ewa.empower.notification.NotifictionResource;
import org.springframework.test.context.TestExecutionListeners;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestExecutionListeners({ServiceTestExecutionListener.class, TransactionalTestExecutionListener.class})
public class NotificationTestOld {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public UserService userService;

    @Autowired
    NotifictionResource notifictionResource;

    @Autowired
    public NotificationRepository notificationRepository;

    @Autowired
    public NotificationService notificationService;

    User a;
    String userAJWT;

    User b;
    String userBJWT;

    @BeforeEach
    public void before() {
        this.a = new User("Aard", "de from", "testuserA@ewa.nl", "123456789");
        this.b = new User("Bernard", "de to", "testuserB@ewa.nl", "123456789");

        userRepository.save(this.a);
        userRepository.save(this.b);
        userAJWT = userService.loginUser(this.a).getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
        userBJWT= userService.loginUser(this.b).getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
    }

    @AfterEach
    public void after(){
        userRepository.delete(a);
        userRepository.delete(b);
    }

    /**
     * Notification-test 1
     * Tests if a notification can be send from user a to user b
     */
    @Transactional
    @Test
    public void testSendNewNotification(){
        String message = "A test notification";
        Notification n = new Notification(
           this.a,
           this.b,
           message
        );
        notifictionResource.sendNotification(userAJWT, n);

        assertEquals(n.getMessage(), message);
    }

    /**
     * Notification-test 2
     * Test if a notification that has been send from a to b is received at user b
     */
    @Test
    public final void testRecieveNewNotification(){
        String message = "A test notification 2";
        Notification n = new Notification(
                this.a,
                this.b,
                message
        );
        notifictionResource.sendNotification(userAJWT, n);

        assertEquals(n.getMessage(), message);
    }

    /**
     * Notification-test 3
     * Test if notification goes on read when reading the newly received notification
     */
    @Test
    public final void testReadNewNotification(){
        String message = "A test notification";
        Notification n = new Notification(
                this.a,
                this.b,
                message
        );
        notifictionResource.sendNotification(userAJWT, n);
        n.setRead(true);

        assertTrue(n.isRead());
    }

    /**
     * Notification-test 4
     * Test if all newly recieved notifications go on read when trying to readall()
     */
    @Test
    public final void testReadAllNewNotifications(){
        ArrayList<Notification> notifications = new ArrayList<Notification>();
        notifications.add(new Notification(
                this.a,
                this.b,
                "Not 1"
        ));
        notifications.add(new Notification(
                this.a,
                this.b,
                "Not 2"
        ));
        for (Notification n : notifications){
            n.setRead(true);
        }

        boolean areAllRead = true;
        for (Notification n : notifications)
            if (!n.isRead()) {
                areAllRead = false;
            }
        assertTrue(areAllRead);
    }

    /**
     * Notification-test 6
     * Tests if a logged in user is able to receive all their received notifications
     */
    @Test
    @Transactional
    public void testGetAllReceivedNotifications(){
        String message = "A test notification";
        Notification n = new Notification(
                this.a,
                this.b,
                message
        );
        notifictionResource.sendNotification(userAJWT, n);
        List<Notification> nots = notifictionResource.getAllRecievedNotifications(userBJWT);
        assertNull(nots);
    }

    /**
     * Notification-test 7
     * Tests if a logged in user is able to recieve all their send notifications
     */
    @Test
    public final void testGetAllSendNotifications(){

    }

    /**
     * Notification-test 8
     * Tests if notification service throws error when trying to send a notification from person A to person A (Same sender as reciever)
     */
    @Test
    public final void testSendFromAToA(){

    }

    /**
     * Notification-test 9
     * Tests if notification service throws error when trying to send notification from person A to non-existing user
     */
    @Test
    public final void testSendFromAtoNull(){

    }

    /**
     * Notification-test 10
     * Tests if notification can be send when the sender is not logged in
     */
    @Test
    public final void testSendNotificationLoggedOut(){

    }



}
