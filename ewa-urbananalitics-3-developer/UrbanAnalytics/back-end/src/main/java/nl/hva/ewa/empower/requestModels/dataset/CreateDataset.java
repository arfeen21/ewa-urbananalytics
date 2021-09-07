package nl.hva.ewa.empower.requestModels.dataset;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hva.ewa.empower.chart.Chart;
import nl.hva.ewa.empower.dataset.Dataset;

import java.util.ArrayList;

public class CreateDataset {
    private String datasetInfo;

    public CreateDataset() { }

    public CreateDataset(String datasetInfo){
        super();
        this.datasetInfo = datasetInfo;
    }

    public String getDatasetInfo() {
        return datasetInfo;
    }

    public void setDatasetInfo(String datasetInfo) {
        this.datasetInfo = datasetInfo;
    }
}
