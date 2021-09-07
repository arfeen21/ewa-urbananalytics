package nl.hva.ewa.empower.dataset;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hva.ewa.empower.chart.Chart;
import nl.hva.ewa.empower.dataset_sharedWith_group.DatasetSharedWithGroup;
import nl.hva.ewa.empower.group.Group;
import nl.hva.ewa.empower.requestModels.dataset.CreateDataset;
import nl.hva.ewa.empower.user.User;
import nl.hva.ewa.empower.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DatasetTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    private Dataset dataset;
    private HttpHeaders headers;
    private User user;

    @LocalServerPort
    private int port;

    /* Jerry Geerts tests */
    @BeforeEach
    private void setup(){
        ArrayList data = new ArrayList();
        List<Chart> charts = new ArrayList<>();

        user = new User();
        user.setEmail("jerry8@live.nl");
        user.setFirstName("Jerry");
        user.setLastName("Geerts");

        dataset = new Dataset("Mijn dataset", "Dit is een test dataset", Regio.EU, false, data, charts);
        dataset.setGroups(new ArrayList<>());
        dataset.setUploadedBy(user);

        headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authentication", jwtUtil.generateToken(user));
    }

    /* Jerry Geerts test 1 */
    @Test
    @DisplayName("Find a dataset by id")
    void findOneDataset() {
        int id = 1117;

        ResponseEntity<Dataset> response = this.restTemplate.exchange("http://localhost:" + port + "/dataset/" + id, HttpMethod.GET, new HttpEntity<>(headers), Dataset.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    /* Jerry Geerts test 2 */
    @Test
    @DisplayName("Add a dataset to the repository")
    void addOneDataset() throws JsonProcessingException {
        dataset.setUploadedBy(user);

        String json = new ObjectMapper().writeValueAsString(dataset);

        CreateDataset createDataset = new CreateDataset();
        createDataset.setDatasetInfo(json);

        HttpEntity entity = new HttpEntity<>(createDataset, headers);
        ResponseEntity<Dataset> response = this.restTemplate.exchange("http://localhost:" + port + "/dataset/add", HttpMethod.POST,entity, Dataset.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    /* Jerry Geerts test 3 */
    @Test
    @DisplayName("Get all datasets")
    void GetAllDatasets() {
        HttpEntity entity = new HttpEntity<>(headers);
        ResponseEntity response1 = this.restTemplate.exchange("http://localhost:" + port + "/dataset/all", HttpMethod.GET, entity, new ParameterizedTypeReference<Set<Dataset>>() {});

        assertEquals(HttpStatus.OK, response1.getStatusCode());
    }

    /* Jerry Geerts test 4 */
    @Test
    @DisplayName("Add datasets to group")
    void AddDatasetToGroup(){
        DatasetSharedWithGroup datasetSharedWithGroup = new DatasetSharedWithGroup();
        Group group = new Group();

        datasetSharedWithGroup.setGroup(group);
        datasetSharedWithGroup.setDataset(dataset);
        datasetSharedWithGroup.setFromUser(user);

        List<DatasetSharedWithGroup> list = new ArrayList<>();
        list.add(datasetSharedWithGroup);

        group.setDatasets(list);

        assertSame(group.getDatasets().get(0).getDataset().get("name"), dataset.getName());
    }

    /* Jerry Geerts test 5 */
    @Test
    @DisplayName("Add chart to a dataset")
    void AddChartToDataset(){
        Chart chart = new Chart();
        List<Chart> list = new ArrayList<>();
        list.add(chart);

        dataset.setCharts(list);

        assertSame(dataset.getCharts().get(0), chart);
    }

    /* Jerry Geerts test 6 */
    @Test
    @DisplayName("Set region enum")
    void SetRegionEnum(){
        dataset.setRegion(Regio.EU);
        assertSame(dataset.getRegion().toString(), "EU");

        dataset.setRegion(Regio.National);
        assertSame(dataset.getRegion().toString(), "National");

        dataset.setRegion(Regio.US);
        assertNotEquals(dataset.getRegion().toString(), "Test");
    }

    /* Jerry Geerts test 7 */
    @Test
    @DisplayName("Set uploadedBy")
    void SetUploadedBy(){
        assertEquals(dataset.getUploadedBy(), user.getFirstName() + " " + user.getLastName());

        User testUser1 = new User("test@test.nl");
        testUser1.setFirstName("test");
        testUser1.setLastName("test");

        dataset.setUploadedBy(testUser1);
        assertEquals(dataset.getUploadedBy(), "test test");

        User testUser2 = new User("mock@mock.nl");
        testUser2.setFirstName("mock");
        testUser2.setLastName("Test");

        dataset.setUploadedBy(testUser2);
        assertNotEquals(dataset.getUploadedBy(),  "mock mock");
    }

    /* Jerry Geerts test 8 */
    @Test
    @DisplayName("Change dataset name")
    void ChangeDatasetName(){
        assertEquals(dataset.getName(), "Mijn dataset");

        dataset.setName("new Dataset");
        assertEquals(dataset.getName(), "new Dataset");

        dataset.setName("Test name");
        assertNotEquals(dataset.getName(), "False name");
    }

    /* Jerry Geerts test 9 */
    @Test
    @DisplayName("Change dataset summary")
    void ChangeDataset(){
        assertEquals(dataset.getSummary(), "Dit is een test dataset");

        dataset.setSummary("Met een andere summmary");
        assertEquals(dataset.getSummary(), "Met een andere summmary");

        dataset.setSummary("Foute test naam");
        assertNotEquals(dataset.getSummary(), "Is fout");
    }

    /* Jerry Geerts test 10 */
    @Test
    @DisplayName("Change if a dataset is public")
    void ChangePublicState(){
        assertFalse(dataset.isPublic());

        dataset.setPublic(true);
        assertTrue(dataset.isPublic());

        dataset.setPublic(false);
        assertNotEquals(dataset, true);
    }
}