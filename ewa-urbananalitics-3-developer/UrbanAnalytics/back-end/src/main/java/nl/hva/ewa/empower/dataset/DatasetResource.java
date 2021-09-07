package nl.hva.ewa.empower.dataset;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hva.ewa.empower.requestModels.dataset.CreateDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
public class DatasetResource {
    @Autowired
    private DatasetService datasetService;

    @GetMapping(path = "/dataset/all")
    public Set<Dataset> getallDataset(){
        return datasetService.getAllDatasets();
    }

    @GetMapping(path = "/dataset/{id}")
    public Dataset getADataset(@PathVariable int id, @RequestHeader("Authentication") String jwtToken){
        return datasetService.getADataset(id, jwtToken);
    }

    @PostMapping(path = "/dataset/add"  )
    public ResponseEntity<?> addDataset(@RequestBody CreateDataset createDataset, @RequestHeader("Authentication") String jwtToken) throws JsonProcessingException {
        return datasetService.addDataset(createDataset, jwtToken);
    }

    @PostMapping(path = "/dataset/starred")
    public ResponseEntity<?> starDataset(@PathVariable int datasetId, @RequestHeader("Authentication") String jwtToken){
        return datasetService.addStarredDataset(datasetId, jwtToken);
    }

    @GetMapping(path = "dataset/getAllStarred")
    public List<Dataset> getAllStarredDatasets(@RequestHeader("Authentication") String jwtToken){
        return datasetService.getAllStarredDatasets(jwtToken);
    }


}



