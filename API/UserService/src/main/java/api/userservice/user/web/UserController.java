package api.userservice.user.web;

import api.userservice.user.domain.userEntity.UserEntityContainer;
import api.userservice.user.domain.userEntity.UserEntityDto;
import api.userservice.user.service.UserEntityService;
import org.hibernate.TransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import java.util.List;

@RestController
@RequestMapping("/waiters")
public class UserController {

    private final UserEntityService userEntityService;

    public UserController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> createNewUser(@RequestBody UserEntityDto newUser) {
        UserEntityDto result = this.userEntityService.createNewUser(newUser);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllWaiters() {
        UserEntityContainer result = new UserEntityContainer(this.userEntityService.getAllWaiters());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    //Mock login just for development purposes
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntityDto loginUser) {
        UserEntityDto logged = this.userEntityService.login(loginUser);

        return new ResponseEntity<>(logged, HttpStatus.OK);
    }


    //********** Error Handlers **********//

    @ExceptionHandler({PersistenceException.class, TransactionException.class})
    public ResponseEntity<String> handleDbExceptions(EntityExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
