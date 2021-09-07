package nl.hva.ewa.empower.dataset_sharedWith_group;

import nl.hva.ewa.empower.requestModels.group.RemoveDataset;
import nl.hva.ewa.empower.requestModels.group.RemoveUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class DatasetSharedWithGroupResource {
    @Autowired
    private DatasetSharedWithGroupService datasetSharedWithGroupService;

    @PostMapping(path = "group/dataset/remove")
    public ResponseEntity<?> removeDatasetFromGroup(@ModelAttribute RemoveDataset removeDataset, @RequestHeader("Authentication") String jwtToken){
        return datasetSharedWithGroupService.removeDatasetFromGroup(removeDataset, jwtToken);
    }
}
