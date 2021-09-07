package nl.hva.ewa.empower.group;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hva.ewa.empower.notification.Notification;
import nl.hva.ewa.empower.notification.NotificationService;
import nl.hva.ewa.empower.requestModels.group.CreateGroup;
import nl.hva.ewa.empower.user.User;
import nl.hva.ewa.empower.user.UserService;
import nl.hva.ewa.empower.user_in_group.UserInGroup;
import nl.hva.ewa.empower.user_in_group.UserInGroupService;
import nl.hva.ewa.empower.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.*;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserInGroupService userInGroupService;
    @Autowired
    private NotificationService notificationService;

    public GroupService(){

    }

    public Set<Group> getAllMembers() {
        Set<Group> memberSet = new HashSet<>();

        groupRepository.findAll().forEach(memberSet::add);

        return memberSet;
    }

    public ResponseEntity<?> addMember(User aUser, String jwtToken) {
        User currentUser = userService.getSingleUser(jwtUtil.extractEmail(jwtToken));
        return ResponseEntity.ok().body("");
    }

    public Group findGroupById(int groupId){
        return this.groupRepository.findById(groupId).orElse(null);
    }

    public ResponseEntity<?> createGroup(CreateGroup group, String jwtToken) throws IOException {
        //Group name checks
        if(group.getGroupName().isEmpty()){
            return ResponseEntity.badRequest().body("Please provide a group name.");
        }
        else if(!group.getGroupName().matches("^.{4,}$")){
            return ResponseEntity.badRequest().body("Please enter a valid group name with minimum length of 4.");
        }

        User user = userService.getSingleUser(jwtUtil.extractEmail(jwtToken));

        //User checks
        if(user == null){
            return ResponseEntity.badRequest().body("Current user could not be found");
        }

        Set<UserInGroup> UserInGroup = new ObjectMapper().readValue(group.getGroupMembers(), new TypeReference<Set<UserInGroup>>() {});
        Group newGroup = new Group(group.getGroupName(), UserInGroup);

        // Set createdBy
        newGroup.setCreatedBy(user);
        if(group.getGroupImg() != null) newGroup.setGroupImg(group.getGroupImg().getBytes());

        // Save group and groupMembers
        groupRepository.save(newGroup);
        userInGroupService.addMemberToGroup(newGroup , user, true);

        ArrayList<Integer> alreadySent = new ArrayList<Integer>();

        UserInGroup.forEach(member -> {
            userInGroupService.addMemberToGroup(newGroup , member.getUser());
            if (!alreadySent.contains(member.getUser().getUserId())){
                System.out.println("new message: from: " + user.getFirstName() + " to: " + member.getUser().getFirstName());
                try{
                    alreadySent.add(member.getUser().getUserId());
                    notificationService.addNotification(new Notification(
                            user,
                            member.getUser(),
                            user.getFirstName() + " has added you to their group: " + newGroup.getName()
                    ));
                }catch (Exception e){
                    System.out.println("Creating nottification went wrong....");
                    System.out.println(e);
                }
            }
        });
        return ResponseEntity.ok().body("Succesfully created a group!");
    }
}
