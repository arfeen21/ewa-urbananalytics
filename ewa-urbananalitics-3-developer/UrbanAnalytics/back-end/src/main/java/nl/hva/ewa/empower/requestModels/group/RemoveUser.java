package nl.hva.ewa.empower.requestModels.group;

public class RemoveUser {
    private int user;
    private int group;

    public RemoveUser() {
    }

    public RemoveUser(int user, int group) {
        this.user = user;
        this.group = group;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
