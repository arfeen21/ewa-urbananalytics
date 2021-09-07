package nl.hva.ewa.empower.group;

import nl.hva.ewa.empower.requestModels.group.CreateGroup;
import nl.hva.ewa.empower.user.User;
import nl.hva.ewa.empower.user_in_group.UserInGroup;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
@RestController
class GroupRepositoryTest {

    @Mock
    GroupRepository repo = mock(GroupRepository.class);

    User user = new User("test@test.com" );
    User user2 = new User("user2" , "test" , "test2@test.com"  , "password");
    UserInGroup userInGroup = new UserInGroup();
    Set<UserInGroup> users = new HashSet<>();
    public Group group = new Group("group1" , user , users  );

    GroupService groupService = new GroupService();

    @Test
    void TestEmptyGroupName(){
        CreateGroup createGroup = new CreateGroup("");
        try{
            groupService.createGroup(createGroup ,  "jwt");
        }catch (Exception e){
            assertEquals("Please provide a group name" ,e);
        }
    }
}