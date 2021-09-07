package nl.hva.ewa.empower.dataset;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hva.ewa.empower.chart.ChartService;
import nl.hva.ewa.empower.dataset_sharedWith_group.DatasetSharedWithGroup;
import nl.hva.ewa.empower.dataset_sharedWith_group.DatasetSharedWithGroupService;
import nl.hva.ewa.empower.group.Group;
import nl.hva.ewa.empower.group.GroupService;
import nl.hva.ewa.empower.requestModels.dataset.CreateDataset;
import nl.hva.ewa.empower.user.User;
import nl.hva.ewa.empower.user.UserRepository;
import nl.hva.ewa.empower.user.UserService;
import nl.hva.ewa.empower.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;

import nl.hva.ewa.empower.dataset.Regio;

import javax.xml.crypto.Data;

@Component
public class DatasetService {
    @Autowired
    private DatasetRepository datasetRepository;
    @Autowired
    private ChartService chartService;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private DatasetSharedWithGroupService datasetSharedWithGroupService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;

    /**
     * Constructor
     */
    public DatasetService() {
    }

    public Set<Dataset> getAllDatasets() {
        Set<Dataset> dataset = new HashSet<>();

        datasetRepository.findAll().forEach(dataset::add);

        return dataset;
    }

    /**
     * Method to return a specified dataset
     *
     * @param datasetId a number (int) which identifies a dataset by its ID
     * @return the specified datset
     */
    public Dataset getDataset(int datasetId) {
        return datasetRepository.findById(datasetId).orElse(null);
    }

    /**
     * Method to return a specified dataset
     *
     * @param datasetName a String which identifies a dataset by its given name;
     * @return the specified datset
     */
    public Dataset getDataset(String datasetName) {
        return datasetRepository.findByName(datasetName);
    }

    /**
     * Method to add a new dataset
     *
     * @param createDataset
     * @param jwtToken
     */
    public ResponseEntity addDataset(CreateDataset createDataset, String jwtToken) throws JsonProcessingException {
        User user = userService.getSingleUser(jwtUtil.extractEmail(jwtToken));
        Dataset dataset = new ObjectMapper().readValue(createDataset.getDatasetInfo(), Dataset.class);

        dataset.setUploadedBy(user);
        dataset.setUploadedOn(new Date());
        Dataset storedDataset = this.datasetRepository.save(dataset);

        dataset.getCharts().forEach(chart -> {
            chart.setDataset(storedDataset);
            this.chartService.saveChart(chart);
        });

        dataset.getGroups().forEach(group -> {
            Group aGroup = this.groupService.findGroupById(group.getGroup().getGroupId());

            group.setGroup(aGroup);
            group.setFromUser(user);
            group.setDataset(storedDataset);

            this.datasetSharedWithGroupService.saveGroup(group);
        });

        return ResponseEntity.status(200).body(Collections.singletonMap("response", storedDataset.getDatasetId()));
    }

    public Dataset getADataset(int id, String jwtToken) {
        return this.datasetRepository.findById(id).orElse(null);
    }

    /**
     * Method to add a data set to a user's list of starred or favorited data sets
     *
     * @param datasetId number which represents the id of the data set to be starred
     * @param jwtToken  jwtToken of the user
     */
    public ResponseEntity addStarredDataset(int datasetId, String jwtToken) {
        Dataset starredDataset = getDataset(datasetId);
        User userThatStarred = this.userService.getSingleUser(jwtUtil.extractEmail(jwtToken));

        //Adds the starred data set to the user's list of starred data sets
        userThatStarred.addStarredDataset(starredDataset);

        userRepository.save(userThatStarred);

        return ResponseEntity.status(200).body("Data set has been successfully added to your starred data sets!");
    }

    /**
     * Method to return all starred data sets that a user has
     *
     * @param jwtToken jwtToken of the user
     */
    public ArrayList<Dataset> getAllStarredDatasets(String jwtToken) {
        User user = userService.getSingleUser(jwtUtil.extractEmail(jwtToken));

        return user.getAllStarredDatasets();
    }

}

/*
  cors
  orm
  auth
 */