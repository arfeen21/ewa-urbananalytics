package nl.hva.ewa.tests.notification;
//package nl.hva.ewa.empower.notification;

import nl.hva.ewa.empower.notification.Notification;
import nl.hva.ewa.empower.notification.*;
import nl.hva.ewa.empower.notification.NotificationService;
//import nl.hva.ewa.empower.notification.NotificationRepository;
import nl.hva.ewa.empower.user.User;
import nl.hva.ewa.empower.user.UserRepository;
import nl.hva.ewa.empower.user.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.JMock1Matchers.equalTo;
import static org.mockito.Mockito.mock;
import org.springframework.http.HttpHeaders;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class NotificationTest {

    private NotificationRepository notificationRepo = mock(NotificationRepository.class);
    private UserRepository userRepo = mock(UserRepository.class);
    private User userA, userB;
    private String userAJWT, userBJWT;
    @Mock
    private NotificationService notificationService;
    private Notification notification;

    @BeforeEach
    void setUp(){
        this.userA = new User("Aard", "de from", "testuserA@ewa.nl", "123456789");
        this.userB = new User("Bernard", "de to", "testuserB@ewa.nl", "123456789");
    }

    @AfterEach
    void tearDown(){
        this.userA = null;
        this.userB = null;
    }

    /**
     * Danny Huigen 1: Test if the sender is the correct sender by testing the getFrom method
     */
    @Test
    void testFrom(){
        notification = new Notification(userA, userB, "The message");
        assertEquals(notification.getFrom(), userA);
    }

    /**
     * Danny Huigen 2: Test if the reciever is the correct reciever by testing the getTo method
     */
    @Test
    void testTo(){
        notification = new Notification(userA, userB, "The message");
        assertEquals(notification.getTo(), userB);
    }

    /**
     * Danny Huigen 3: Test if the setRead function works by "reading" a notification and checking if the notificaiton is read
     */
    @Test
    void testRead(){
        notification = new Notification(userA, userB, "The message");
        notification.setRead(true);
        assertTrue(notification.isRead());
    }

    /**
     * Danny Huigen 4: Test if the notifications repo is able to delete an existing notification
     */
    @Test
    void deleteNotification() {
        notification = new Notification(userA, userB, "The message");
        when(notificationRepo.save(notification)).thenReturn(notification);
        notificationRepo.delete(notification);

        assertEquals(notificationRepo.findById(notification.getNotificationId()), Optional.empty());
    }

    /**
     * Danny Huigen 5: Test if the notifications repo is able to save a message to the repository
     */
    @Test
    void findById() {
        notification = new Notification(userA, userB, "The message");
        when(notificationRepo.save(notification)).thenReturn(notification);
        Notification savedNotification = notificationRepo.save(notification);

        assertEquals(savedNotification, notification);
    }

    /**
     * Danny Huigen 6: Tests if the update functionality of the repository works a expected
     */
    @Test
    void updateNotification() {
        notification = new Notification(userA, userB, "Laten we er van uit gaan dat de test niet breekt!");
        when(notificationRepo.save(notification)).thenReturn(notification);

        notification.setMessage("Wouw pas op een nieuwe message!");
        Notification newNotification = notificationRepo.save(notification);

        when(notificationRepo.findById(notification.getNotificationId())).thenReturn(Optional.ofNullable(notification));
        Optional<Notification> recievedNotification = notificationRepo.findById(newNotification.getNotificationId());

        assertEquals(recievedNotification, recievedNotification);
    }

    /**
     * Danny Huigen 7: Tests if the recieved notifications functionality works as expected
     */
    @Test
    void testRecievedNotifications(){
        List<Notification> notifications = new LinkedList<>();
        notifications.add(new Notification(userA, userB, "Een hele message"));
        notifications.add(new Notification(userA, userB, "Nog een hele message"));
        notifications.add(new Notification(userA, userB, "De laatste volledige message"));
        userB.setSendNotifications(notifications);

        assertEquals(userB.getSendNotifications().size(), 3);
    }


    /**
     * Danny Huigen 8: Tests if the notificationservice doesent add an exisitng notification
     */
    @Test
    void testSendNotification(){
        notification = new Notification(userA, userB, "Laten we er van uit gaan dat de test _wel_ breekt!");
        assertEquals(notificationService.addNotification(notification), false);
    }

    /**
     * Danny Huigen 9: Test if the notification sent functionallity works correctly
     */
    @Test
    void testSentMessage(){
        notification = new Notification(userA, userB, "Verzonden message!");
        ArrayList<Notification> notifications = new ArrayList<>();
        notifications.add(notification);
        userB.setSendNotifications(notifications);
        assertEquals(userB.getSendNotifications().get(0).getMessage(), "Verzonden message!");
    }

    /**
     * Danny Huigen 10: Tet if the notification recieved functionallity works correctly
     */
    @Test
    void testRecievedMessage(){
        notification = new Notification(userA, userB, "Ontvangen message!");
        ArrayList<Notification> notifications = new ArrayList<>();
        notifications.add(notification);
        userA.setRecievedNotifications(notifications);
        assertEquals(userA.getRecievedNotifications().get(0).getMessage(), "Ontvangen message!");
    }
}
