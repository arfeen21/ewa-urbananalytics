package nl.hva.ewa.tests;

import nl.hva.ewa.empower.notification.Notification;
import nl.hva.ewa.empower.user.User;
import nl.hva.ewa.empower.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    static User user = new User("Joe", "Who", "YougetTheJoke@gmail.com", "yepMadeAjokeHere69");

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private static UserService userService = new UserService();


    @Test
    void getAllUsers() {

    }

    @Test
    void getSingleUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void testGetSingleUser() {
    }

    @Test
    void registerUser() {
    }

    @Test
    void loginUser() {

    }

    @Test
    void getAllGroups() {
    }

    @Test
    void getAGroups() {
    }

    @Test
    void getUser() {
    }

    @Test
    void removeUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void readAllNotifications() {

    }

}