package nl.hva.ewa.empower.user_in_group;

import nl.hva.ewa.empower.group.Group;
import nl.hva.ewa.empower.user.User;

import javax.persistence.*;

@Entity(name = "User_in_Group")
@Table(name = "user_in_group")
public class UserInGroup {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "ugId")
    private Integer ugId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "groupId")
    private Group group;

    Boolean admin;

    public UserInGroup() {
    }

    public UserInGroup(User user, Group group) {
        this.group = group;
        this.user = user;
        this.admin = false;
    }

    public UserInGroup(User user, Group group, Boolean admin) {
        this.group = group;
        this.user = user;
        this.admin = admin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
