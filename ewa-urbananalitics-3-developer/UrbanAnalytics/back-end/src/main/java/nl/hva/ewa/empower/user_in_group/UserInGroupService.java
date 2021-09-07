package nl.hva.ewa.empower.user_in_group;

import nl.hva.ewa.empower.group.Group;
import nl.hva.ewa.empower.group.GroupRepository;
import nl.hva.ewa.empower.group.GroupService;
import nl.hva.ewa.empower.requestModels.group.RemoveUser;
import nl.hva.ewa.empower.user.User;
import nl.hva.ewa.empower.user.UserRepository;
import nl.hva.ewa.empower.user.UserService;
import nl.hva.ewa.empower.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserInGroupService {
    @Autowired
    private UserInGroupRepository userInGroupRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;

    public UserInGroupService() {
    }

    public UserInGroup addMemberToGroup(Group group, User user){
        UserInGroup newUserInGroup = new UserInGroup(user, group);

        if(userInGroupRepository.findUserInGroupsByGroupAndUser(group, user) == null){
            userInGroupRepository.save(newUserInGroup);
        }

        return newUserInGroup;
    }

    public UserInGroup addMemberToGroup(Group group, User user, boolean admin){
        UserInGroup newUserInGroup = new UserInGroup(user, group, admin);

        userInGroupRepository.save(newUserInGroup);
        //todo: Add error checks?

        return newUserInGroup;
    }

    public boolean addAdmin(UserInGroup userInGroup){
        userInGroup.setAdmin(true);
        //todo: Add error checks?
        return true;
    }

    public ResponseEntity<?> removeUser(RemoveUser userInGroup, String jwtToken) {
        Group group = groupService.findGroupById(userInGroup.getGroup());
        User removeUser = userService.getSingleUser(userInGroup.getUser());
        User user = userService.getSingleUser(jwtUtil.extractEmail(jwtToken));

        UserInGroup ug = userInGroupRepository.findUserInGroupsByGroupAndUser(group, user);

        if(!ug.getAdmin()){
            return ResponseEntity.badRequest().body("You are not admin of this group.");
        }

        UserInGroup savedObject = userInGroupRepository.findUserInGroupsByGroupAndUser(group, removeUser);
        userInGroupRepository.delete(savedObject);

        return ResponseEntity.status(200).body(Collections.singletonMap("groupMember", group.getGroupMembers()));
    }
}
