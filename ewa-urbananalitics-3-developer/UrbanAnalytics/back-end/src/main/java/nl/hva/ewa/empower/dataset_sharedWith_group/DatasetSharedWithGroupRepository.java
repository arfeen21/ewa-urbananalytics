package nl.hva.ewa.empower.dataset_sharedWith_group;

import nl.hva.ewa.empower.dataset.Dataset;
import nl.hva.ewa.empower.group.Group;
import nl.hva.ewa.empower.user.User;
import nl.hva.ewa.empower.user_in_group.UserInGroup;
import org.springframework.data.repository.CrudRepository;

public interface DatasetSharedWithGroupRepository extends CrudRepository<DatasetSharedWithGroup, Integer> {
    DatasetSharedWithGroup findDatasetSharedWithGroupByGroupAndDataset(Group group, Dataset dataset);
}
