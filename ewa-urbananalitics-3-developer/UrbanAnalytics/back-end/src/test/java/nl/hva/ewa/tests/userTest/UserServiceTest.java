package nl.hva.ewa.tests.userTest;

import nl.hva.ewa.empower.user.User;
import nl.hva.ewa.empower.user.UserRepository;
import nl.hva.ewa.empower.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@SpringBootTest
public class UserServiceTest {

    private MockMvc mockMvc;
    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @MockBean
    private UserService userService2;

    @Autowired
    UserService userServica = null;


    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(new UserService()).build();

    }

    // made by Arfeen
    @Test
    public void contextLoads() {
        assertNotNull(userServica);
        System.out.println("Application auto-configuration has been succeeded");
    }

    //  Arfeen, test8 using mockito
    @Test
    final void GetUser() {
        User userEntity = new User();
        userEntity.setUserId(1009);
        userEntity.setFirstName("ariboon");
        userEntity.setLastName("straat");
        userEntity.setPassword("74hgd8hvja");

        when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
    }


}
