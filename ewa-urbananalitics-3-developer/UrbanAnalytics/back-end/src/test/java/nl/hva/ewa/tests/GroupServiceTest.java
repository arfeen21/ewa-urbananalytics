package nl.hva.ewa.tests;

import nl.hva.ewa.empower.group.Group;
import nl.hva.ewa.empower.group.GroupRepository;
import nl.hva.ewa.empower.group.GroupService;
import nl.hva.ewa.empower.requestModels.group.CreateGroup;
import nl.hva.ewa.empower.user.User;
import nl.hva.ewa.empower.user.UserService;
import nl.hva.ewa.empower.user_in_group.UserInGroup;
import nl.hva.ewa.empower.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GroupServiceTest {

    private JwtUtil jwtUtil;
    private MockMvc mockMvc;

    @InjectMocks
    GroupService groupService;

    @Mock
    GroupRepository repo = mock(GroupRepository.class);

    private User user = new User("test@test.com" );
    private User user2 = new User("user2" , "test" , "test2@test.com"  , "password");
    UserInGroup userInGroup = new UserInGroup();
    Set<UserInGroup> users = new HashSet<>();
    public Group group = new Group("group1" , user , users  );

    //Test 9 Niels van Oeffel
    @Test
    void TestEmptyGroupName(){
        CreateGroup createGroup = new CreateGroup("");
        try{
            groupService.createGroup(createGroup ,  jwtUtil.generateToken(user2));
        }catch (Exception e){
            assertEquals("Please provide a group name" , e.toString());
        }
    }
    //Test 10 Niels van Oeffel
    @Test
    void TestShortGroupName(){

        CreateGroup createGroup = new CreateGroup("");

        try{
            groupService.createGroup(createGroup , "");
        }catch (Exception e){
            System.out.println(e);
            assertEquals("The groupname is too short" , e);
        }
    }

}