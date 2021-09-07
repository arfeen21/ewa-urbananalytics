package nl.hva.ewa.empower.group;

import com.fasterxml.jackson.databind.util.JSONPObject;
import nl.hva.ewa.empower.requestModels.group.CreateGroup;
import nl.hva.ewa.empower.user.User;
import org.hibernate.mapping.Any;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
public class GroupResource {
    @Autowired
    private GroupService groupService;

    @GetMapping(path = "group/member/all")
    public Set<Group> getAllMembers(){
        return groupService.getAllMembers();}

    @PostMapping(path = "group/member/add")
    public ResponseEntity addMember(@RequestBody User user, @RequestHeader("Authentication") String jwtToken){
        return groupService.addMember(user, jwtToken);
    }

    @PostMapping(path = "group/group/create")
    public ResponseEntity<?> createGroup(@ModelAttribute CreateGroup createGroup, @RequestHeader("Authentication") String jwtToken) throws IOException {
        return groupService.createGroup(createGroup, jwtToken);
    }
}
