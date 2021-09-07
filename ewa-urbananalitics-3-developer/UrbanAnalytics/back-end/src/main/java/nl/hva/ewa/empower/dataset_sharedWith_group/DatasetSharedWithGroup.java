package nl.hva.ewa.empower.dataset_sharedWith_group;

import com.fasterxml.jackson.annotation.JsonBackReference;
import nl.hva.ewa.empower.dataset.Dataset;
import nl.hva.ewa.empower.group.Group;
import nl.hva.ewa.empower.group.GroupRepository;
import nl.hva.ewa.empower.user.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity(name = "Dataset_sharedWith_Group")
@Table(name = "dataset_sharedWith_group")
public class DatasetSharedWithGroup {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "dgId")
    private int dgId;

    @ManyToOne
    @JoinColumn(name = "datasetId")
    private Dataset dataset;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "groupId")
    private Group group;

    @OneToOne
    @JoinColumn(name = "fromUser")
    private User fromUser;

    public DatasetSharedWithGroup() {
    }

    public Map getDataset() {
        Map json = new HashMap<>();
        json.put("datasetId", dataset.getDatasetId());
        json.put("name", dataset.getName());
        json.put("summary", dataset.getSummary());
        json.put("uploadedBy", dataset.getUploadedBy());
        json.put("uploadedOn", dataset.getUploadedOn());
        return json;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }

    public Group getGroup() {
        return group;
    }

    public User getFromUser() {
        return fromUser;
    }
}
