package nl.hva.ewa.empower.chart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import nl.hva.ewa.empower.dataset.Dataset;

import javax.persistence.*;
import java.util.ArrayList;

@Entity(name = "Chart")
@Table(name = "chart")
public class Chart {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "chartId")
    private int chartId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "datasetId")
    private Dataset dataset;

    private String name;
    private String typeValue;
    private ArrayList data;
    private ArrayList header;
    private int xValue;
    private int yValue;
    private int groupByValue;
    private String description;

    public Chart() {
    }

    public Chart(String name, String typeValue, ArrayList data, int xValue, int yValue, int groupByValue, String description, ArrayList header) {
        this.name = name;
        this.typeValue = typeValue;
        this.data = data;
        this.xValue = xValue;
        this.yValue = yValue;
        this.groupByValue = groupByValue;
        this.description = description;
        this.header = header;
    }

    public ArrayList getHeader() {
        return header;
    }

    public void setHeader(ArrayList header) {
        this.header = header;
    }

    public int getChartId() {
        return chartId;
    }

    public void setChartId(int chartId) {
        this.chartId = chartId;
    }

    public Dataset getDataset() {
        return dataset;
    }

    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList getData() {
        return data;
    }

    public void setData(ArrayList data) {
        this.data = data;
    }

    public int getxValue() {
        return xValue;
    }

    public void setxValue(int xValue) {
        this.xValue = xValue;
    }

    public int getyValue() {
        return yValue;
    }

    public void setyValue(int yValue) {
        this.yValue = yValue;
    }

    public int getGroupByValue() {
        return groupByValue;
    }

    public void setGroupByValue(int groupByValue) {
        this.groupByValue = groupByValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }
}
