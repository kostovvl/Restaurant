package api.gateway.web;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@RequestMapping("/tables")
public class TableController {

    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;
    private URI userServiceUri;

    public TableController(DiscoveryClient discoveryClient, RestTemplate restTemplate) {
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/create/{tableNumber}")
    public ResponseEntity<String> createNewTable(@PathVariable(name = "tableNumber") int tableNumber) {
        this.userServiceUri = this.discoveryClient.getInstances("table-service").get(0).getUri();
        this.restTemplate.postForObject(this.userServiceUri + "/create/" + tableNumber, new Object(), ResponseEntity.class);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
