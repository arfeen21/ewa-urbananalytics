package nl.hva.ewa.empower.chart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChartService {
    @Autowired
    private ChartRepository chartRepository;

    public ChartService() {
    }

    public void saveChart(Chart chart){
        chartRepository.save(chart);
    }
}
