package nl.hva.ewa.empower.requestModels.group;

public class RemoveDataset {
    private int dataset;
    private int group;

    public RemoveDataset() {
    }

    public RemoveDataset(int dataset, int group) {
        this.dataset = dataset;
        this.group = group;
    }

    public int getDataset() {
        return dataset;
    }

    public void setDataset(int dataset) {
        this.dataset = dataset;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
