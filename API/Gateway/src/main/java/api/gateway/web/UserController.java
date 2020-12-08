package api.gateway.web;

import api.gateway.domain.UserContainer;
import api.gateway.domain.UserEntityDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/waiters")
@RefreshScope
public class UserController {


    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;
    private URI userServiceUri;
    private final String testVariable;


    public UserController(DiscoveryClient discoveryClient, RestTemplate restTemplate,
                          @Value("${test}") String testVariable) {
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
        this.testVariable = testVariable;
    }


    @GetMapping("/test")
    String test() {
        return testVariable;
    }


    @PostMapping("/add")
    public ResponseEntity<?> createNewUser(@RequestBody UserEntityDto newUser) {
        this.userServiceUri = this.discoveryClient.getInstances("user-service").get(0).getUri();
        UserEntityDto result = this.restTemplate.postForObject(userServiceUri + "/waiters/add",
                newUser, UserEntityDto.class);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllWaiters() {
        this.userServiceUri = this.discoveryClient.getInstances("user-service").get(0).getUri();
        List<UserEntityDto> result = this.restTemplate.getForObject(this.userServiceUri + "/waiters/all",
                UserContainer.class).getUsers();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    //Mock login just for development purposes
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntityDto loginUser) {
        this.userServiceUri = this.discoveryClient.getInstances("user-service").get(0).getUri();
        UserEntityDto logged = this.restTemplate.postForObject(this.userServiceUri + "/waiters/login",
                loginUser, UserEntityDto.class);

        return new ResponseEntity<>(logged, HttpStatus.OK);
    }

}
