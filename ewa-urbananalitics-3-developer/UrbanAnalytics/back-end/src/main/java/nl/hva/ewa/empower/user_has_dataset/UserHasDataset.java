package nl.hva.ewa.empower.user_has_dataset;

import nl.hva.ewa.empower.dataset.Dataset;
import nl.hva.ewa.empower.group.Group;
import nl.hva.ewa.empower.user.User;

import javax.persistence.*;

@Entity(name = "User_has_Dataset")
@Table(name = "user_has_dataset")
public class UserHasDataset {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "udId")
    private Integer udId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @MapsId("datasetId")
    @JoinColumn(name = "datasetId")
    private Dataset dataset;

    Boolean admin;

    public UserHasDataset() {
    }

    public UserHasDataset(User user, Dataset dataset, Boolean admin) {
        this.user = user;
        this.dataset = dataset;
        this.admin = admin;
    }

    public Integer getUdId() {
        return udId;
    }

    public void setUdId(Integer udId) {
        this.udId = udId;
    }
}
