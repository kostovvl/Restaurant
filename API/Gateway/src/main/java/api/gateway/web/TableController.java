package api.gateway.web;

import api.gateway.domain.TableContainer;
import api.gateway.domain.TableEntityDto;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tables")
public class TableController {

    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;
    private URI userServiceUri;
    private final String mock;

    public TableController(DiscoveryClient discoveryClient, RestTemplate restTemplate) {
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
        this.mock = "mock";
    }

    @PostMapping("/create/{tableNumber}")
    public ResponseEntity<String> createNewTable(@PathVariable(name = "tableNumber") int tableNumber) {
        this.userServiceUri = this.discoveryClient.getInstances("table-service").get(0).getUri();

        this.restTemplate.postForObject(this.userServiceUri + "/create/" + tableNumber, this.mock, ResponseEntity.class);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTables() {
        this.userServiceUri = this.discoveryClient.getInstances("table-service").get(0).getUri();
        List<TableEntityDto> result =
                this.restTemplate.getForObject(this.userServiceUri + "/all", TableContainer.class)
                        .getTables();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/free")
    public ResponseEntity<?> getAllFreeTables() {
        this.userServiceUri = this.discoveryClient.getInstances("table-service").get(0).getUri();
        List<TableEntityDto> result = this.restTemplate.getForObject(this.userServiceUri + "/free", TableContainer.class)
                .getTables();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/add_waiter/{waiterId}/{tableId}")
    public ResponseEntity<String> addTableToWaiter(@PathVariable(name = "waiterId") long waiterId,
                                                   @PathVariable(name = "tableId") long tableId) {
        this.userServiceUri = this.discoveryClient.getInstances("table-service").get(0).getUri();

        this.restTemplate.put(this.userServiceUri + "/add_waiter/" + waiterId + "/" + tableId, this,mock);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/remove_waiter/{tableId}")
    public ResponseEntity<String> removeTableFromWaiter(@PathVariable(name = "tableId") long tableId) {

        this.userServiceUri = this.discoveryClient.getInstances("table-service").get(0).getUri();
        this.restTemplate.put(this.userServiceUri + "/remove_waiter/"  + tableId, this.mock);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
