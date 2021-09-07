package nl.hva.ewa.empower.requestModels.dataset;

import nl.hva.ewa.empower.dataset.Regio;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DatasetInfo {
    private String name;
    private String summery;
    private Regio region;
    private Boolean isPublic;
    private ArrayList data;
    private ArrayList datasetCharts;

    public DatasetInfo() { }

    public DatasetInfo(String name, String summery, Regio region, Boolean isPublic) {
        this.name = name;
        this.summery = summery;
        this.region = region;
        this.isPublic = isPublic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummery() {
        return summery;
    }

    public void setSummery(String summery) {
        this.summery = summery;
    }

    public Regio getRegion() {
        return region;
    }

    public void setRegion(Regio region) {
        this.region = region;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }
}
