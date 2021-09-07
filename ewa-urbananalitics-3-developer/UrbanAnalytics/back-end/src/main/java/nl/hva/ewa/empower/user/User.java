package nl.hva.ewa.empower.user;

import com.fasterxml.jackson.annotation.*;
import nl.hva.ewa.empower.dataset.Dataset;
import nl.hva.ewa.empower.group.Group;
import nl.hva.ewa.empower.notification.Notification;
import nl.hva.ewa.empower.user_has_dataset.UserHasDataset;
import nl.hva.ewa.empower.user_in_group.UserInGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity(name = "User")
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "userId")
    private int userId;
    private String firstname;
    private String lastname;
    @JsonProperty
    private String password;
    private String email;
    private String phoneNumber;
    private String location;
    private String aboutMe;
    private String job;
    private String website;
    private String jobDesc;
    private String profileImage;
    private boolean isAdmin;
    private ArrayList<Dataset> starredDatasets;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserInGroup> groups;

    @OneToMany(mappedBy = "user")
    private List<UserHasDataset> datasets;

    @JsonManagedReference(value = "from")
    @OneToMany(mappedBy = "from")
    private List<Notification> sendNotifications;

    @JsonManagedReference
    @OneToMany(mappedBy = "to")
    private List<Notification> recievedNotifications;



    public User() {
    }

    public User(int userId) {
        this.userId = userId;
    }

    public User(String email) {
        this.email = email;
    }

    public User(String firstName, String lastName, String email, String password) {
        super();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.firstname = firstName;
        this.lastname = lastName;
        this.email = email;
        //TODO: Move password encryption to frontend (don't expose plain password over HTTP request)
        this.password = bCryptPasswordEncoder.encode(password);

        this.phoneNumber = "Not set";
        this.location = "Not set";
        this.aboutMe = "Not set";
        this.job = "Not set";
        this.jobDesc = "Not set";
        this.profileImage = "https://elysator.com/wp-content/uploads/blank-profile-picture-973460_1280-e1523978675847.png";
        this.starredDatasets = null;
    }

    public void addStarredDataset(Dataset starredDataset) {
        if (getStarredDataset(starredDataset.getDatasetId()) == null){
            this.starredDatasets.add(starredDataset);
        }
    }

    public Dataset getStarredDataset(int starredDatasetId) {
        return this.starredDatasets.get(starredDatasetId);
    }

    public void removeDataset(int starredDatasetId) {
        if(getStarredDataset(starredDatasetId) != null) {
            this.starredDatasets.remove(starredDatasetId);
        }
    }

    public ArrayList<Dataset> getAllStarredDatasets() {
        return this.starredDatasets;
    }

    public void setStarredDatasets(ArrayList<Dataset> starredDatasets) {
        this.starredDatasets.addAll(starredDatasets);
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getJob() {
        return job;
    }

    public List<UserHasDataset> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<UserHasDataset> datasets) {
        this.datasets = datasets;
    }

    public int getUserId() {
        return userId;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public String getTel() {
        return phoneNumber;
    }

    public void setTel(String tel) {
        this.phoneNumber = tel;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getBaan() {
        return job;
    }

    public void setBaan(String baan) {
        this.job = baan;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getFoto() {
        return profileImage;
    }

    public void setFoto(String foto) {
        this.profileImage = foto;
    }

    public void setSendNotifications(List<Notification> sendNotifications) {
        this.sendNotifications = sendNotifications;
    }

    public void setRecievedNotifications(List<Notification> recievedNotifications) {
        this.recievedNotifications = recievedNotifications;
    }

    public List<Notification> getSendNotifications() {
        return sendNotifications;
    }

    public List<Notification> getRecievedNotifications() {
        return recievedNotifications;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getWebsite() {
        return website;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public List<Group> getGroups() {
        List<Group> groups = new ArrayList<>();

        this.groups.forEach(group -> {
            if (!groups.contains(group)) {
                groups.add(group.getGroup());
            }
        });

        return groups;
    }


    public void setGroups(List<UserInGroup> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId + "\n" +
                ", firstname='" + firstname + '\'' + "\n" +
                ", lastname='" + lastname + '\'' + "\n" +
                ", password='" + password + '\'' + "\n" +
                ", email='" + email + '\'' + "\n" +
                ", phoneNumber='" + phoneNumber + '\'' + "\n" +
                ", location='" + location + '\'' + "\n" +
                ", aboutMe='" + aboutMe + '\'' + "\n" +
                ", job='" + job + '\'' + "\n" +
                ", jobDesc='" + jobDesc + '\'' + "\n" +
                ", profileImage='" + profileImage + '\'' + "\n" +
                ", groups=" + groups + "\n" +
                ", datasets=" + datasets + "\n" +
                ", sendNotifications=" + sendNotifications + "\n" +
                ", recievedNotifications=" + recievedNotifications + "\n" +
                '}';
    }
}
