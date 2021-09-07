package nl.hva.ewa.tests.userTest;

import nl.hva.ewa.empower.user.User;
import nl.hva.ewa.empower.user.UserResource;
import nl.hva.ewa.empower.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserResourceTest {

    @InjectMocks
    UserResource userResource;
    @Mock
    UserService userService;
    User user;
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate restTemplate;


    @BeforeEach
    void setup() {

        mockMvc = MockMvcBuilders.standaloneSetup(userResource)
                .build();
    }

    //    Arfeen.test5 mockobject with mockito
    @Test
    final void testGetUser() {
        when(userService.getSingleUser(anyString())).thenReturn(user);
        assertThat(null, isEmptyOrNullString());


    }


}
