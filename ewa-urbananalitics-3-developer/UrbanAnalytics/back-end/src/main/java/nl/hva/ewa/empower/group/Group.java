package nl.hva.ewa.empower.group;

import com.fasterxml.jackson.annotation.*;
import nl.hva.ewa.empower.dataset_sharedWith_group.DatasetSharedWithGroup;
import nl.hva.ewa.empower.user.User;
import nl.hva.ewa.empower.user_in_group.UserInGroup;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "Group")
@Table(name = "_group")
public class Group {
    @Id
    @Column(columnDefinition = "groupId")
    @GeneratedValue
    private int groupId;
    private String name;
    private Date createdOn;

    @OneToOne
    @JoinColumn(name="createdBy")
    private User createdBy;

    @Column(length = 50000)
    @Lob
    private Byte[] groupImg;

    @OneToMany(mappedBy = "group")
    private Set<UserInGroup> groupMembers;

    @JsonManagedReference
    @OneToMany(mappedBy = "group")
    private List<DatasetSharedWithGroup> datasets;

    public Group(){
        this.createdOn = new Date();
    }

    public Group(String name, User createdBy) {
        super();
        this.name = name;
        this.createdOn = new Date();
        this.createdBy = createdBy;
    }

    public Group(String name, User createdBy, Set<UserInGroup> groupMembers){
        super();
        this.name = name;
        this.createdOn = new Date();
        this.createdBy = createdBy;
        this.groupMembers = groupMembers;
    }

    public Group(String name, Set<UserInGroup> groupMembers) {
        super();
        this.name = name;
        this.groupMembers = groupMembers;
        this.createdOn = new Date();
    }

    public Group(String name) {
        super();
        this.createdOn = new Date();
        this.name = name;
    }

    public Group(int groupId) {
        super();
        this.createdOn = new Date();
        this.groupId = groupId;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public int getGroupId() {
        return groupId;
    }

    public List<User> getGroupMembers() {
        List<User> groupMembers = new ArrayList<>();

        this.groupMembers.forEach(member -> {
            groupMembers.add(member.getUser());
        });

        return groupMembers;
    }

    public Byte[] getGroupImg() {
        return groupImg;
    }

    public void setGroupImg(byte[] groupImg) {
        Byte[] byteObject = new Byte[groupImg.length];

        int i = 0;
        for(byte b : groupImg){
            byteObject[i++] = b;
        }

        this.groupImg = byteObject;
    }

    public String getName() {
        return name;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setGroupMembers(Set<UserInGroup> groupMembers) {
        this.groupMembers = groupMembers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DatasetSharedWithGroup> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<DatasetSharedWithGroup> datasets) {
        this.datasets = datasets;
    }
}