package nl.hva.ewa.tests.userTest;

import nl.hva.ewa.empower.user.User;
import nl.hva.ewa.empower.user.UserRepository;
import nl.hva.ewa.empower.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRepositoryTest {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    private HttpHeaders headers;
    private HttpHeaders invalidHeaders;
    private User user;
    private User updatedUser;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup(){
        user = new User("Evan", "Sahit", "evantest@mail.com", "qwerty");
        updatedUser = new User("Updated", "User", "update@mail.com", "asdfgh");

        jwtUtil.generateToken(user);

        headers = new HttpHeaders();
        headers.add("Authentication", jwtUtil.generateToken(user));
    }


    // Arfeen,test1
    @Test
    public void testGetUser() {
        User user = new User(21);
        userRepository.save(user);
        User user1 = userRepository.findByEmail("arfeen17@hotmail.com");
        assertNotNull(user);
        assertEquals(user1.getFirstName(), user.getFirstName());
        assertEquals(user1.getLastName(), user.getLastName());
        assertEquals(user1.getEmail(), "arfeen17@hotmail.com");
    }

    // Arfeen.test2
    @Test
    public void deleteUserTest() {
        User user = new User();
        User use = userRepository.save(user);
        userRepository.deleteById(use.getUserId());

    }

    //Arfeen.test3
    @Test
    public void findAllUsers() {
        User user = new User(21);
        userRepository.save(user);
        assertNotNull(userRepository.findAll());
    }

    //A.test4
    @Test
    public void updateUser() {
        User user = new User("arfeen14@hotmail.com");
        User use = userRepository.save(user);

        Optional<User> optional = userRepository.findById(use.getUserId());
        User user1 = optional.get();
        user1.setFirstName("Johan");
        user1.setLastName("Zimmerman");
        userRepository.save(user1);
    }


    //Arfeen.test6
    @Test
    public void testCRUD() {

        User user = new User("arfeen", "sid", "arfeen@hotmail.com", "nope");
        User savedUser = userRepository.save(user);
        //check id generation
        assertTrue(savedUser.getUserId() > 0);

        assertEquals("arfeen", savedUser.getFirstName());
        assertEquals("sid", savedUser.getLastName());
    }

    //Arfeen.test7, forced error test
    @Test
    public void errorTest() throws AssertionError {
        User user = new User("arfeen", "sid", "arfeen@hotmail.com", "nope");
        User savedUser = userRepository.save(user);
        assertThrows(AssertionError.class, () -> {
            assertEquals("bob", savedUser.getFirstName());
            assertEquals("henk", savedUser.getLastName());
        });
    }

    //Test 5, Evan Sahit
    @Test
    @DisplayName("Get all users using the endpoint")
    void TestGetAllUsers(){
        HttpEntity entity = new HttpEntity<>(headers);
        ResponseEntity response = restTemplate.exchange("http://localhost:" + port + "/user/user/all", HttpMethod.GET, entity, new ParameterizedTypeReference<Set<User>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    //Test 6, Evan Sahit
    @Test
    @DisplayName("Find a user by id using the endpoint")
    void TestFindUserByID() {
        int id = 15;

        ResponseEntity<User> response = this.restTemplate.exchange("http://localhost:" + port + "/user/user/" + id, HttpMethod.GET, new HttpEntity<>(headers), User.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    //Test 7, Evan Sahit
    @Test
    @DisplayName("Update a user using the endpoint")
    void TestUpdateUser(){
        ResponseEntity<User> response = this.restTemplate.exchange("http://localhost:" + port + "/user/update", HttpMethod.POST, new HttpEntity<>(updatedUser, headers), User.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    //Test 8, Evan Sahit
    @Test
    @DisplayName("Get a user using their jwtToken using the endpoint")
    void TestGetUser(){
        ResponseEntity<User> response = this.restTemplate.exchange("http://localhost:" + port + "/user/user", HttpMethod.GET, new HttpEntity<>(headers), User.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    //Test 9, Evan Sahit
    @Test
    @DisplayName("Attempt to get a user without valid headers")
    void TestGetAUserWithInvalidHeaders(){
        int id = 12;

        ResponseEntity<User> badReponse = this.restTemplate.exchange("http://localhost:" + port + "/user/user" + id, HttpMethod.GET, new HttpEntity<>(invalidHeaders), User.class);

        assertEquals(HttpStatus.BAD_REQUEST, badReponse.getStatusCode());
    }

    //Test 10, Evan Sahit
    @Test
    @DisplayName("Change a user's first name")
    void TestChangeFirstName(){
        assertEquals(user.getFirstName(), "Evan");

        user.setFirstName("Mark");

        assertEquals(user.getFirstName(), "Mark");
    }


}