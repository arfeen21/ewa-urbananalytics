package nl.hva.ewa.empower.dataset;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import nl.hva.ewa.empower.chart.Chart;
import nl.hva.ewa.empower.dataset_sharedWith_group.DatasetSharedWithGroup;
import nl.hva.ewa.empower.group.Group;
import nl.hva.ewa.empower.user.User;
import nl.hva.ewa.empower.user_has_dataset.UserHasDataset;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**Class that represents a dataset object**/

@Entity(name = "Dataset")
@Table(name = "dataset")
public class Dataset {


    @Id
    @GeneratedValue
    @Column(columnDefinition = "datasetId")
    private int datasetId;

    private String name;
    private String summary;
    private Date uploadedOn;
    private Regio region;
    private boolean isPublic;
    private ArrayList data;

    @OneToOne
    @JoinColumn(name="uploadedBy")
    private User uploadedBy;

    @OneToMany(mappedBy = "dataset")
    private List<UserHasDataset> users;

    @OneToMany(mappedBy = "dataset")
    private List<Chart> charts;

    @OneToMany(mappedBy = "dataset")
    private List<DatasetSharedWithGroup> groups;

    public Dataset(){
        this.uploadedOn = new Date();
    }

    public Dataset(String name, String summary, Regio regio, boolean isPublic, ArrayList data , List<Chart> charts) {
        super();
        this.name = name;
        this.summary = summary;
        this.region = regio;
        this.isPublic = isPublic;
        this.data = data;
        this.charts = charts;
    }

    public String getName() {
        return name;
    }

    public int getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(int datasetId) {
        this.datasetId = datasetId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getUploadedOn() {
        return uploadedOn;
    }

    public void setUploadedOn(Date uploadedOn) {
        this.uploadedOn = uploadedOn;
    }

    public String getUploadedBy() {
        return uploadedBy.getFirstName() + " " + uploadedBy.getLastName();
    }

    public void setUploadedBy(User uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public Regio getRegion() {
        return region;
    }

    public void setRegion(Regio region) {
        this.region = region;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public ArrayList getData() {
        return data;
    }

    public void setData(ArrayList data) {
        this.data = data;
    }

    public List<UserHasDataset> getUsers() {
        return users;
    }

    public void setUsers(List<UserHasDataset> users) {
        this.users = users;
    }

    public List<Chart> getCharts() {
        return charts;
    }

    public void setCharts(List<Chart> charts) {
        this.charts = charts;
    }

    public List<DatasetSharedWithGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<DatasetSharedWithGroup> groups) {
        this.groups = groups;
    }


}