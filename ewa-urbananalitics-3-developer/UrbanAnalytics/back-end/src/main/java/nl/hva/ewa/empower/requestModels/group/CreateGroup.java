package nl.hva.ewa.empower.requestModels.group;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CreateGroup {
    private MultipartFile groupImg;
    private String groupName;
    private String groupMembers;

    public CreateGroup() {}

    public CreateGroup(String groupName) {
        this.groupName = groupName;
    }

    public CreateGroup(String groupName, String groupMembers) {
        this.groupName = groupName;
        this.groupMembers = groupMembers;
    }

    public CreateGroup(MultipartFile groupImg, String groupName, String groupMembers) {
        this.groupImg = groupImg;
        this.groupName = groupName;
        this.groupMembers = groupMembers;
    }

    public MultipartFile getGroupImg() {
        return groupImg;
    }

    public void setGroupImg(MultipartFile groupImg) {
        this.groupImg = groupImg;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(String groupMembers) {
        this.groupMembers = groupMembers;
    }
}
