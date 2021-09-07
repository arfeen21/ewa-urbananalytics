package nl.hva.ewa.empower.user_in_group;

import nl.hva.ewa.empower.requestModels.group.RemoveUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserInGroupResource {
    @Autowired
    private UserInGroupService userInGroupService;

    @PostMapping(path = "group/member/remove")
    public ResponseEntity<?> removeUser(@RequestBody RemoveUser userInGroup, @RequestHeader("Authentication") String jwtToken){
        return userInGroupService.removeUser(userInGroup, jwtToken);
    }
}
