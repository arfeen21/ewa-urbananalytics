package nl.hva.ewa.empower.dataset_sharedWith_group;

import nl.hva.ewa.empower.dataset.Dataset;
import nl.hva.ewa.empower.dataset.DatasetService;
import nl.hva.ewa.empower.group.Group;
import nl.hva.ewa.empower.group.GroupService;
import nl.hva.ewa.empower.requestModels.group.RemoveDataset;
import nl.hva.ewa.empower.user.User;
import nl.hva.ewa.empower.user.UserService;
import nl.hva.ewa.empower.user_in_group.UserInGroup;
import nl.hva.ewa.empower.user_in_group.UserInGroupRepository;
import nl.hva.ewa.empower.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DatasetSharedWithGroupService {
    @Autowired
    private UserInGroupRepository userInGroupRepository;
    @Autowired
    private DatasetSharedWithGroupRepository datasetSharedWithGroupRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private DatasetService datasetService;
    @Autowired
    private JwtUtil jwtUtil;

    public DatasetSharedWithGroupService() {
    }

    public void saveGroup(DatasetSharedWithGroup group){
        datasetSharedWithGroupRepository.save(group);
    }

    public ResponseEntity<?> removeDatasetFromGroup(RemoveDataset removeDataset, String jwtToken) {
        Dataset dataset = datasetService.getDataset(removeDataset.getDataset());
        Group group = groupService.findGroupById(removeDataset.getGroup());
        User user = userService.getSingleUser(jwtUtil.extractEmail(jwtToken));

        UserInGroup ug = userInGroupRepository.findUserInGroupsByGroupAndUser(group, user);

        if(!ug.getAdmin()){
            return ResponseEntity.badRequest().body("You are not admin of this group.");
        }

        DatasetSharedWithGroup dg = datasetSharedWithGroupRepository.findDatasetSharedWithGroupByGroupAndDataset(group, dataset) ;
        datasetSharedWithGroupRepository.delete(dg);

        return ResponseEntity.status(200).body("Successfully removed dataset from group!");
    }
}
    