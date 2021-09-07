package nl.hva.ewa.tests;

import nl.hva.ewa.empower.user.User;
import nl.hva.ewa.empower.user.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration()
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RegisterTest {
    static User user = new User("Jaap", "Stoom", "jaapstoom@gmail.com", "stoomboot43");

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private static UserService userService = new UserService();

    @BeforeAll
    public static void before() {
    }

//    @Test
//    public void test() {
//        User newUser = new User("test", "test", "test@gmail.com", "123456");
//
//        ResponseEntity<User> creationResult = restTemplate.postForEntity("auth/register", newUser, User.class);
//
//        assertEquals(creationResult.getStatusCode(), HttpStatus.CREATED);
//        assertNotNull(creationResult.getBody().getUserId());
//
//        ResponseEntity<User> queryResult = this.restTemplate.getForEntity("user/user/" + creationResult.getBody().getUserId(), User.class);
//        assertEquals(queryResult.getStatusCode(), HttpStatus.OK);
//        // assertEquals(queryResult.getBody().getEmail(), creationResult.getBody().getEmail());
//    }


    /**
     * Richard Test 1
     */
    @Test
     void registerWork() {
        userService.registerUser(user);
        User newUser = userService.getSingleUser("jaapstoom@gmail.com");
        assertNotNull(newUser);
        assertEquals(user, newUser);
    }


    /**
     * Richard Test 2
     */
    @Test
    public void firstNameValidation() {
        User noFName = new User("", "test", "test@test.nl", "123456");
        ResponseEntity<User> noNameCreation = this.restTemplate.postForEntity("auth/register", noFName, User.class);

        assertEquals(noNameCreation, ResponseEntity.badRequest());
    }

    /**
     * Richard Test 3
     */
    @Test
    public void lastNameValidation() {
        User noFName = new User("test", "", "test@test.nl", "123456");
        ResponseEntity<User> noNameCreation = this.restTemplate.postForEntity("auth/register", noFName, User.class);

        assertEquals(noNameCreation, ResponseEntity.badRequest());
    }

    /**
     * Richard Test 4
     */
    @Test
    public void emailValidation() {
        User noATEmail = new User("test", "test", "testtest.nl", "123456");
        ResponseEntity<User> noATCreation = this.restTemplate.postForEntity("auth/register", noATEmail, User.class);

        assertEquals(noATCreation, ResponseEntity.badRequest());
    }

    /**
     * Richard Test 5
     */
    @Test
    public void passwordValidation() {
        User shortPassword = new User("test", "test", "test@test.nl", "123");
        ResponseEntity<User> shortPasswordCreation = this.restTemplate.postForEntity("auth/register", shortPassword, User.class);

        assertEquals(shortPasswordCreation, ResponseEntity.badRequest());
    }

    /**
     * Richard Test 6
     */
    @Test
    public void usableEmailValidation() {
        User noUsableEmail = new User("test", "test", "richard@gmail.com", "123456");
        ResponseEntity<User> usableEmailCreation = this.restTemplate.postForEntity("auth/register", noUsableEmail, User.class);

        assertEquals(usableEmailCreation, ResponseEntity.badRequest());
    }

    @AfterAll
    public static void after() {
        userService.removeUser(user.getEmail());
    }
}
