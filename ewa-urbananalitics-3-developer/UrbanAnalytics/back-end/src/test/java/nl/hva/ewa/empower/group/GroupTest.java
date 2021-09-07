package nl.hva.ewa.empower.group;

import nl.hva.ewa.empower.dataset.Dataset;
import nl.hva.ewa.empower.dataset_sharedWith_group.DatasetSharedWithGroup;
import nl.hva.ewa.empower.requestModels.group.CreateGroup;
import nl.hva.ewa.empower.user.User;
import nl.hva.ewa.empower.user.UserService;
import nl.hva.ewa.empower.user_in_group.UserInGroup;
import nl.hva.ewa.empower.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;



@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GroupTest {
    private JwtUtil jwtUtil;
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders headers;

    @InjectMocks
    GroupService groupService;

    @Mock
    GroupRepository repo = mock(GroupRepository.class);


    private User user;
    private User user2;
    private UserInGroup userInGroup;
    private Set<UserInGroup> users;
    private Group group;
    private UserInGroup userInGroup2;
    private CreateGroup createGroup;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new UserService()).build();

        user = new User("test@test.com" );
        user2 = new User("user2" , "test" , "test2@test.com"  , "password");
        userInGroup = new UserInGroup();
        users = new HashSet<>();

        group = new Group("group1" , user , users);

        userInGroup2 = new UserInGroup(user2 , group);

        createGroup = new CreateGroup("");

        headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authentication", jwtUtil.generateToken(user2));

    }
    //Test1 Niels van Oeffel
    @Test
    void TestAddGroupMembers() {
        userInGroup.setUser(user2);
        users.add(userInGroup);
        group.setGroupMembers(users);
        //check if the user we added is really in the group
        assertEquals("user2" , group.getGroupMembers().get(0).getFirstName());
    }

    //Test2 Niels van Oeffel
    @Test
    void TestClassIsGroup() {
        assertEquals("nl.hva.ewa.empower.group.Group" , group.getClass().getName() );
    }

    //Test3 Niels van Oeffel
    @Test
    void TestCreateGroup() {
        assertEquals(group.getName() , "group1");
    }

    //Test4 Niels van Oeffel
    @Test
    void TestUserInGroup(){
        //confirm if test@test has made the group
        assertEquals( "test@test.com" , group.getCreatedBy().getEmail() );
    }

    //Test5 Niels van Oeffel
    @Test
    void TestChangeGroupName() {

        String newGroupName = "new groupname";
        //delete the user we just added
        group.setName(newGroupName);
        assertEquals(newGroupName , group.getName() );

    }

    //Test6 Niels van Oeffel
    @Test
    void TestFindGroup(){
        Group groupEntity = new Group();
        groupEntity.setName("test");
        repo.save(groupEntity);
    }

    //Test7 Niels van Oeffel
    @Test
    void TestAddProfilePicture() {

        String newGroupName = "new groupname";
        //delete the user we just added
        group.setName(newGroupName);
        assertEquals(newGroupName , group.getName() );

    }

    //Test8 Niels van Oeffel
    @Test
    void GetAllGroupMembers(){
        Group group = new Group("groep");
        repo.save(group);
        assertNotNull(repo.findAll());
    }

    // Test 1, Evan Sahit
    @Test
    @DisplayName("Get all groups using the endpoint")
    void TestGetAllGroups(){
        ResponseEntity response = this.restTemplate.exchange("http://localhost:" + port + "group/member/all",
                HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<Set<Group>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    // Test 2, Evan Sahit
    @Test
    @DisplayName("Add a group member using the endpoint")
    void TestAddAGroup(){
        ResponseEntity response = this.restTemplate.exchange("http://localhost:" + port + "group/member/all",
                HttpMethod.POST, new HttpEntity<>(userInGroup2, headers), new ParameterizedTypeReference<Set<Group>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    // Test 3, Evan Sahit
    @Test
    @DisplayName("Create a group member using the endpoint")
    void TestCreateAGroup(){
        group.setGroupMembers(users);

        ResponseEntity response = this.restTemplate.exchange("http://localhost:" + port + "group/group/create",
                HttpMethod.POST, new HttpEntity<>(group, headers), new ParameterizedTypeReference<Set<Group>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    //Test 4, Evan Sahit
    @Test
    @DisplayName("Add data sets to group")
    void TestRemoveUserFromGroup(){
        DatasetSharedWithGroup dataset = new DatasetSharedWithGroup();
        DatasetSharedWithGroup dataset2 = new DatasetSharedWithGroup();

        List<DatasetSharedWithGroup> datasets = new ArrayList<>();
        datasets.add(dataset);
        datasets.add(dataset2);

        group.setDatasets(datasets);

        assertEquals(2, group.getDatasets().size());
    }
}